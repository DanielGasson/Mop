package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;

public class Playlist extends Writable{

    @Column(required = true)
    private int playlistId;

    @Column(required = true)
    private String name;

    public Playlist() {

    }

    private Playlist(Builder builder) {

        this.playlistId = builder.playlistId;
        this.name = builder.name;
    }

    public static class Builder {

        private int playlistId;
        private String name;

        public Builder playlistId(int playlistId) {

            this.playlistId = playlistId;
            return this;
        }

        public Builder name(String name) {

            this.name = name;
            return this;
        }

        public Playlist build() {

            return new Playlist(this);
        }
    }
}
