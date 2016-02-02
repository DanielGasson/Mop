package mop.test.java.backend.utilities;

import org.junit.Test;
import static org.junit.Assert.*;

import mop.main.java.backend.utilities.Length;
import mop.main.java.backend.utilities.LengthConverter;

public class LengthConverterTest {

    @Test
    public void testConvertOneLengthValue() {

        Length length = LengthConverter.convertToHoursAndMinutes(133);
        assertEquals(2, length.getHours());
        assertEquals(13, length.getMinutes());
    }

    @Test
    public void testConvertValueLessThanAnHour() {

        Length length = LengthConverter.convertToHoursAndMinutes(59);
        assertEquals(0, length.getHours());
        assertEquals(59, length.getMinutes());
    }

    @Test
    public void testConvertValueExactlyOneHour() {

        Length length = LengthConverter.convertToHoursAndMinutes(60);
        assertEquals(1, length.getHours());
        assertEquals(0, length.getMinutes());
    }

    @Test
    public void testConvertMultipleLengthValues() {

        Length length1 = LengthConverter.convertToHoursAndMinutes(110);
        assertEquals(1, length1.getHours());
        assertEquals(50, length1.getMinutes());

        Length length2 = LengthConverter.convertToHoursAndMinutes(187);
        assertEquals(3, length2.getHours());
        assertEquals(7, length2.getMinutes());

        Length length3 = LengthConverter.convertToHoursAndMinutes(47);
        assertEquals(0, length3.getHours());
        assertEquals(47, length3.getMinutes());
    }
}
