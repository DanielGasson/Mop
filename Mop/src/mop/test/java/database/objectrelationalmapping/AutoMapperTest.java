package mop.test.java.database.objectrelationalmapping;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.AutoMapper;
import mop.main.java.database.objectrelationalmapping.helpers.Attribute;
import mop.main.java.database.objectrelationalmapping.helpers.Row;
import mop.main.java.database.model.queryable.Movie;
import mop.main.java.database.model.queryable.Genre;

public class AutoMapperTest {

    private AutoMapper mapper;
    private Row row1;
    private Row row2;

    @Before
    public void setup() {

        mapper = new AutoMapper();

        Attribute playlistId1 = new Attribute<>("PlaylistId", Integer.class, 2);
        Attribute movieId1 = new Attribute<>("MovieId", Integer.class, 8);
        Attribute title1 = new Attribute<>("Title", String.class, "Test Movie 1");
        Attribute year1 = new Attribute<>("Year", Short.class, (short) 1998);
        Attribute director1 = new Attribute<>("Director", String.class, "John Doe");
        Attribute length1 = new Attribute<>("Length", Integer.class, 134);
        Attribute description1 = new Attribute<>("Description", String.class, "This is a mop.test");
        Attribute imageLocation1 = new Attribute<>("ImageLocation", String.class, "C:\\Images\\Image1.jpg");
        Attribute fileLocation1 = new Attribute<>("FileLocation", String.class, "C:\\Movies\\Test Movie1.mp4");
        Attribute isHighDefinition1 = new Attribute<>("IsHighDefinition", Boolean.class, true);

        row1 = new Row();
        row1.addAttribute(1, playlistId1);
        row1.addAttribute(2, movieId1);
        row1.addAttribute(3, title1);
        row1.addAttribute(5, year1);
        row1.addAttribute(6, director1);
        row1.addAttribute(7, length1);
        row1.addAttribute(8, description1);
        row1.addAttribute(9, imageLocation1);
        row1.addAttribute(10, fileLocation1);
        row1.addAttribute(11, isHighDefinition1);

        Attribute playlistId2 = new Attribute<>("PlaylistId", Integer.class, 3);
        Attribute movieId2 = new Attribute<>("MovieId", Integer.class, 9);
        Attribute title2 = new Attribute<>("Title", String.class, "Test Movie 2");
        Attribute year2 = new Attribute<>("Year", Short.class, (short) 2000);
        Attribute director2 = new Attribute<>("Director", String.class, "Jane Doe");
        Attribute length2 = new Attribute<>("Length", Integer.class, 180);
        Attribute description2 = new Attribute<>("Description", String.class, "Another Test");
        Attribute imageLocation2 = new Attribute<>("ImageLocation", String.class, "C:\\Images\\Image2.jpg");
        Attribute fileLocation2 = new Attribute<>("FileLocation", String.class, "C:\\Movies\\Test Movie2.mp4");
        Attribute isHighDefinition2 = new Attribute<>("IsHighDefinition", Boolean.class, false);

        row2 = new Row();
        row2.addAttribute(1, playlistId2);
        row2.addAttribute(2, movieId2);
        row2.addAttribute(3, title2);
        row2.addAttribute(5, year2);
        row2.addAttribute(6, director2);
        row2.addAttribute(7, length2);
        row2.addAttribute(8, description2);
        row2.addAttribute(9, imageLocation2);
        row2.addAttribute(10, fileLocation2);
        row2.addAttribute(11, isHighDefinition2);
    }

    @After
    public void teardown() {

        mapper = null;
        row1 = null;
        row2 = null;
    }

    @Test
    public void testMapSingleMovieRow() {

        // act
        Movie movie = mapper.mapSingleDatabaseRow(Movie.class, row1);

        // assert
        assertEquals(2, movie.getPlaylistId());
        assertEquals(8, movie.getMovieId());
        assertEquals("Test Movie 1", movie.getTitle());
        assertEquals(1998, movie.getYear());
        assertEquals("John Doe", movie.getDirector());
        assertEquals(2, movie.getLength().getHours());
        assertEquals(14, movie.getLength().getMinutes());
        assertEquals("This is a mop.test", movie.getDescription());
        assertEquals("C:\\Images\\Image1.jpg", movie.getImageLocation());
        assertEquals("C:\\Movies\\Test Movie1.mp4", movie.getFileLocation());
        assertEquals(true, movie.getIsHighDefinition());
    }

    @Test
    public void testMapSingleGenreRow() {

        // arrange
        Row row = new Row();
        row.addAttribute(1, new Attribute<>("GenreId", Integer.class, 10));
        row.addAttribute(2, new Attribute<>("Name", String.class, "TestGenre"));

        // act
        Genre genre = mapper.mapSingleDatabaseRow(Genre.class, row);

        // assert
        assertEquals(10, genre.getGenreId());
        assertEquals("TestGenre", genre.getName());
    }

    @Test
    public void testMapMultipleMovieRows() {

        // arrange
        ArrayList<Row> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);

        // act
        List<Movie> movies = mapper.mapMultipleDatabaseRows(Movie.class, rows);

        // assert
        Movie movie1 = movies.get(0);
        assertEquals(2, movie1.getPlaylistId());
        assertEquals(8, movie1.getMovieId());
        assertEquals("Test Movie 1", movie1.getTitle());
        assertEquals(1998, movie1.getYear());
        assertEquals("John Doe", movie1.getDirector());
        assertEquals(2, movie1.getLength().getHours());
        assertEquals(14, movie1.getLength().getMinutes());
        assertEquals("This is a mop.test", movie1.getDescription());
        assertEquals("C:\\Images\\Image1.jpg", movie1.getImageLocation());
        assertEquals("C:\\Movies\\Test Movie1.mp4", movie1.getFileLocation());
        assertEquals(true, movie1.getIsHighDefinition());

        Movie movie2 = movies.get(1);
        assertEquals(3, movie2.getPlaylistId());
        assertEquals(9, movie2.getMovieId());
        assertEquals("Test Movie 2", movie2.getTitle());
        assertEquals(2000, movie2.getYear());
        assertEquals("Jane Doe", movie2.getDirector());
        assertEquals(3, movie2.getLength().getHours());
        assertEquals(0, movie2.getLength().getMinutes());
        assertEquals("Another Test", movie2.getDescription());
        assertEquals("C:\\Images\\Image2.jpg", movie2.getImageLocation());
        assertEquals("C:\\Movies\\Test Movie2.mp4", movie2.getFileLocation());
        assertEquals(false, movie2.getIsHighDefinition());
    }
}
