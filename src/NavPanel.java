import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Panel that appears on the left of the screen (used to add a bug)
 */
public class NavPanel extends JPanel implements ActionListener{
    private JLabel projectNameLabel, priorityLabel, descriptionLabel;
    private JTextField projectNameField;
    private JTextArea descriptionArea;
    private JButton submit, clear;
    private JList priorityList;
    private NavListener navListener;
    // Constructor
    public NavPanel() {
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
        priorityList = new JList();
        descriptionArea = new JTextArea(10,10);
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
     * Setting navigation listener
     * @param listener
     */
    public void setNavListener(NavListener listener) {
        this.navListener = listener;
    }
    /**
     * Button handlers for submit and clear
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if (clicked == submit) {
            if (navListener != null) {
                String project = projectNameField.getText();
                String description = descriptionArea.getText();
                PriorityCategory priorityCat = (PriorityCategory)priorityList.getSelectedValue();
                int id = priorityCat.getID();

                NavEvent navEvent = new NavEvent(this, project, id, description);

                navListener.submitOccurred(navEvent);
                //navListener.addBug(project, description);
                // Removing text from description only
                descriptionArea.setText("");
            }
        } else if (clicked == clear) {
            if (navListener != null) {
                // Removing text from all fields
                clearText();
            }
        }
    }
    /**
     * Clears text from every text field within NavPanel
     */
    public void clearText() {
        projectNameField.setText("");
        descriptionArea.setText("");
    }
    /**
     * Formatting components within NavPanel
     */
    public void layoutComponents() {
        // Declaring variables
        Insets noInsets = new Insets(0,0,0,0);
        Insets rightInset = new Insets(0,0,0,7);
        GridBagConstraints gc = new GridBagConstraints();
        ////////////////////////////// FIRST ROW //////////////////////////////
        // Initializing grid fields
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        // Adding project name label
        add(projectNameLabel, gc);
        //// Second column ////
        gc.insets = noInsets;
        gc.gridx = 1;
        gc.gridy = 0;
        // Adding project name text field
        gc.anchor = GridBagConstraints.LINE_START;
        add(projectNameField, gc);
        ////////////////////////////// SECOND ROW //////////////////////////////
        // Changing grid fields
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        // Adding description label
        add(priorityLabel, gc);
        ////////////////////////////// THIRD ROW //////////////////////////////
        // Changing grid fields
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = noInsets;
        // Adding priority list
        add(priorityList, gc);
        ////////////////////////////// FOURTH ROW //////////////////////////////
        // Changing grid fields
        gc.gridx = 0;
        gc.gridy = 3;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        // Adding description label
        add(descriptionLabel, gc);
        ////////////////////////////// FIFTH ROW //////////////////////////////
        // Changing grid fields
        gc.insets = noInsets;
        gc.gridx = 0;
        gc.gridy = 4;
        gc.weighty = 0.1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.fill = GridBagConstraints.BOTH;
        // Adding description text field
        add(new JScrollPane(descriptionArea), gc);
        ////////////////////////////// SIXTH ROW //////////////////////////////
        // Changing grid fields
        gc.gridx = 0;
        gc.gridy = 5;
        gc.weighty = 1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.fill = GridBagConstraints.NONE;
        // Adding submit button
        add(submit, gc);
        //// Second column ////
        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        // Adding clear button
        add(clear, gc);
    }
}
/**
 * Small utility class for assigning IDs to strings in the priority list
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
