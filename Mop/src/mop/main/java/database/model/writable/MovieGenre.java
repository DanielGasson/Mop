package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;
import mop.main.java.database.objectrelationalmapping.helpers.Relation;
import mop.main.java.database.objectrelationalmapping.helpers.Table;

public class MovieGenre extends Writable {

    @Column(required = true)
    private int movieId;

    @Column(required = true)
    private int genreId;

    @Relation(table = Table.GENRE)
    private Genre genre;

    public MovieGenre() {

    }

    private MovieGenre(Builder builder) {

        this.movieId = builder.movieId;
        this.genreId = builder.genreId;
        this.genre = builder.genre;
    }

    public static class Builder {

        private int movieId, genreId;
        private Genre genre;

        public Builder movieId(int movieId) {

            this.movieId = movieId;
            return this;
        }

        public Builder genreId(int genreId) {

            this.genreId = genreId;
            return this;
        }

        public Builder genre(Genre genre) {

            this.genre = genre;
            return this;
        }

        public MovieGenre build() {

            return new MovieGenre(this);
        }
    }
}
