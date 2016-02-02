package mop.test.java.database.objectrelationalmapping;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.helpers.Table;

public class TableTest {

    @Test
    public void testToString() {

        assertEquals("AcademyAwardWinner", Table.ACADEMYAWARDWINNER.toString());
        assertEquals("Actor", Table.ACTOR.toString());
        assertEquals("Documentary", Table.DOCUMENTARY.toString());
        assertEquals("DocumentaryEpisode", Table.DOCUMENTARYEPISODE.toString());
        assertEquals("Genre", Table.GENRE.toString());
        assertEquals("Movie", Table.MOVIE.toString());
        assertEquals("MovieActor", Table.MOVIEACTOR.toString());
        assertEquals("MovieGenre", Table.MOVIEGENRE.toString());
        assertEquals("PlaylistDocumentary", Table.PLAYLISTDOCUMENTARY.toString());
        assertEquals("PlaylistMovie", Table.PLAYLISTMOVIE.toString());
        assertEquals("PlaylistSeries", Table.PLAYLISTSERIES.toString());
        assertEquals("Playlist", Table.PLAYLIST.toString());
        assertEquals("Series", Table.SERIES.toString());
        assertEquals("Season", Table.SEASON.toString());
        assertEquals("SeasonEpisode", Table.SEASONEPISODE.toString());
        assertEquals("RelatedMovie", Table.RELATEDMOVIE.toString());
    }
}
