package mop.test.java.database.utilities;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import mop.main.java.database.utilities.ScriptUtils;

public class ScriptUtilsTest {

    @Test
    public void TestSqlScriptReturnsTrue() {

        String script = "Test Script.sql";
        assertTrue(ScriptUtils.IsSqlScript(script));
    }

    @Test
    public void TestSqlScriptWithExtraPeriodReturnsTrue() {

        String script = "Test.Script.sql";
        assertTrue(ScriptUtils.IsSqlScript(script));
    }

    @Test
    public void TestNonSqlScriptReturnsFalse() {

        String script = "Test Script.txt";
        assertFalse(ScriptUtils.IsSqlScript(script));
    }

    @Test
    public void TestScriptWithNoExtensionReturnsFalse() {

        String script = "Test Scriptsql";
        assertFalse(ScriptUtils.IsSqlScript(script));
    }
}
