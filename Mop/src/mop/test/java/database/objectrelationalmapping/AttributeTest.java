package mop.test.java.database.objectrelationalmapping;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.helpers.Attribute;

public class AttributeTest {

    @Test
    public void testGetNameWithUppercaseFirstCharacter() {

        Attribute sut = new Attribute<>("Title", String.class, "Test Title");
        assertEquals("Title", sut.getName());
    }

    @Test
    public void testGetNameWithLowercaseFirstCharacter() {

        Attribute sut = new Attribute<>("title", String.class, "Test Title");
        assertEquals("Title", sut.getName());
    }

    @Test
    public void testGetType() {

        Attribute sut = new Attribute<>("Title", String.class, "Test Title");
        assertEquals(String.class, sut.getType());
    }

    @Test
    public void testGetValue() {

        Attribute sut = new Attribute<>("Title", String.class, "Test Title");
        assertEquals("Test Title", sut.getValue());
    }
}
