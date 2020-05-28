package gui;

import model.Bug;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {
    private JPanel bug;
    private JScrollPane scroll;
    private JTextArea notepad;
    private JTable table;
    private BugTableModel bugTableModel;
    // Constructor
    public TablePanel() {
        setBackground(new Color(160, 160, 160, 150));
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new BorderLayout());
        // Creating BugTableModel
        bugTableModel = new BugTableModel();
        // Adding table to TablePanel
        table = new JTable(bugTableModel);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Bug> db) {
        bugTableModel.setData(db);
    }

    public void refresh() {
        bugTableModel.fireTableDataChanged();
    }
    /**
     * Creates panel to be displayed at the top of TablePanel
     * @param project
     * @param description
     *
    public void addBugPanel(String project, String description) {
        // Creating labels
        JLabel projectLabel = new JLabel(project);
        JLabel descriptionLabel = new JLabel(description);
        // Adding panel object to TablePanel
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
    */
}
