package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * MainFrame frame of the application
 */
public class MainFrame extends JFrame{
    private Toolbar toolbar;
    private BugForm bugForm;
    private TablePanel tablePanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private PrefsDialog prefsDialog;
    private SignupDialog signupDialog;
    private LoginDialog loginDialog;
    private Preferences prefs;

    MainFrame() {
        // JFrame container window
        setTitle("Application");
        setMinimumSize(new Dimension(720, 480));
        setSize(1280, 720);
        setVisible(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Preferences
        prefs = Preferences.userRoot().node("db");
        // Creating controller
        controller = new Controller();
        // Menu bar
        setJMenuBar(createMenuBar());
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new BugFileFilter());
        // toolbar panel
        toolbar = new Toolbar();
        add(toolbar, BorderLayout.NORTH);
        loginDialog = new LoginDialog(this);
        signupDialog = new SignupDialog(this);
        toolbar.setToolbarListener(new ToolbarListener() {
            // Opens the existing user login form
            public void loginOpened() { loginDialog.setVisible(true); }
            // Opens the new user registration form
            public void signupOpened() {
                signupDialog.setVisible(true);
            }
        });
        // bugForm
        bugForm = new BugForm();
        add(bugForm, BorderLayout.WEST);
        bugForm.setBugFormListener(new BugFormListener() {
            public void submitOccurred(BugFormEvent e) {
                // Adds relevant text fields to data as bug object
                controller.addBug(e);
                tablePanel.refresh();
            }
        });
        // tablePanel
        tablePanel = new TablePanel();
        add(tablePanel, BorderLayout.CENTER);
        tablePanel.setData(controller.getBugs());
        tablePanel.setBugTableListener(new BugTableListener() {
            public void rowDeleted(int row){
                controller.removeBug(row);
            }
        });
        // prefsDialog
        prefsDialog = new PrefsDialog(this);
        prefsDialog.setPrefsListener(new PrefsListener() {
            public void preferencesSet(String user, String password, int port) {
                prefs.put("user", user);
                prefs.put("password", password);
                prefs.putInt("port", port);
            }
        });
        String user = prefs.get("user", "");
        String password = prefs.get("password", "");
        int port = prefs.getInt("port", 1521);
        prefsDialog.setDefaults(user, password, port);
    }
    /**
     * Used to create a JMenuBar
     * @return JMenuBar
     */
    private JMenuBar createMenuBar() {
        // menuBar //
        JMenuBar menuBar = new JMenuBar();
            // fileMenu //
            JMenu fileMenu = new JMenu("File");
                JMenuItem exportDataItem = new JMenuItem("Export Data...");
                JMenuItem importDataItem = new JMenuItem("Import Data...");
                JMenuItem exitItem = new JMenuItem("Exit");
                // Adding menu items to fileMenu
                fileMenu.add(exportDataItem);
                fileMenu.add(importDataItem);
                fileMenu.addSeparator(); ///// Separation
                fileMenu.add(exitItem);
            // windowMenu //
            JMenu viewMenu = new JMenu("View");
                // showMenu (windowMenu sub menu) //
                JMenuItem prefsItem = new JMenuItem("Preferences...");
                JMenu showMenu = new JMenu("Show");
                    JCheckBoxMenuItem showBugForm = new JCheckBoxMenuItem("Bug Form");
                    JCheckBoxMenuItem showToolbar = new JCheckBoxMenuItem("Toolbar");
                    showBugForm.setSelected(true);
                    showToolbar.setSelected(true);
                    // Adding menu items to showMenu
                    showMenu.add(showBugForm);
                    showMenu.add(showToolbar);
                // Adding submenus to windowMenu
                viewMenu.add(showMenu);
                viewMenu.add(prefsItem);
            // Adding menus to menuBar
            menuBar.add(fileMenu);
            menuBar.add(viewMenu);

        // Mnemonics
        fileMenu.setMnemonic(KeyEvent.VK_F);
        viewMenu.setMnemonic(KeyEvent.VK_V);
        exitItem.setMnemonic(KeyEvent.VK_X);
        // Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        ////// ACTION LISTENERS FOR MENU BAR //////
        prefsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });

        showBugForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();

                bugForm.setVisible(menuItem.isSelected());
            }
        });

        showToolbar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();

                toolbar.setVisible(menuItem.isSelected());
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Unable to load data from file.",
                                "Error importing data", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Unable to save data to file.",
                                "Error exporting data", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }
}
