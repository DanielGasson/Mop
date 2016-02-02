package mop.main.java.backend.utilities;

public class LengthConverter {

    /**
     * Gets the length in hours and minutes
     * @param totalMinutes, the length in minutes
     * @return a Length object containing the length in hours and minutes
     */
    public static Length convertToHoursAndMinutes(int totalMinutes) {

        return convertToHoursAndMinutes(totalMinutes, 0);
    }

    private static Length convertToHoursAndMinutes(int totalMinutes, int temp) {

        if(totalMinutes > 59) {

            totalMinutes -= 60;
            temp ++;

            return convertToHoursAndMinutes(totalMinutes, temp);
        }

        int hours = temp;

        return new Length(hours, totalMinutes);
    }
}
