package gui;

/**
 * Small utility class
 * for returning a file extension String
 */
public class Utils {
    /**
     * Takes in a file name and returns its file extension
     * @param name - file name String
     * @return String
     */
    public static String getFileExtension(String name) {
        int pointIndex = name.lastIndexOf(".");

        if(pointIndex == -1) {
            name = null;
        } else if (pointIndex == name.length()-1) {
            name = null;
        } else {
            name = name.substring(pointIndex+1);
        }

        return name;
    }
}
