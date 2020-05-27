package gui;

public class Utils {
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
