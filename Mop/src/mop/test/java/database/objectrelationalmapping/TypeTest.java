package mop.test.java.database.objectrelationalmapping;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.objectrelationalmapping.helpers.Type;

public class TypeTest {

    @Test
    public void testStringReturnsString() {

        Class<Object> type = Type.get("java.lang.String");
        assertEquals(java.lang.String.class, type);
    }

    @Test
    public void testIntegerReturnsInteger() {

        Class<Object> type = Type.get("java.lang.Integer");
        assertEquals(java.lang.Integer.class, type);
    }

    @Test
    public void testBooleanReturnsBoolean() {

        Class<Object> type = Type.get("java.lang.String");
        assertEquals(java.lang.String.class, type);
    }

    @Test
    public void testShortReturnsShort() {

        Class<Object> type = Type.get("java.lang.Short");
        assertEquals(java.lang.Short.class, type);
    }

    @Test
    public void testTimeReturnsDate() {

        Class<Object> type = Type.get("java.sql.Time");
        assertEquals(java.util.Date.class, type);
    }

    @Test
    public void testDateReturnsDate() {

        Class<Object> type = Type.get("java.util.Date");
        assertEquals(java.util.Date.class, type);
    }

    @Test
    public void testNoMatchReturnsNull() {

        Class<Object> type = Type.get("java.lang.ThisWillNotMatch");
        assertEquals(null, type);
    }
}
