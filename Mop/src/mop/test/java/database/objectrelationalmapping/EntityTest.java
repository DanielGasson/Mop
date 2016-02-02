package mop.test.java.database.objectrelationalmapping;

import java.util.ArrayList;

import mop.main.java.database.objectrelationalmapping.helpers.Table;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.helpers.Attribute;
import mop.main.java.database.objectrelationalmapping.helpers.Entity;

public class EntityTest {

    @Test
    public void testGetTableName() {

        Entity sut = new Entity(Table.MOVIE);
        assertEquals(Table.MOVIE, sut.getTableName());
    }

    @Test
    public void testSetAndGetAttributes() {

        Entity sut = new Entity(Table.MOVIE);
        sut.addAttribute(new Attribute<>("Title", String.class, "Test Movie"));

        ArrayList<Attribute> attributes =  sut.getAttributes();

        assertEquals(1, attributes.size());
        assertEquals("Test Movie", attributes.get(0).getValue());
    }
}
