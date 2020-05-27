package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;
/**
 * File filter for file dialog box
 */
public class BugFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        String extension = Utils.getFileExtension(name);
        boolean bool = false;

        if(extension == null) {
            bool = false;
        } else if(extension == "bug") {
            bool = true;
        }

        if(file.isDirectory()) {
            bool = true;
        }

        return bool;
    }

    @Override
    public String getDescription() {
        return "Bug database files (*.bug)";
    }
}
