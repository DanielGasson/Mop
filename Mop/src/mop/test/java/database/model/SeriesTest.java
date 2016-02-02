package mop.test.java.database.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mop.main.java.database.model.queryable.Season;
import mop.main.java.database.model.queryable.Series;

import static org.junit.Assert.*;

public class SeriesTest {

    private Series sut;

    @Before
    public void setup() {

        sut = new Series();
    }

    @After
    public void teardown() {

        sut = null;
    }

    @Test
    public void testSeriesId() {

        sut.setSeriesId(5);
        assertEquals(5, sut.getSeriesId());
    }

    @Test
    public void testSeriesName() {

        sut.setSeriesName("Test Series");
        assertEquals("Test Series", sut.getSeriesName());
    }

    @Test
    public void testNumSeasons() {

        sut.setNumSeasons(10);
        assertEquals(10, sut.getNumSeasons());
    }

    @Test
    public void testImageLocation() {

        sut.setImageLocation("C://FakeFolder/DeletedTVShows");
        assertEquals("C://FakeFolder/DeletedTVShows", sut.getImageLocation());
    }

    @Test
    public void testAddSeason() {

        Season season = new Season();
        season.setSeasonId(2);

        assertEquals(0, sut.getSeasons().size());

        boolean added = sut.addSeason(season);

        assertTrue(added);
        assertEquals(1, sut.getSeasons().size());
        assertTrue(sut.getSeasons().contains(season));
    }

    @Test
    public void testRemoveSeason() {

        Season season = new Season();
        season.setSeasonId(2);
        sut.addSeason(season);

        assertTrue(sut.getSeasons().contains(season));

        sut.removeSeason(season);

        assertFalse(sut.getSeasons().contains(season));
        assertEquals(0, sut.getSeasons().size());
    }
}
