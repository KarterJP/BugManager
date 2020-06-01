package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    private JTextField userField;
    private JPasswordField passField;
    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent) {
        // setup
        super(parent, "Preferences", true);
        setLocationRelativeTo(parent);
        setSize(400,300);
        setLayout(new GridBagLayout());
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerModel = new SpinnerNumberModel(1521, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        userField = new JTextField(10);
        passField = new JPasswordField(10);

        ////////////////////////////// FIRST ROW //////////////////////////////
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;
        add(new JLabel("User: "), gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        add(userField, gc);
        ////////////////////////////// SECOND ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        add(new JLabel("Password: "), gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        add(passField, gc);
        ////////////////////////////// THIRD ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        add(okButton, gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        add(cancelButton, gc);
        ////////////////////////////// FOURTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        add(new JLabel("Port: "), gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        add(portSpinner, gc);
        ////////////////////////////// FIFTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        add(okButton, gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        add(cancelButton, gc);
        ////////////////////////////// END //////////////////////////////

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                char[] password = passField.getPassword();
                Integer port = (Integer) portSpinner.getValue();

                if (prefsListener != null) {
                    prefsListener.preferencesSet(user, new String(password), port);
                }

                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public void setDefaults(String user, String password, int port) {
        userField.setText(user);
        passField.setText(password);
        portSpinner.setValue(port);
    }

    /**
     * Sets listener for prefsDialog
     * @param prefsListener - Preferences listener
     */
    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }
}
