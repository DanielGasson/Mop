package mop.test.java.backend.utilities;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.backend.utilities.Length;

public class LengthTest {

    @Test
    public void testGetHours() {

        Length sut = new Length(2, 30);
        assertEquals(2, sut.getHours());
    }

    @Test
    public void testGetMinutes() {

        Length sut = new Length(2, 30);
        assertEquals(30, sut.getMinutes());
    }

    @Test
    public void testToString() {

        Length sut = new Length(2, 30);
        assertEquals("2:30", sut.toString());
    }
}
