package mop.main.java.backend.utilities;

public class File {

    /**
     * Checks if a file exists at a url.
     * @param url - the url to check.
     * @return true if a file was found, and it isn't a directory.
     */
    public static boolean exists(String url) {

        java.io.File file = new java.io.File(url);
        return file.exists() && !file.isDirectory();
    }

    /**
     * Gets a file from a url.
     * @param url - the url of the file to fetch.
     * @return a file object or null if no file exists for the url.
     */
    public static java.io.File tryGet(String url) {

        java.io.File file = new java.io.File(url);
        if(file.exists() && !file.isDirectory()) {

            return file;
        }

        return null;
    }
}
