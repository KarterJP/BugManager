package gui;

import model.Bug;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Presents data in table format to the user
 */
public class TablePanel extends JPanel {
    private JTable table;
    private BugTableModel bugTableModel;
    private JPopupMenu tablePopup;
    private BugTableListener bugTableListener;

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
        // Adding tablePopup menu to table
        tablePopup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        tablePopup.add(removeItem);
        // Setting mouse listener
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int x = e.getX();
                int y = e.getY();

                table.getSelectionModel().setSelectionInterval(row, row);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    tablePopup.show(table, x, y);
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (bugTableListener != null) {
                    bugTableListener.rowDeleted(row);
                    bugTableModel.fireTableRowsDeleted(row, row);
                }
            }
        });
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

    /**
     * Listens for actions in the table
     * @param listener - BugTableListener
     */
    public void setBugTableListener(BugTableListener listener) {
        this.bugTableListener = listener;
    }
}
