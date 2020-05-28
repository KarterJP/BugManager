package gui;

import model.Bug;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BugTableModel extends AbstractTableModel {
    private List<Bug> db;
    private String[] colNames = {"ID", "Project Name", "Priority Level", "Description"};

    public BugTableModel() {
    }

    public void setData(List<Bug> db) {
        this.db = db;
    }

    @Override
    public String getColumnName(int column) {
        String h = colNames[column];
        return h;
    }

    @Override
    public int getRowCount() {
        int i = db.size();
        return i;
    }

    @Override
    public int getColumnCount() {
        int j = 4;
        return j;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Bug bug = db.get(rowIndex);
        Object k = null;
        switch(columnIndex) {
            case 0:
                k = bug.getId();
                break;
            case 1:
                k = bug.getProject();
                break;
            case 2:
                k = bug.getPriorityCat();
                break;
            case 3:
                k = bug.getDescription();
        }
        return k;
    }
}