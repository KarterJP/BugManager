import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Main window of the application
 */
public class Window extends JFrame{
    private Toolbar toolbar;
    private NavPanel navPanel;
    private MainPanel mainPanel;
    private JFileChooser fileChooser;
    // Constructor
    Window() {
        // JFrame container window
        setTitle("Application");
        setMinimumSize(new Dimension(720, 480));
        setSize(1280, 720);
        setVisible(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Menu bar
        setJMenuBar(createMenuBar());
        fileChooser = new JFileChooser();
        // toolbar panel
        toolbar = new Toolbar();
        add(toolbar, BorderLayout.NORTH);
        toolbar.setToolbarListener(new ToolbarListener() {
            // Opens the existing user login form
            public void loginOpened() { new Login(); }
            // Opens the new user registration form
            public void signupOpened() {
                new Signup();
            }
        });
        // navPanel
        navPanel = new NavPanel();
        add(navPanel, BorderLayout.WEST);
        navPanel.setNavListener(new NavListener() {
            public void submitOccurred(NavEvent e) {
                String project = e.getProject();
                int priorityCat = e.getPriorityCat();
                String description = e.getDescription();

                mainPanel.appendText(project, priorityCat, description);
            }
            public void addBug(NavEvent e) {
            //    mainPanel.addBugPanel(project, description);
            }
        });
        // mainPanel
        mainPanel = new MainPanel();
        add(mainPanel, BorderLayout.CENTER);
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
            JMenu windowMenu = new JMenu("Window");
                // showMenu (windowMenu sub menu) //
                JMenu showMenu = new JMenu("Show");
                    JCheckBoxMenuItem showNavPanel = new JCheckBoxMenuItem("Bug Form");
                    JCheckBoxMenuItem showToolbar = new JCheckBoxMenuItem("Toolbar");
                    showNavPanel.setSelected(true);
                    showToolbar.setSelected(true);
                // Adding menu items to showMenu
                showMenu.add(showNavPanel);
                showMenu.add(showToolbar);
            // Adding submenus to windowMenu
            windowMenu.add(showMenu);
        // Adding menus to menuBar
        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        // Action listeners
        showNavPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();

                navPanel.setVisible(menuItem.isSelected());
            }
        });
        showToolbar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();

                toolbar.setVisible(menuItem.isSelected());
            }
        });
        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(fileChooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
                    mainPanel.appendText(fileChooser.getSelectedFile());
                }
            }
        });
        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(fileChooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
                    mainPanel.appendText(fileChooser.getSelectedFile());
                }
            }
        });
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int action = JOptionPane.showConfirmDialog(Window.this,
                        "Do you really want to exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }

            }
        });

        // Mnemonics
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        // Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        return menuBar;
    }
}
