package gui;

import javax.swing.*;
import javax.swing.border.Border;
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
        setSize(300,250);
        setLayout(new GridBagLayout());
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerModel = new SpinnerNumberModel(1521, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        userField = new JTextField(10);
        passField = new JPasswordField(10);

        layoutControls();

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

    private void layoutControls() {
        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        int space = 10;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");

        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        controlsPanel.setLayout(new GridBagLayout());

        Insets rightPadding = new Insets(0,0,0,10);
        Insets noPadding = new Insets(0,0,0,10);
        ////////////////////////////// FIRST ROW //////////////////////////////
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0;
        gc.weighty = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("User: "), gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(userField, gc);
        ////////////////////////////// SECOND ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Password: "), gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(passField, gc);
        ////////////////////////////// THIRD ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Port: "), gc);
        //// SECOND COLUMN ////
        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(portSpinner, gc);
        ////////////////////////////// BUTTONS PANEL //////////////////////////////
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);
        ////////////////////////////// END //////////////////////////////
        // Add sub panels to dialog
        setLayout(new BorderLayout());
        add(buttonsPanel, BorderLayout.SOUTH);
        add(controlsPanel, BorderLayout.CENTER);
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
