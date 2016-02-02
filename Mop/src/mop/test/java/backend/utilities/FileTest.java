package mop.test.java.backend.utilities;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FileTest {

    @Test
    public void testFileExistsReturnsTrue() {

        boolean fileExists = mop.main.java.backend.utilities.File.exists("src/mop/test/resources/ReadonlyTestConfig.xml");
        assertTrue(fileExists);
    }

    @Test
    public void testFileExistsReturnsFalse() {

        boolean fileExists = mop.main.java.backend.utilities.File.exists("src/mop/test/resources/UnknownFile.xml");
        assertFalse(fileExists);
    }

    @Test
    public void testTryGetExistingFileReturnsFile() {

        File file = mop.main.java.backend.utilities.File.tryGet("src/mop/test/resources/ReadonlyTestConfig.xml");
        assertNotNull(file);
    }

    @Test
    public void testTryGetInvalidFilePathReturnsNull() {

        File file = mop.main.java.backend.utilities.File.tryGet("src/mop/test/resource/UnknownFile.xml");
        assertNull(file);
    }
}
