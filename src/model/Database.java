package model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private ArrayList<Bug> bugs;

    Database() {
        bugs = new ArrayList<Bug>();
    }

    public void addBug(Bug bug) {
        bugs.add(bug);
    }

    public List<Bug> getBugs() {
        return bugs;
    }
}
