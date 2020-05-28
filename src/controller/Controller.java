package controller;

import gui.NavEvent;
import model.Bug;
import model.Database;
import model.PriorityCategory;

import java.util.List;

public class Controller {
    Database db = new Database();

    public List<Bug> getBugs() {
        List<Bug> list = db.getBugs();
        return list;
    }

    public void addBug(NavEvent e) {
        String project = e.getProject();
        int priorityCatId = e.getPriorityCat();
        String description = e.getDescription();

        PriorityCategory priorityCategory;

        if (priorityCatId == 0) {
            priorityCategory = PriorityCategory.low;
        }
        else if (priorityCatId == 1) {
            priorityCategory = PriorityCategory.medium;
        }
        else {
            priorityCategory = PriorityCategory.high;
        }

        Bug bug = new Bug(project, priorityCategory, description);
        db.addBug(bug);
    }
}
