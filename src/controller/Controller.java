package controller;

import gui.BugFormEvent;
import model.Bug;
import model.Database;
import model.PriorityCategory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Communication medium from the gui to the model
 */
public class Controller {
    Database db = new Database();

    public List<Bug> getBugs() {
        return db.getBugs();
    }

    public void save() throws SQLException {
        db.save();
    }

    public void load() throws SQLException {
        db.load();
    }

    /**
     * Attempts to establish connection with database
     */
    public void connect() throws Exception {
        db.connect();
    }
    /**
     * Disconnects from database
     */
    public void disconnect() {
        db.disconnect();
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
            priorityCategory = PriorityCategory.Low;
        }
        else if (priorityCatId == 1) {
            priorityCategory = PriorityCategory.Medium;
        }
        else {
            priorityCategory = PriorityCategory.High;
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
