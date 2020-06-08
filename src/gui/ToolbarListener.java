package gui;

/**
 * Interface for toolbar panel to communicate with other panels through MainFrame
 */
public interface ToolbarListener {
    void saveEventOccurred();

    void loadEventOccurred();
}