package model;

import java.io.Serializable;

/**
 * Instantiates bug objects
 */
public class Bug implements Serializable {
    private static int count = 0;
    private int id;
    private String project;
    private PriorityCategory priorityCat;
    private String description;

    public Bug(String project, PriorityCategory priorityCat, String description) {
        this.project = project;
        this.priorityCat = priorityCat;
        this.description = description;
        this.id = count;
        count++;
    }

    public Bug(int id, String project, PriorityCategory priorityCat, String description) {
        this(project, priorityCat, description);

        this.id = id;
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public PriorityCategory getPriorityCat() {
        return priorityCat;
    }

    public void setPriorityCat(PriorityCategory priorityCat) {
        this.priorityCat = priorityCat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
