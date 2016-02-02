package mop.main.java.database.objectrelationalmapping.helpers;

/**
 * Enumeration of Mop database tables
 */
public enum Table {

    ACADEMYAWARDWINNER("AcademyAwardWinner"),
    ACTOR("Actor"),
    DOCUMENTARY("Documentary"),
    DOCUMENTARYEPISODE("DocumentaryEpisode"),
    GENRE("Genre"),
    MOVIE("Movie"),
    MOVIEACTOR("MovieActor"),
    MOVIEGENRE("MovieGenre"),
    PLAYLISTDOCUMENTARY("PlaylistDocumentary"),
    PLAYLISTMOVIE("PlaylistMovie"),
    PLAYLISTSERIES("PlaylistSeries"),
    PLAYLIST("Playlist"),
    SERIES("Series"),
    SEASON("Season"),
    SEASONEPISODE("SeasonEpisode"),
    RELATEDMOVIE("RelatedMovie");

    private final String string;

    Table(String string) {

        this.string = string;
    }

    @Override
    public String toString() {

        return string;
    }
}
