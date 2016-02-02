package mop.test.java.database.objectrelationalmapping;

import java.lang.reflect.Field;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.helpers.EntityFields;

public class EntityFieldsTest {

    @Test
    public void testRelationFields() {

        EntityFields sut =  new EntityFields();
        Field field = PowerMock.createMock(Field.class);

        sut.addColumnField(field);

        assertEquals(1, sut.getColumnFields().size());
        assertEquals(field, sut.getColumnFields().get(0));
    }

    @Test
    public void testColumnFields() {

        EntityFields sut =  new EntityFields();
        Field field = PowerMock.createMock(Field.class);

        sut.addRelationField(field);

        assertEquals(1, sut.getRelationFields().size());
        assertEquals(field, sut.getRelationFields().get(0));
    }
}
