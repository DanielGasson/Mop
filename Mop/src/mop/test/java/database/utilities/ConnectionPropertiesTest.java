package mop.test.java.database.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import mop.main.java.database.utilities.ConnectionProperties;

public class ConnectionPropertiesTest {

    private ConnectionProperties sut1;

    @Before
    public void setup() {

        sut1 = new ConnectionProperties.Builder()
            .driverName("TestDriver")
            .password("TestPassword")
            .schema("TestSchema")
            .url("TestUrl")
            .userName("TestUser")
            .build();
    }

    @After
    public void teardown() {

        sut1 = null;
    }

    @Test
    public void testGetDriverName() {

        assertEquals("TestDriver", sut1.getDriverName());

        ConnectionProperties sut2 = new ConnectionProperties.Builder()
            .password("TestPassword")
            .schema("TestSchema")
            .url("TestUrl")
            .userName("TestUser")
            .build();

        assertNull(sut2.getDriverName());
    }

    @Test
    public void testGetUrl() {

        assertEquals("TestUrl", sut1.getUrl());

        ConnectionProperties sut2 = new ConnectionProperties.Builder()
            .driverName("TestDriver")
            .password("TestPassword")
            .schema("TestSchema")
            .userName("TestUser")
            .build();

        assertNull(sut2.getUrl());
    }

    @Test
    public void testGetUserName() {

        assertEquals("TestUser", sut1.getUserName());

        ConnectionProperties sut2 = new ConnectionProperties.Builder()
            .driverName("TestDriver")
            .password("TestPassword")
            .schema("TestSchema")
            .url("TestUrl")
            .build();

        assertNull(sut2.getUserName());
    }

    @Test
    public void testGetPassword() {

        assertEquals("TestPassword", sut1.getPassword());

        ConnectionProperties sut2 = new ConnectionProperties.Builder()
            .driverName("TestDriver")
            .schema("TestSchema")
            .url("TestUrl")
            .userName("TestUser")
            .build();

        assertNull(sut2.getPassword());
    }

    @Test
    public void testGetSchema() {

        assertEquals("TestSchema", sut1.getSchema());

        ConnectionProperties sut2 = new ConnectionProperties.Builder()
            .driverName("TestDriver")
            .password("TestPassword")
            .url("TestUrl")
            .userName("TestUser")
            .build();

        assertNull(sut2.getSchema());
    }

    @Test
    public void testAdditionalProperties() {

        assertEquals(0, sut1.getAllProperties().size());

        sut1.addProperty("TestKey1", "TestValue1");
        sut1.addProperty("TestKey2", "TestValue2");

        assertEquals(2, sut1.getAllProperties().size());
        assertEquals("TestValue1", sut1.getAllProperties().get("TestKey1"));
        assertEquals("TestValue2", sut1.getAllProperties().get("TestKey2"));
    }
}
