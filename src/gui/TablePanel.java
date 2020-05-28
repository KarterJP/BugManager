package gui;

import model.Bug;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Presents data in table format to the user
 */
public class TablePanel extends JPanel {
    private JTable table;
    private BugTableModel bugTableModel;

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

    /**
     * Sends list of Bug objects to BugTableModel
     * @param db - list of Bug objects
     */
    public void setData(List<Bug> db) {
        bugTableModel.setData(db);
    }

    /**
     * Updates the users table when data is submitted
     */
    public void refresh() {
        bugTableModel.fireTableDataChanged();
    }
}
