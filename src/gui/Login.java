package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog popup for existing users to login to an account
 */
public class Login extends JDialog implements ActionListener {
    JLabel title, uLabel, pLabel;
    JTextField uText;
    JButton loginBtn;
    JPasswordField pText;

    Login() {
        // Frame setup
        setVisible(true);
        setSize(250, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setTitle("Login");
        // Buttons, labels and text boxes
        title = new JLabel("Login");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        uLabel = new JLabel("Username   ");
        pLabel = new JLabel("Password   ");
        uText = new JTextField(15);
        pText = new JPasswordField(15);
        loginBtn = new JButton("Login");
        // Text limits
        uText.setDocument(new TextFieldLimit(20));
        pText.setDocument(new TextFieldLimit(40));
        // Action listeners
        loginBtn.addActionListener(this);
        // Applying layout
        layoutComponents();
    }

    /**
     * Handler for login button
     * @param e - ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        // If login button is clicked
        if (e.getSource() == loginBtn) {
            int x = 0;
            String s1 = uText.getText();
            char[] s2 = pText.getPassword();
            String s3 = new String(s2);

                try {
                    /*Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:BugManager","SYSTEM","Kjp939598");
                    PreparedStatement ps = con.prepareStatement("SELECT username, pass FROM USERS WHERE username = '"+s1+"' AND pass = '"+s3+"'");
                    ResultSet rs = ps.executeQuery();

                    if (!rs.wasNull()) {*/
                        JOptionPane.showMessageDialog(loginBtn, "Login successful");
                        this.dispose();
                    /*} else {
                        JOptionPane.showMessageDialog(loginBtn, "Username or password invalid");
                    }*/
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(loginBtn, "Error: " +ex);
                }

        } else {
            uText.setText("");
            pText.setText("");
        }
    }
    /**
     * Arranging components within Login
     */
    public void layoutComponents() {
        // Declaring variables
        GridBagConstraints gc = new GridBagConstraints();
        ////////////////////////////// FIRST ROW //////////////////////////////
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridwidth = 2;
        add(title, gc);
        ////////////////////////////// SECOND ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(uLabel, gc);
        //////// SECOND COLUMN ////////
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(uText, gc);
        ////////////////////////////// THIRD ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(pLabel, gc);
        //////// SECOND COLUMN ////////
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(pText, gc);
        ////////////////////////////// FOURTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        add(loginBtn, gc);
    }
}
