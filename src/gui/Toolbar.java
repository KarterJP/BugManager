package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Toolbar to be displayed at the top of the application
 */
public class Toolbar extends JPanel implements ActionListener {
    private JButton saveButton;
    private JButton loadButton;
    private ToolbarListener toolbarListener;
    // Constructor
    public Toolbar() {
        // Properties
        setBackground(new Color(230, 230, 230));
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        // Buttons
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        add(saveButton);
        add(loadButton);
        // Action listeners for buttons
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }
    /**
     * Setting up app listener interface on toolbar
     * @param listener - ToolbarListener object
     */
    public void setToolbarListener(ToolbarListener listener) {
        this.toolbarListener = listener;
    }
    /**
     * Action listener to operate on toolbar buttons
     * @param e - ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == saveButton) {
            if(toolbarListener != null) {
                toolbarListener.saveEventOccurred();
            }
        } else if (clicked == loadButton) {
            if(toolbarListener != null) {
                toolbarListener.loadEventOccurred();
            }
        }
    }
}