package mop.main.java.database.model.writable;

import mop.main.java.database.objectrelationalmapping.helpers.Column;

public class AcademyAwardWinner extends Writable {

    @Column(required = true)
    private int academyAwardWinnerId;

    @Column(required = true)
    private int movieId;

    public AcademyAwardWinner() {

    }

    private AcademyAwardWinner(Builder builder) {

        this.academyAwardWinnerId = builder.academyAwardWinnerId;
        this.movieId = builder.movieId;
    }

    public static class Builder {

        private int academyAwardWinnerId, movieId;

        public Builder academyAwardWinnerId(int academyAwardWinnerId) {

            this.academyAwardWinnerId = academyAwardWinnerId;
            return this;
        }

        public Builder movieId(int movieId) {

            this.movieId = movieId;
            return this;
        }

        public AcademyAwardWinner build() {

            return new AcademyAwardWinner(this);
        }
    }
}
