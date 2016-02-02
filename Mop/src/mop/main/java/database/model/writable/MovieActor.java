package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;
import mop.main.java.database.objectrelationalmapping.helpers.Relation;
import mop.main.java.database.objectrelationalmapping.helpers.Table;

public class MovieActor extends Writable {

    @Column(required = true)
    private int movieId;

    @Column(required = true)
    private int actorId;

    @Relation(table = Table.ACTOR)
    private Actor actor;

    public MovieActor() {

    }

    private MovieActor(Builder builder) {

        this.movieId = builder.movieId;
        this.actorId = builder.actorId;
        this.actor = builder.actor;
    }

    public static class Builder {

        private int movieId, actorId;
        private Actor actor;

        public Builder movieId(int movieId) {

            this.movieId = movieId;
            return this;
        }

        public Builder actorId(int actorId) {

            this.actorId = actorId;
            return this;
        }

        public Builder actor(Actor actor) {

            this.actor = actor;
            return this;
        }

        public MovieActor build() {

            return new MovieActor(this);
        }
    }
}
