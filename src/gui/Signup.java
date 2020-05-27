package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JDialog implements ActionListener {
    JLabel title, uLabel, eLabel, pLabel1, pLabel2;
    JTextField uText, eText;
    JPasswordField pText1, pText2;
    JButton registerBtn;
    // Constructor
    Signup() {
        // Frame
        setVisible(true);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setTitle("Registration");
        // Buttons, labels and text boxes
        title = new JLabel("Create a new account");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        uLabel = new JLabel("Username   ");
        eLabel = new JLabel("Email Address   ");
        pLabel1 = new JLabel("Create Password   ");
        pLabel2 = new JLabel("Confirm Password   ");
        uText = new JTextField(15);
        eText = new JTextField(15);
        pText1 = new JPasswordField(15);
        pText2 = new JPasswordField(15);
        registerBtn = new JButton("Register");
        // Text limits
        uText.setDocument(new TextFieldLimit(20));
        eText.setDocument(new TextFieldLimit(40));
        pText1.setDocument(new TextFieldLimit(40));
        pText2.setDocument(new TextFieldLimit(40));
        // Action listeners
        registerBtn.addActionListener(this);
        // Applying layout
        layoutComponents();
    }
    /**
     * Handler for buttons clicked
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        // If register button is clicked
        if (e.getSource() == registerBtn) {
            int x = 0;
            String s1 = uText.getText();
            String s2 = eText.getText();
            char[] s3 = pText1.getPassword();
            char[] s4 = pText2.getPassword();
            String s5 = new String(s3);
            String s6 = new String(s4);
            // If 'password' is the same as 'confirm password'
            if (s5.equals(s6)) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:BugManager","SYSTEM","Kjp939598");
                    PreparedStatement ps = con.prepareStatement("insert into USERS values(?,?,?)");
                    ps.setString(1, s1);
                    ps.setString(2, s5);
                    ps.setString(3, s2);
                    ps.executeQuery();
                    x++;
                    if (x > 0) {
                        JOptionPane.showMessageDialog(registerBtn, "Data Saved Successfully");
                        this.dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(registerBtn, "Error: " +ex);
                }
            } else {
                JOptionPane.showMessageDialog(registerBtn, "Password Does Not Match");
            }
        } else {
            uText.setText("");
            eText.setText("");
            pText1.setText("");
            pText2.setText("");
        }
    }
    /**
     * Formatting components within Registration
     */
    public void layoutComponents() {
        // Declaring variables
        GridBagConstraints gc = new GridBagConstraints();
        ////////////////////////////// FIRST ROW //////////////////////////////
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,50,0);
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridwidth = 2;
        // Adding title
        add(title, gc);
        ////////////////////////////// SECOND ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = .5;
        gc.gridwidth = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.LINE_END;
        // Adding username label
        add(uLabel, gc);
        //////// SECOND COLUMN ////////
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        // Adding username text field
        add(uText, gc);
        ////////////////////////////// THIRD ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        // Adding username label
        add(eLabel, gc);
        //////// SECOND COLUMN ////////
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        // Adding username text field
        add(eText, gc);
        ////////////////////////////// FOURTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        // Adding password label
        add(pLabel1, gc);
        //////// SECOND COLUMN ////////
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        // Adding password text field
        add(pText1, gc);
        ////////////////////////////// FIFTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        // Adding password label
        add(pLabel2, gc);
        //////// SECOND COLUMN ////////
        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_START;
        // Adding password text field
        add(pText2, gc);
        ////////////////////////////// SIXTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 5;
        gc.weighty = 1;
        gc.gridwidth = 2;
        gc.insets = new Insets(50,0,0,0);
        gc.fill = GridBagConstraints.BOTH;
        // Adding submit button
        add(registerBtn, gc);
    }
}