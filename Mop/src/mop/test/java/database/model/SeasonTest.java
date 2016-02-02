package mop.test.java.database.model;

import java.time.Year;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import mop.main.java.database.model.queryable.Episode;
import mop.main.java.database.model.queryable.Season;

public class SeasonTest {

    @Test
    public void testSeasonId() {

        Season sut = new Season();
        sut.setSeasonId(3);
        assertEquals(3, sut.getSeasonId());
    }

    @Test
    public void testSeriesId() {

        Season sut = new Season();
        sut.setSeriesId(3);
        assertEquals(3, sut.getSeriesId());
    }

    @Test
    public void testSeasonNumber() {

        Season sut = new Season();
        sut.setSeasonNumber(3);
        assertEquals(3, sut.getSeasonNumber());
    }

    @Test
    public void testYear() {

        Season sut = new Season();
        Short value = 2001;
        Year year = Year.parse("2001");

        sut.setYear(value);
        assertEquals(year, sut.getYear());
    }

    @Test
    public void testEpisodes() {

        Season sut = new Season();

        Episode episode = new Episode();
        episode.setEpisodeNumber(5);

        sut.addEpisode(episode);
        assertEquals(episode, sut.getEpisode(5));

        sut.removeEpisode(episode);
        assertNull(sut.getEpisode(5));
    }
}
