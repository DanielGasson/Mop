package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;

public class RelatedMovie extends Writable {

    @Column(required = true)
    private int relatedMovieId;

    @Column(required = true)
    private int movieId;

    @Column(required = true)
    private int relationId;

    public RelatedMovie() {

    }

    private RelatedMovie(Builder builder) {

        this.relatedMovieId = builder.relatedMovieId;
        this.movieId = builder.movieId;
        this.relationId = builder.relationId;
    }

    public static class Builder {

        private int relatedMovieId, movieId, relationId;

        public Builder relatedMovieId(int relatedMovieId) {

            this.relatedMovieId = relatedMovieId;
            return this;
        }

        public Builder movieId(int movieId) {

            this.movieId = movieId;
            return this;
        }

        public Builder relationId(int relationId) {

            this.relationId = relationId;
            return this;
        }

        public RelatedMovie build() {

            return new RelatedMovie(this);
        }
    }
}
