package mop.test.java.database.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.model.queryable.Genre;

public class GenreTest {

    @Test
    public void testGenreName() {

        Genre sut = new Genre();
        sut.setName("testGenre");
        assertEquals("testGenre", sut.getName());
    }

    @Test
    public void testGenreId() {

        Genre sut = new Genre();
        sut.setGenreId(2);
        assertEquals(2, sut.getGenreId());
    }
}
