package controller;

import gui.BugFormEvent;
import model.Bug;
import model.Database;
import model.PriorityCategory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Communication medium from the gui to the model
 */
public class Controller {
    Database db = new Database();

    public List<Bug> getBugs() {
        return db.getBugs();
    }

    /**
     * Takes bug form submission event, separates the field data, stores into new Bug object,
     * then sends that Bug object to the addBug method in Database.java
     * @param e - NavEvent object
     */
    public void addBug(BugFormEvent e) {
        String project = e.getProject();
        int priorityCatId = e.getPriorityCat();
        String description = e.getDescription();

        PriorityCategory priorityCategory;

        if (priorityCatId == 0) {
            priorityCategory = PriorityCategory.low;
        }
        else if (priorityCatId == 1) {
            priorityCategory = PriorityCategory.medium;
        }
        else {
            priorityCategory = PriorityCategory.high;
        }

        Bug bug = new Bug(project, priorityCategory, description);
        db.addBug(bug);
    }

    /**
     * Removes bug data from table
     * @param index - int index of row in data
     */
    public void removeBug(int index) {
        db.removeBug(index);
    }
    /**
     * Stores data into a file
     * @param file - File object
     * @throws IOException - outputting to file
     */
   public void saveToFile(File file) throws IOException {
        db.saveToFile(file);
   }
    /**
     * Retrieves data from a file
     * @param file - File object
     * @throws IOException - outputting to file
     */
   public void loadFromFile(File file) throws IOException {
        db.loadFromFile(file);
   }
}
