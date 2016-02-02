package mop.test.java.database.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import mop.main.java.database.model.queryable.Episode;

public class EpisodeTest {

    @Test
    public void testEpisodeId() {

        Episode sut = new Episode();
        sut.setEpisodeId(7);
        assertEquals(7, sut.getEpisodeId());
    }

    @Test
    public void testEpisodeNumber() {

        Episode sut = new Episode();
        sut.setEpisodeNumber(9);
        assertEquals(9, sut.getEpisodeNumber());
    }

    @Test
    public void testName() {

        Episode sut = new Episode();
        sut.setName("mop.test episode");
        assertEquals("mop.test episode", sut.getName());
    }

    @Test
    public void testFileLocation() {

        Episode sut = new Episode();
        sut.setFileLocation("C://mop.test");
        assertEquals("C://mop.test", sut.getFileLocation());
    }
}
