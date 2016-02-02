package mop.test.java.backend.configuration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.backend.configuration.AppConfig;

public class AppConfigTest {

    private AppConfig config1;
    private AppConfig config2;
    private AppConfig config3;

    @Before
    public void setup() {

        config1 = new AppConfig.Builder()
            .username("user")
            .password("password")
            .id("2")
            .name("default")
            .build();

        config2 = new AppConfig.Builder()
            .username(null)
            .password(null)
            .id(null)
            .name(null)
            .build();

        config3 = new AppConfig.Builder().build();
    }

    @After
    public void teardown() {

        config1 = null;
        config2 = null;
        config3 = null;
    }

    @Test
    public void testGetUsername() {

        assertEquals("user", config1.getUsername());
    }

    @Test
    public void testGetPassword() {

        assertEquals("password", config1.getPassword());
    }

    @Test
    public void testGetId() {

        assertEquals(2, config1.getId());
    }

    @Test
    public void testGetIdFromStringInitialisation() {

        AppConfig sut = new AppConfig.Builder().id("3").build();
        assertEquals(3, sut.getId());
    }

    @Test
    public void testGetName() {

        assertEquals("default", config1.getName());
    }

    @Test
    public void testNullUsernameReturnsEmptyString() {

        assertEquals("", config2.getUsername());
        assertEquals("", config3.getUsername());
    }

    @Test
    public void testNullPasswordReturnsEmptyString() {

        assertEquals("", config2.getPassword());
        assertEquals("", config3.getPassword());
    }

    @Test
    public void testNullIdReturnsZero() {

        assertEquals(0, config2.getId());
        assertEquals(0, config3.getId());
    }

    @Test
    public void testInvalidStringIdReturnsZero() {

        AppConfig sut = new AppConfig.Builder().id("number").build();
        assertEquals(0, sut.getId());
    }

    @Test
    public void testBuildIdNullStringReturnsZero() {

        AppConfig sut = new AppConfig.Builder().id(null).build();
        assertEquals(0, sut.getId());
    }

    @Test
    public void testNullNameReturnsEmptyString() {

        assertEquals("", config2.getName());
        assertEquals("", config3.getName());
    }
}
