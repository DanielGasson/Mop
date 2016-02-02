package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;

public class Genre extends Writable {

    @Column(required = true)
    private int genreId;

    @Column(required = true)
    private String name;

    public Genre() {

    }

    private Genre(Builder builder) {

        this.genreId = builder.genreId;
        this.name = builder.name;
    }

    public static class Builder {

        private int genreId;
        private String name;

        public Builder genreId(int genreId) {

            this.genreId = genreId;
            return this;
        }

        public Builder name(String name) {

            this.name = name;
            return this;
        }

        public Genre build() {

            return new Genre(this);
        }
    }
}
