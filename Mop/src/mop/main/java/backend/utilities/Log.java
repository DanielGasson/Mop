package mop.main.java.backend.utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log {

    private static final String MAC_LOGGING_PATH = "/Mop Logs";
    private static final String WINDOWS_LOGGING_PATH = "C://Mop Logs/";

    public static Logger getLog(Class<?> className) {

        // Check what operating system the application is running on, to set the path of the log file.
        final String operatingSystem = System.getProperty("os.name");
        final String osLogPath = operatingSystem.equals("Mac OS X")
            ? MAC_LOGGING_PATH
            : WINDOWS_LOGGING_PATH;

        //Configure logger
        System.setProperty("logFilePath", osLogPath); // Set log properties before instantiating the logger

        return LogManager.getLogger(className.toString());
    }

    private Log() {

    }
}
