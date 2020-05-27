package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Toolbar to be displayed at the top of the application
 */
public class Toolbar extends JPanel implements ActionListener {
    private JButton signup;
    private JButton login;
    private ToolbarListener toolbarListener;
    // Constructor
    public Toolbar() {
        // Properties
        setBackground(new Color(230, 230, 230));
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        // Buttons
        signup = new JButton("Signup");
        login = new JButton("Login");
        add(signup);
        add(login);
        // Action listeners for buttons
        signup.addActionListener(this);
        login.addActionListener(this);
    }
    /**
     * Setting up app listener interface on toolbar
     * @param listener
     */
    public void setToolbarListener(ToolbarListener listener) {
        this.toolbarListener = listener;
    }
    /**
     * Action listener to operate on toolbar buttons
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == login) {
            if(toolbarListener != null) {
                toolbarListener.loginOpened();
            }
        } else if (clicked == signup) {
            if(toolbarListener != null) {
                toolbarListener.signupOpened();
            }
        }
    }
}