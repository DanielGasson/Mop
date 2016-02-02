package mop.main.java.database.model.writable;

import mop.main.java.backend.utilities.Log;
import mop.main.java.database.objectrelationalmapping.helpers.Column;
import mop.main.java.database.objectrelationalmapping.helpers.Relation;
import mop.main.java.database.objectrelationalmapping.helpers.Table;

import org.apache.logging.log4j.Logger;

public class Movie extends Writable {

    private static final Logger log = Log.getLog(Movie.class);

    @Column(required = true)
    private int movieId;

    @Column(required = true)
    private String title, fileLocation;

    @Column(required = false)
    private String year, director, description, imageLocation;

    @Column(required = false)
    private int length;

    @Column(required = false)
    private boolean isHighDefinition;

    @Relation(table = Table.ACADEMYAWARDWINNER)
    private AcademyAwardWinner academyAwardWinner;

    @Relation(table = Table.PLAYLISTMOVIE)
    private PlaylistMovie playlistMovie;

    @Relation(table = Table.ACTOR)
    private MovieActor[] movieActors;

    @Relation(table = Table.MOVIEGENRE)
    private MovieGenre[] movieGenres;

    @Relation(table = Table.RELATEDMOVIE)
    private RelatedMovie[] relatedMovies;

    public Movie() {

    }

    private Movie(Builder builder) {

        this.movieId = builder.movieId;
        this.title = builder.title;
        this.year = builder.year;
        this.director = builder.director;
        this.description = builder.description;
        this.imageLocation = builder.imageLocation;
        this.fileLocation = builder.fileLocation;
        this.length = builder.length;
        this.isHighDefinition = builder.isHighDefinition;
        this.playlistMovie = builder.playlistMovie;
        this.academyAwardWinner = builder.academyAwardWinner;
        this.movieActors = builder.movieActors;
        this.movieGenres = builder.movieGenres;
        this.relatedMovies = builder.relatedMovies;
    }

    public static class Builder {

        private int movieId, length;
        private String title, year, director, description, imageLocation, fileLocation;
        private boolean isHighDefinition;
        private PlaylistMovie playlistMovie;
        private AcademyAwardWinner academyAwardWinner;
        private MovieActor[] movieActors;
        private MovieGenre[] movieGenres;
        private RelatedMovie[] relatedMovies;

        public Builder movieId(int movieId) {

            this.movieId = movieId;
            return this;
        }

        public Builder title(String title) {

            this.title = title;
            return this;
        }

        public Builder year(String year) {

            this.year = year;
            return this;
        }

        public Builder director(String director) {

            this.director = director;
            return this;
        }

        public Builder description(String description) {

            this.description = description;
            return this;
        }

        public Builder fileLocation(String fileLocation) {

            this.fileLocation = fileLocation;
            return this;
        }

        public Builder imageLocation(String imageLocation) {

            this.imageLocation = imageLocation;
            return this;
        }

        public Builder length(int length) {

            this.length = length;
            return this;
        }

        public Builder isHighDefinition(boolean isHighDefinition) {

            this.isHighDefinition = isHighDefinition;
            return this;
        }

        public Builder playlistMovie(PlaylistMovie playlistMovie) {

            this.playlistMovie = playlistMovie;
            return this;
        }

        public Builder academyAwardWinner(AcademyAwardWinner academyAwardWinner) {

            this.academyAwardWinner = academyAwardWinner;
            return this;
        }

        public Builder movieActors(MovieActor[] movieActors) {

            this.movieActors = movieActors;
            return this;
        }

        public Builder movieGenres(MovieGenre[] movieGenres) {

            this.movieGenres = movieGenres;
            return this;
        }

        public Builder relatedMovies(RelatedMovie[] relatedMovies) {

            this.relatedMovies = relatedMovies;
            return this;
        }

        public Movie build() {

            return new Movie(this);
        }
    }
}
