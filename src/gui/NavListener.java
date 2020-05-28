package gui;

/**
 * Interface for navigation panel to communicate with other panels through the 'Window' class
 */
public interface NavListener {
    void submitOccurred(NavEvent navEvent);
}
