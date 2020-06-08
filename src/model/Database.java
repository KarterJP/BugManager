package model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Serves as reader and writer to and from the database
 */
public class Database {
    private List<Bug> bugs;
    private Connection con;

    public Database() {
        bugs = new ArrayList<Bug>();
    }

    /**
     * Establishes a connection with the database
     */
    public void connect() throws Exception {
        if (con == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                throw new Exception("Driver not found");
            }

            String url = "jdbc:oracle:thin:@localhost:1521:BugManager";
            con = DriverManager.getConnection(url, "SYSTEM", "Kjp939598");
            System.out.println("Connected: " + con);
        }
    }

    /**
     * Releases the connection with the database
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                System.out.println("Cannot close connection");
            }
        }
    }

    /**
     * Saves data to database
     * or updates data if already existing
     * @throws SQLException
     */
    public void save() throws SQLException {
        String checkSql = "select count(*) as count from BUGS where BUG_ID=?";

        String insertSql = "insert into BUGS (BUG_ID, PROJECT_NAME, DESCRIPTION, PRIORITY_LEVEL) values (?, ?, ?, ?)";

        String updateSql = "update BUGS set PROJECT_NAME=?, DESCRIPTION=?, PRIORITY_LEVEL=? where BUG_ID=?";

        PreparedStatement checkStatement = con.prepareStatement(checkSql);
        PreparedStatement insertStatement = con.prepareStatement(insertSql);
        PreparedStatement updateStatement = con.prepareStatement(updateSql);

        for (Bug bug : bugs) {
            int id = bug.getId();
            String project = bug.getProject();
            PriorityCategory priority = bug.getPriorityCat();
            String description = bug.getDescription();

            checkStatement.setInt(1, id);

            ResultSet checkResult = checkStatement.executeQuery();
            checkResult.next();

            int count = checkResult.getInt(1);

            if (count == 0) {
                System.out.println("Inserting bug with id " + id);

                insertStatement.setInt(1, id);
                insertStatement.setString(2, project);
                insertStatement.setString(3, description);
                insertStatement.setString(4, priority.toString());

                insertStatement.executeUpdate();
            } else {
                System.out.println("Updating bug with id " + id);

                updateStatement.setString(1, project);
                updateStatement.setString(2, description);
                updateStatement.setString(3, priority.toString());
                updateStatement.setInt(4, id);

                updateStatement.executeUpdate();
            }
        }
        checkStatement.close();
        insertStatement.close();
        updateStatement.close();
    }

    public void load() throws SQLException {
        bugs.clear();

        String sql = "select BUG_ID, PROJECT_NAME, DESCRIPTION, PRIORITY_LEVEL from BUGS order by PROJECT_NAME";
        Statement selectStatement = con.createStatement();



        ResultSet results = selectStatement.executeQuery(sql);

        while (results.next()) {
            int id = results.getInt("BUG_ID");
            String project = results.getString("PROJECT_NAME");
            String description = results.getString("DESCRIPTION");
            String priority = results.getString("PRIORITY_LEVEL");

            Bug bug = new Bug(id, project, PriorityCategory.valueOf(priority), description);
            bugs.add(bug);
        }

        selectStatement.close();
        results.close();
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
        return Collections.unmodifiableList(bugs);
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

    /**
     * Removes all data related to the Bug object at index
     * @param index - int index of row in data
     */
    public void removeBug(int index) {
        bugs.remove(index);
    }
}
