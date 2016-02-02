package mop.test.java.database.objectrelationalmapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.backend.caching.Cache;
import mop.main.java.backend.caching.DatabaseCache;
import mop.main.java.common.Constants;
import mop.main.java.common.exceptions.EntityMetadataException;
import mop.main.java.database.model.writable.AcademyAwardWinner;
import mop.main.java.database.model.writable.Movie;
import mop.main.java.database.model.writable.Playlist;
import mop.main.java.database.model.writable.PlaylistMovie;
import mop.main.java.database.objectrelationalmapping.helpers.Attribute;
import mop.main.java.database.objectrelationalmapping.helpers.SqlGenerator;
import mop.test.java.TestBase;

public class SqlGeneratorTest extends TestBase {

    private SqlGenerator sut;
    private Cache cache;
    private Method formatValue;

    @Before
    public void setup() throws NoSuchMethodException {

        sut = new SqlGenerator();

        cache = DatabaseCache.getInstance();
        cache.set(Constants.AppConfigSections.defaultSchema, "TestMop");

        formatValue = SqlGenerator.class.getDeclaredMethod("formatValue", Attribute.class);
        formatValue.setAccessible(true);
    }

    @After
    public void teardown() {

        sut = null;
        cache = null;
        formatValue = null;
    }

    @Test
    public void testGenerateGetScript() {

        // arrange
        mop.main.java.database.model.queryable.Movie movie = new mop.main.java.database.model.queryable.Movie();
        movie.setMovieId(5);

        // act
        String actualScript = sut.generateGetScript("Movie", movie.getMovieId(), cache);

        // assert
        String expectedScript = "SELECT * FROM TestMop.Movie WHERE MovieId = 5 LIMIT 1";
        assertEquals(expectedScript, actualScript);
    }

    @Test
    public void testGenerateSaveScripts() throws EntityMetadataException {

        // arrange
        int movieId = 2;

        Playlist playlist = new Playlist.Builder()
            .playlistId(1)
            .name("Test Playlist")
            .build();

        PlaylistMovie playlistMovie = new PlaylistMovie.Builder()
            .playlistId(1)
            .movieId(movieId)
            .playlist(playlist)
            .build();

        AcademyAwardWinner academyAwardWinner = new AcademyAwardWinner.Builder()
            .academyAwardWinnerId(5)
            .movieId(movieId)
            .build();

        Movie movie = new Movie.Builder()
            .movieId(movieId)
            .title("Test Movie")
            .year("1999")
            .length((short) 120)
            .director("Test Director")
            .description("Test Description")
            .fileLocation("C:/TestFolder")
            .imageLocation("C:/TestImageFolder")
            .isHighDefinition(true)
            .academyAwardWinner(academyAwardWinner)
            .playlistMovie(playlistMovie)
            .build();

        // act
        String[] result = sut.generateSaveScripts(movie, DatabaseCache.getInstance());

        // assert
        String playlistExpected = "INSERT INTO TestMop.Playlist (PlaylistId, Name) VALUES (1, 'Test Playlist');";
        String playlistMovieExpected = "INSERT INTO TestMop.PlaylistMovie (PlaylistId, MovieId) VALUES (1, 2);";
        String academyAwardWinnerExpected = "INSERT INTO TestMop.AcademyAwardWinner (AcademyAwardWinnerId, MovieId) VALUES (5, 2);";
        String movieExpected = "INSERT INTO TestMop.Movie (MovieId, Title, FileLocation, Year, Director, Description, ImageLocation, Length, IsHighDefinition) VALUES (2, 'Test Movie', 'C:/TestFolder', '1999', 'Test Director', 'Test Description', 'C:/TestImageFolder', 120, 1);";

        assertEquals(4, result.length);

        String playlistResult = "", playlistMovieResult = "", academyAwardWinnerResult = "", movieResult = "";
        for (String str : result) {

            System.out.println(str);
            if (str.contains("TestMop.Playlist")) { playlistResult = str; }
            if (str.contains("TestMop.PlaylistMovie")) { playlistMovieResult = str; }
            if (str.contains("TestMop.AcademyAwardWinner")) { academyAwardWinnerResult = str; }
            if (str.contains("TestMop.Movie")) { movieResult = str; }
        }

        assertEquals(playlistExpected, playlistResult);
        assertEquals(playlistMovieExpected, playlistMovieResult);
        assertEquals(academyAwardWinnerExpected, academyAwardWinnerResult);
        assertEquals(movieExpected, movieResult);
    }

    @Test
    public void testGenerateDeleteScript() {

        // arrange
        mop.main.java.database.model.queryable.Movie movie = new mop.main.java.database.model.queryable.Movie();
        movie.setMovieId(5);

        // act
        String actualScript = sut.generateDeleteScript("Movie", movie.getMovieId(), cache);

        // assert
        String expectedScript = "DELETE FROM TestMop.Movie WHERE MovieId = 5";
        assertEquals(expectedScript, actualScript);
    }

    @Test
    public void testStringFormatValue() throws IllegalAccessException, InvocationTargetException {

        // arrange
        Attribute attribute = new Attribute<>("TestAttribute", String.class, "Test Title");

        // act
        String result = (String) formatValue.invoke(sut, attribute);

        // assert
        assertEquals("'Test Title'", result);
    }

    @Test
    public void testIntFormatValue() throws IllegalAccessException, InvocationTargetException {

        // arrange
        Attribute attribute = new Attribute<>("TestAttribute", int.class, 5);

        // act
        String result = (String) formatValue.invoke(sut, attribute);

        // assert
        assertEquals("5", result);
    }

    @Test
    public void testBooleanFormatValue() throws IllegalAccessException, InvocationTargetException {

        // arrange
        Attribute attribute = new Attribute<>("TestAttribute", boolean.class, true);

        // act
        String result = (String) formatValue.invoke(sut, attribute);

        // assert
        assertEquals("1", result);
    }
}
