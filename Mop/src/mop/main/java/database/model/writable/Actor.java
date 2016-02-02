package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;

public class Actor extends Writable {

    @Column(required = true)
    private int actorId;

    @Column(required = true)
    private String name;

    public Actor() {

    }

    private Actor(Builder builder) {

        this.actorId = builder.actorId;
        this.name = builder.name;
    }

    public static class Builder {

        private int actorId;
        private String name;

        public Builder actorId(int actorId) {

            this.actorId = actorId;
            return this;
        }

        public Builder name(String name) {

            this.name = name;
            return this;
        }

        public Actor build() {

            return new Actor(this);
        }
    }
}
