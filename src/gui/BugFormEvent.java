package gui;

import java.util.EventObject;

/**
 * Serves as creator of EventObjects for the BugPanel
 */
public class BugFormEvent extends EventObject {
    private String project;
    private int priorityCat;
    private String description;
    /**
     * @param source - event source
     */
    public BugFormEvent(Object source) {
        super(source);
    }
    /**
     * @param source - event source
     * @param project - project name
     * @param priorityCat - priority category
     * @param description - description of bug
     */
    public BugFormEvent(Object source, String project, int priorityCat, String description) {
        super(source);

        this.project = project;
        this.priorityCat = priorityCat;
        this.description = description;
    }
    // Getters and setters
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getPriorityCat() {
        return priorityCat;
    }

    public void setPriorityCat(int priorityCat) {
        this.priorityCat = priorityCat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}