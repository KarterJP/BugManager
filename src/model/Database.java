package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Serves as reader and writer to and from the database
 */
public class Database {
    private ArrayList<Bug> bugs;

    public Database() {
        bugs = new ArrayList<Bug>();
    }

    /**
     * Stores Bug object in ArrayList
     * @param bug - Bug object
     */
    public void addBug(Bug bug) {
        bugs.add(bug);
    }

    /**
     * @return List<Bug> bugs
     */
    public List<Bug> getBugs() {
        return bugs;
    }

    /**
     * Turns arraylist of bugs into an array then writes the object to file
     * @param file - File object
     * @throws IOException - outputting to file
     */
    public void saveToFile(File file) throws IOException {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Bug[] bugsArr = bugs.toArray(new Bug[bugs.size()]);
            oos.writeObject(bugsArr);

            oos.close();
    }

    /**
     * Reads from file, stores data into array, then stores that array as a list
     * @param file - File object
     * @throws IOException - inputting from file
     */
    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Bug[] bugsArr = (Bug[]) ois.readObject();

            bugs.clear();
            bugs.addAll(Arrays.asList(bugsArr));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }
}
