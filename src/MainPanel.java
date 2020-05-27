import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainPanel extends JPanel {
    private JPanel bug;
    private JScrollPane scroll;
    private JTextArea notepad;
    // Constructor
    MainPanel() {
        setBackground(new Color(160, 160, 160, 150));
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        // Adding text area to MainPanel
        notepad = new JTextArea();
        scroll = new JScrollPane(notepad);
        notepad.setRows(8);
        add(scroll, BorderLayout.SOUTH);
    }
    /**
     * Adding text to the end of the MainPanel text area
     * @param project
     * @param description
     */
    public void appendText(String project, int priorityCat, String description) {
        project = project.trim();
        description = description.trim();

        notepad.append(project.toUpperCase() + ": " + description+": "+priorityCat+"\n");
    }

    public void appendText(File fileChooser) {
        String file = fileChooser.toString();
        notepad.append(file+"\n");
    }
    /**
     * Creates panel to be displayed at the top of MainPanel
     * @param project
     * @param description
     */
    public void addBugPanel(String project, String description) {
        // Creating labels
        JLabel projectLabel = new JLabel(project);
        JLabel descriptionLabel = new JLabel(description);
        // Adding panel object to MainPanel
        bug = new JPanel();
        add(bug, BorderLayout.NORTH);
        bug.setLayout(new GridBagLayout());
        bug.setBackground(new Color(100,140,170,210));
        ////////////////////////////// BEGINNING OF FORMATTING //////////////////////////////
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        bug.add(projectLabel);
        // SECOND COLUMN
        gc.gridx = 1;
        gc.gridy = 0;
        bug.add(descriptionLabel);
        ////////////////////////////// END OF FORMATTING //////////////////////////////
    }
}
