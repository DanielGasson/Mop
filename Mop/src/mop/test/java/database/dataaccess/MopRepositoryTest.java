package mop.test.java.database.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mop.test.java.TestBase;

import mop.main.java.database.dataaccess.MopRepository;
import mop.main.java.database.dataaccess.Repository;
import mop.main.java.database.model.queryable.Movie;
import mop.main.java.database.model.queryable.Genre;
import mop.main.java.database.utilities.ConnectionFactory;
import mop.main.java.database.utilities.ConnectionProperties;
import mop.main.java.database.utilities.SqlConnectionFactory;

import static org.junit.Assert.*;

public class MopRepositoryTest extends TestBase {

    private Repository sut;
    private ConnectionProperties properties;

    @Before
    public void setup() {

        sut = new MopRepository(new SqlConnectionFactory());

        properties = new ConnectionProperties.Builder()
            .driverName("com.mysql.jdbc.Driver")
            .url("jdbc:mysql://localhost/mysql")
            .userName("root")
            .password("")
            .schema("TestMop")
            .build();

        properties.addProperty("yearIsDateType", "false");
    }

    @After
    public void teardown() {

        sut = null;
        properties = null;
    }

    @Test
    public void testGet() throws SQLException {

        // act
        Movie movie = sut.get(1, Movie.class, properties);
        Genre genre = sut.get(10, Genre.class, properties);

        // assert
        assertEquals(1, movie.getMovieId());
        assertEquals("10 Things I Hate About You", movie.getTitle());
        assertEquals(1999, movie.getYear());
        assertEquals(10, genre.getGenreId());
        assertEquals("Romance", genre.getName());
    }

    @Test
    public void testGetReturnsNullWhenRecordDoesNotExist() throws SQLException, ClassNotFoundException {

        // arrange
        String getMaxId = "SELECT MAX(MovieId) AS MaxMovieId FROM TestMop.Movie;";
        ConnectionFactory connectionFactory = new SqlConnectionFactory();
        Connection connection = connectionFactory.openConnection(properties);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getMaxId);
        if(resultSet.next()) {

            int movieId = resultSet.getInt("MaxMovieId");

            // act
            Movie movie = sut.get(movieId + 1, Movie.class, properties);

            // assert
            assertNull(movie);
        }
        else {

            fail("Unable to set up mop.test data.");
        }
    }

    @Test
    public void testSave() throws SQLException, ClassNotFoundException {

        // arrange
        String getMaxId = "SELECT MAX(GenreId) AS MaxGenreId FROM TestMop.Genre;";
        ConnectionFactory connectionFactory = new SqlConnectionFactory();
        Connection connection = connectionFactory.openConnection(properties);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getMaxId);
        if(resultSet.next()) {

            int genreId = resultSet.getInt("MaxGenreId") + 1;
            mop.main.java.database.model.writable.Genre genre = new mop.main.java.database.model.writable.Genre.Builder().genreId(genreId).name("TestGenre").build();

            // act
            boolean saveResult = sut.save(genre, properties);

            // assert
            assertTrue(saveResult);

            Genre result = sut.get(genreId, Genre.class, properties);
            assertEquals("TestGenre", result.getName());
            assertEquals(genreId, result.getGenreId());

            sut.delete(genre, properties);
        }
        else {

            fail("Unable to set up mop.test data.");
        }
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testUpdate() throws SQLException {

        sut.update(new mop.main.java.database.model.writable.Movie(), properties);
    }

    @Test
    public void testDelete() {

        fail();
    }
}
