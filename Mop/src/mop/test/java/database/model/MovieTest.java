package mop.test.java.database.model;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import mop.main.java.database.model.queryable.Movie;
import mop.main.java.database.model.queryable.Genre;
import mop.main.java.database.model.queryable.Actor;

public class MovieTest {

    @Test
    public void testPlaylistId() {

        Movie sut = new Movie();
        sut.setPlaylistId(2);
        assertEquals(2, sut.getPlaylistId());
    }

    @Test
    public void testMovieId() {

        Movie sut = new Movie();
        sut.setMovieId(26);
        assertEquals(26, sut.getMovieId());
    }

    @Test
    public void testTitle() {

        Movie sut = new Movie();
        sut.setTitle("Test Title");
        assertEquals("Test Title", sut.getTitle());
    }

    @Test
    public void testYear() {

        Movie sut = new Movie();
        sut.setYear((short) 2001);
        assertEquals(2001, sut.getYear());
    }

    @Test
    public void testDirector() {

        Movie sut = new Movie();
        sut.setDirector("Richard Kelly");
        assertEquals("Richard Kelly", sut.getDirector());
    }

    @Test
    public void testLength() {

        Movie sut = new Movie();
        sut.setLength(137);
        assertEquals(2, sut.getLength().getHours());
        assertEquals(17, sut.getLength().getMinutes());
    }

    @Test
    public void testDescription() {

        Movie sut = new Movie();
        sut.setDescription("My favourite movie.");
        assertEquals("My favourite movie.", sut.getDescription());
    }

    @Test
    public void testImageLocation() {

        Movie sut = new Movie();
        sut.setImageLocation("C:\\FakeFolder/Images");
        assertEquals("C:\\FakeFolder/Images", sut.getImageLocation());
    }

    @Test
    public void testFileLocation() {

        Movie sut = new Movie();
        sut.setFileLocation("C:\\FakeFolder/Movies");
        assertEquals("C:\\FakeFolder/Movies", sut.getFileLocation());
    }

    @Test
    public void testIsHighDefinition() {

        Movie sut = new Movie();
        sut.setIsHighDefinition(true);
        assertTrue(sut.getIsHighDefinition());
    }

    @Test
    public void testGenres() {

        Movie sut = new Movie();

        Genre genre1 = new Genre();
        genre1.setName("genre1");
        Genre genre2 = new Genre();
        genre2.setName("genre2");

        sut.addGenre(genre1);
        sut.addGenre(genre2);

        ArrayList<Genre> result = sut.getGenres();

        assertEquals("genre1", result.get(0).getName());
        assertEquals("genre2", result.get(1).getName());
    }

    @Test
    public void testActors() {

        Movie sut = new Movie();

        Actor actor1 = new Actor();
        actor1.setName("actor1");
        Actor actor2 = new Actor();
        actor2.setName("actor2");

        sut.addActor(actor1);
        sut.addActor(actor2);

        ArrayList<Actor> result = sut.getActors();

        assertEquals("actor1", result.get(0).getName());
        assertEquals("actor2", result.get(1).getName());
    }

    @Test
    public void testRelatedMovieIds() {

        Movie sut = new Movie();

        sut.addRelatedMovieId(1);
        sut.addRelatedMovieId(2);

        ArrayList<Integer> result = sut.getRelatedMovieIds();

        assertEquals(1, (int) result.get(0));
        assertEquals(2, (int) result.get(1));
    }
}
