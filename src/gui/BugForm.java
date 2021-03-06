package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Panel that appears on the left of the screen (used to add a bug)
 */
public class BugForm extends JPanel implements ActionListener{
    private JLabel projectNameLabel, priorityLabel, descriptionLabel;
    private JTextField projectNameField;
    private JTextArea descriptionArea;
    private JButton submit, clear;
    private JList priorityList;
    private BugFormListener bugFormListener;
    // Constructor
    public BugForm() {
        // Properties
        setBackground(new Color(230, 230, 230));
        setLayout(new GridBagLayout());
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(60, 60, 60, 180)),"Add a new bug");
        Border outerBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        // Buttons, labels and text
        projectNameLabel = new JLabel("Project Name:");
        priorityLabel = new JLabel("Priority Level:");
        descriptionLabel = new JLabel("Description:");
        projectNameField = new JTextField(10);
        projectNameField.setDocument(new TextFieldLimit(30));
        priorityList = new JList();
        descriptionArea = new JTextArea(10,10);
        descriptionArea.setDocument(new TextFieldLimit(300));
        submit = new JButton("Submit");
        clear = new JButton("Clear");
        // Attaching labels to components
        projectNameLabel.setLabelFor(projectNameField);
        priorityLabel.setLabelFor(priorityList);
        descriptionLabel.setLabelFor(descriptionArea);
        // Set up mnemonics
        projectNameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        priorityLabel.setDisplayedMnemonic(KeyEvent.VK_P);
        descriptionLabel.setDisplayedMnemonic(KeyEvent.VK_D);
        submit.setMnemonic(KeyEvent.VK_O);
        // Priority list items
        DefaultListModel priorityModel = new DefaultListModel();
        priorityModel.addElement(new PriorityCategory(0,"Low"));
        priorityModel.addElement(new PriorityCategory(1,"Medium"));
        priorityModel.addElement(new PriorityCategory(2,"High"));
        priorityList.setModel(priorityModel);
        priorityList.setPreferredSize(new Dimension(110, 80));
        priorityList.setBorder(BorderFactory.createEtchedBorder());
        priorityList.setSelectedIndex(0);
        //////////////////////////////////
        // Action listeners for buttons
        submit.addActionListener(this);
        clear.addActionListener(this);
        // Applying layout
        layoutComponents();
    }

    /**
     * Setting up listener for bug form
     * @param bugFormListener - bug form listener
     */
    public void setBugFormListener(BugFormListener bugFormListener) {
        this.bugFormListener = bugFormListener;
    }
    /**
     * Button handlers for submit and clear
     * @param e - ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == submit) {
            if (bugFormListener != null) {
                String project = projectNameField.getText();
                String description = descriptionArea.getText();
                PriorityCategory priorityCat = (PriorityCategory)priorityList.getSelectedValue();
                int id = priorityCat.getID();

                BugFormEvent bugFormEvent = new BugFormEvent(this, project, id, description);

                bugFormListener.submitOccurred(bugFormEvent);
                // Removing text from description only
                descriptionArea.setText("");
            }
        } else if (clicked == clear) {
            if (bugFormListener != null) {
                // Removing text from all fields
                clearText();
            }
        }
    }
    /**
     * Clears text from every text field within BugForm
     */
    public void clearText() {
        projectNameField.setText("");
        descriptionArea.setText("");
    }
    /**
     * Arranging components within BugForm
     */
    public void layoutComponents() {
        // Declaring variables
        Insets noInsets = new Insets(0,0,0,0);
        Insets rightInset = new Insets(0,0,0,7);
        GridBagConstraints gc = new GridBagConstraints();
        ////////////////////////////// FIRST ROW //////////////////////////////
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(projectNameLabel, gc);
        //// Second column ////
        gc.insets = noInsets;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(projectNameField, gc);
        ////////////////////////////// SECOND ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        add(priorityLabel, gc);
        ////////////////////////////// THIRD ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = noInsets;
        add(priorityList, gc);
        ////////////////////////////// FOURTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        add(descriptionLabel, gc);
        ////////////////////////////// FIFTH ROW //////////////////////////////
        gc.insets = noInsets;
        gc.gridx = 0;
        gc.gridy++;
        gc.weighty = 0.1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(descriptionArea), gc);
        ////////////////////////////// SIXTH ROW //////////////////////////////
        gc.gridx = 0;
        gc.gridy++;
        gc.weighty = 1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.fill = GridBagConstraints.NONE;
        add(submit, gc);
        //// Second column ////
        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(clear, gc);
    }
}
/**
 * Small utility class
 * for assigning IDs to strings in the priority list
 */
class PriorityCategory {
    private int id;
    private String value;

    PriorityCategory(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public int getID() {
        return id;
    }
}
