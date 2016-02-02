package mop.test.java.database.objectrelationalmapping;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.helpers.Attribute;
import mop.main.java.database.objectrelationalmapping.helpers.Row;

public class RowTest {

    @Test
    public void testAddAttributeAndGetRowDetails() {

        Row sut = new Row();
        Attribute attribute1 = new Attribute<>("MovieId", int.class, 3);
        Attribute attribute2 = new Attribute<>("Title", String.class, "Test Title");

        sut.addAttribute(1, attribute1);
        sut.addAttribute(2, attribute2);

        Map<Integer, Attribute> row = sut.getRowDetails();

        Attribute attr1 = row.get(1);
        Attribute attr2 = row.get(2);

        assertEquals("MovieId", attr1.getName());
        assertEquals(int.class, attr1.getType());
        assertEquals(3, attr1.getValue());

        assertEquals("Title", attr2.getName());
        assertEquals(String.class, attr2.getType());
        assertEquals("Test Title", attr2.getValue());
    }
}
