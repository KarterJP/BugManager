package gui;

import javax.swing.*;
/**
 * MAIN CLASS created 14 May, 2020
 * @author Karter Phillips (KarterJP <KarterJP@Gmail.com>)
 */
public class App extends JFrame{
    /**
     * MAIN METHOD
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Window();
            }
        });
    }
}
