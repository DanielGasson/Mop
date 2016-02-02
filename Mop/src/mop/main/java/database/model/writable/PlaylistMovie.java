package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;
import mop.main.java.database.objectrelationalmapping.helpers.Relation;
import mop.main.java.database.objectrelationalmapping.helpers.Table;

public class PlaylistMovie extends Writable {

    @Column(required = true)
    private int playlistId;

    @Column(required = true)
    private int movieId;

    @Relation(table = Table.PLAYLIST)
    private Playlist playlist;

    public PlaylistMovie() {

    }

    private PlaylistMovie(Builder builder) {

        this.playlistId = builder.playlistId;
        this.movieId = builder.movieId;
        this.playlist = builder.playlist;
    }

    public static class Builder {

        private int playlistId, movieId;
        private Playlist playlist;

        public Builder playlistId(int playlistId) {

            this.playlistId = playlistId;
            return this;
        }

        public Builder movieId(int movieId) {

            this.movieId = movieId;
            return this;
        }

        public Builder playlist(Playlist playlist) {

            this.playlist = playlist;
            return this;
        }

        public PlaylistMovie build() {

            return new PlaylistMovie(this);
        }
    }
}
