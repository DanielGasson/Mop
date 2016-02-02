package mop.main.java.application;

import mop.main.java.backend.utilities.Log;
import mop.main.java.common.exceptions.StartupException;

import org.apache.logging.log4j.Logger;

class Main {

    private static final Logger log = Log.getLog(Main.class);

    public static void main(String[] args)
    {
        Thread.setDefaultUncaughtExceptionHandler(
            (Thread t, Throwable e) -> {

                log.fatal("Exception " + e + " in " + t + " caught in global exception handler.", e);

                // replace this with dialog box
                // display a dialog with the exception error message and that the app will quit
                // quit upon okay button
                System.exit(1);
            }
        );

        Bootstrap bootstrapper = new Bootstrap();
        try {

            bootstrapper.run();
        }
        catch (StartupException ex) {

            // display a dialog with the exception error message and that the app will quit
            // quit upon okay button
        }
    }
}
