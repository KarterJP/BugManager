package gui;

/**
 * Interface for bug form panel to communicate with other panels through MainFrame
 */
public interface BugFormListener {
    void submitOccurred(BugFormEvent bugFormEvent);
}
