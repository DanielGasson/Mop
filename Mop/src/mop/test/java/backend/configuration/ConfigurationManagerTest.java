package mop.test.java.backend.configuration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import mop.main.java.backend.configuration.AppConfig;
import mop.main.java.backend.configuration.ConfigurationManager;
import mop.main.java.backend.utilities.File;
import mop.main.java.common.Constants;
import mop.main.java.common.exceptions.XmlException;

public class ConfigurationManagerTest {

    private static final String testConfigUrl = "src/mop/test/resources/WritableTestConfig.xml";
    private static final String temporaryConfigUrl = "src/mop/test/resources/TemporaryTestConfig.xml";
    private static final String invalidConfigUrl = "TestAppConfig.xml";

    @Before
    public void setup() throws XmlException {

        if(!File.exists(testConfigUrl)) {

            ConfigurationManager configurationManager = new ConfigurationManager();
            configurationManager.createConfiguration(testConfigUrl);
        }

        if(File.exists(temporaryConfigUrl)) {

            java.io.File file = new java.io.File(temporaryConfigUrl);
            file.delete();
        }
    }

    @After
    public void teardown() {

        java.io.File testFile1 = new java.io.File(temporaryConfigUrl);
        testFile1.delete();

        java.io.File testFile2 = new java.io.File(testConfigUrl);
        testFile2.delete();
    }

    @Test
    public void testCreateEmptyConfigurationReturnsTrueWhenSuccessful() throws XmlException {

        ConfigurationManager sut = new ConfigurationManager();
        boolean result = sut.createConfiguration(temporaryConfigUrl);
        assertTrue(result);
    }

    @Test
    public void testCreateConfigurationReturnsFalseWhenFileExists() throws XmlException {

        ConfigurationManager sut = new ConfigurationManager();
        boolean result = sut.createConfiguration(testConfigUrl);
        assertFalse(result);
    }

    @Test
    public void testGetAllConfigValues() throws XmlException {

        ConfigurationManager sut = new ConfigurationManager();
        sut.createConfiguration(temporaryConfigUrl);

        assertTrue(sut.setValue(Constants.AppConfigSections.defaultSchema, "UnitTestMop", temporaryConfigUrl));
        assertTrue(sut.setValue(Constants.AppConfigSections.username, "testUser", temporaryConfigUrl));
        assertTrue(sut.setValue(Constants.AppConfigSections.password, "testPassword", temporaryConfigUrl));
        assertTrue(sut.setValue(Constants.AppConfigSections.name, "testPlaylist", temporaryConfigUrl));
        assertTrue(sut.setValue(Constants.AppConfigSections.id, "2", temporaryConfigUrl));

        AppConfig result = sut.getAllValues(temporaryConfigUrl);

        assertEquals("UnitTestMop", result.getDefaultSchema());
        assertEquals("testUser", result.getUsername());
        assertEquals("testPassword", result.getPassword());
        assertEquals("testPlaylist", result.getName());
        assertEquals(2, result.getId());
    }

    @Test
    public void testSetValueReturnsTrueWhenSuccessful() throws XmlException {

        ConfigurationManager sut = new ConfigurationManager();
        boolean result = sut.setValue("username", "mop/test", testConfigUrl);
        assertTrue(result);
    }

    @Test
    public void testSetValueReturnsFalseIfConfigFileDoesNotExist() throws XmlException {

        ConfigurationManager sut = new ConfigurationManager();
        boolean result = sut.setValue("username", "mop/test", invalidConfigUrl);
        assertFalse(result);
    }

    @Test
    public void testSetValueReturnsFalseIfConfigKeyInvalid() throws XmlException {

        ConfigurationManager sut = new ConfigurationManager();
        boolean result = sut.setValue("invalidKey", "mop/test", testConfigUrl);
        assertFalse(result);
    }
}
