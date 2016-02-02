package mop.main.java.database.utilities;

public class ScriptUtils {

    public static boolean IsSqlScript(String pathToScript) {

        int scriptLength = pathToScript.length();
        return pathToScript.substring(scriptLength - 4, scriptLength).equals(".sql");
    }
}
