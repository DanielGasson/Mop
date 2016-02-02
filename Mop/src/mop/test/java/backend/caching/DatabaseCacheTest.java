package mop.test.java.backend.caching;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.backend.caching.DatabaseCache;

public class DatabaseCacheTest {

    @Test
    public void testSetAndGet() {

        DatabaseCache sut = DatabaseCache.getInstance();
        sut.set("TestString", "TestStringValue");

        assertEquals("TestStringValue", sut.get("TestString"));
    }
}
