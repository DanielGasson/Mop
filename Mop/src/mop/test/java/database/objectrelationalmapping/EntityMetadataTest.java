package mop.test.java.database.objectrelationalmapping;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.helpers.Attribute;
import mop.main.java.database.objectrelationalmapping.helpers.Entity;
import mop.main.java.database.objectrelationalmapping.helpers.EntityMetadata;
import mop.main.java.database.objectrelationalmapping.helpers.Table;

public class EntityMetadataTest {

    @Test
    public void testSetAndGetBaseTable() {

        EntityMetadata sut = new EntityMetadata();
        sut.setBaseTable(Table.MOVIE);

        assertEquals(Table.MOVIE, sut.getBaseTable());
    }

    @Test
    public void testSetAndGetEntities() {

        Attribute attribute1 = new Attribute<>("Title", String.class, "Test Movie");
        Attribute attribute2 = new Attribute<>("MovieId", int.class, 2);
        Entity entity = new Entity(Table.MOVIE);
        entity.addAttribute(attribute1);
        entity.addAttribute(attribute2);
        EntityMetadata sut = new EntityMetadata();
        sut.setBaseTable(Table.MOVIE);
        sut.addEntity(entity);

        ArrayList<Entity> result = sut.getEntities();

        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getAttributes().size());
    }
}
