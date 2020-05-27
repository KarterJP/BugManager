import java.util.EventObject;

public class NavEvent extends EventObject {
    private String project;
    private int priorityCat;
    private String description;
    /**
     * Constructor
     * @param source
     */
    public NavEvent(Object source) {
        super(source);
    }
    /**
     * Constructor
     * @param source
     * @param project
     * @param priorityCat
     * @param description
     */
    public NavEvent(Object source, String project, int priorityCat, String description) {
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