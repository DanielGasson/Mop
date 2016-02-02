package mop.main.java.backend.utilities;

public class Length {

    private final int hours;
    private final int minutes;

    public Length(int hours, int minutes) {

        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {

        return hours;
    }

    public int getMinutes() {

        return minutes;
    }

    @Override
    public String toString() {

        return hours + ":" + minutes;
    }
}
