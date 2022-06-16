/**
 * CLASS: View (View.java)
 *
 * DESCRIPTION:
 * Creates the View (GUI window) for the program.
 * 
 *
 * COURSE AND PROJECT INFO
 * CSE205 Object Oriented Programming and Data Structures, Summer 2022 C-Session
 * Project Number: p-03
 *
 * Group 4
 * AUTHOR: David McConnell  dmcconn7    dmcconn7@asu.edu
 * AUTHOR: Lia Moua         amoua       amoua@asu.edu
 * AUTHOR: Arsal Akhtar     akakhta2    akakhta2@asu.edu
 * AUTHOR: Kari McBride     kemcbri2    kemcbri2@asu.edu
 * 
 * TEMPLATE AUTHOR
 * Kevin R. Burger (burgerk@asu.edu)
 * Computer Science & Engineering Program
 * Fulton Schools of Engineering
 * Arizona State University, Tempe, AZ 85287-8809
 * (c) Kevin R. Burger 2014-2019
 */
package proj3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The View class implements the GUI. It is a subclass of JFrame and implements
 * the ActionListener interface so that we can respond to user-initiated GUI
 * events.
 */
public class View extends JFrame implements ActionListener {

    /**
     * The width of the View frame.
     */
    public static final int FRAME_WIDTH = 525;

    /**
     * The height of the View frame.
     */
    public static final int FRAME_HEIGHT = 225;

    private static final String PROGRAM_TITLE = "Team 4 Gradebook Editor - CSE 205 Summer C";

    /**
     * When the View() ctor is called from Main.run() to create the View, run()
     * passes a reference to the Main object as the argument to View(). We save that
     * reference into mMain and then later we can use mMain to communicate with the
     * Main class.
     *
     * mMain is made accessible within this class via accessor/mutator methods
     * getMain() and setMain(). It shall not be directly accessed.
     */
    private Main mMain;

    /*
     * Declare GUI related instance variables for the buttons and text fields.
     */
    private JButton mClearButton;
    private JTextField[] mExamText;
    private JButton mExitButton;
    private JTextField[] mHomeworkText;
    private JButton mSaveButton;
    private JButton mSearchButton;
    private JTextField mStudentName;

    /**
     * View()
     *
     * The View constructor creates the GUI interface and makes the frame visible at
     * the end.
     *
     * @param pMain is an instance of the Main class. This links the View to the
     *              Main class so they may communicate with each other.
     */
    public View(Main pMain) {

        /**
         * Save a reference to the Main object pMain into instance var mMain by calling
         * setMain().
         */
        setMain(pMain);

        // Create a Panel containing student information and a search button
        JPanel panelSearch = new JPanel();
        JLabel studentName = new JLabel("Student Name: ");
        panelSearch.add(studentName);
        mStudentName = new JTextField(25);
        panelSearch.add(mStudentName);
        mSearchButton = new JButton("Search");
        mSearchButton.addActionListener(this);
        panelSearch.add(mSearchButton);

        // Create a Panel to contain Homework label and homework scores
        JPanel panelHomework = new JPanel();
        JLabel homework = new JLabel("Homework: ");
        panelHomework.add(homework);
        mHomeworkText = new JTextField[Main.getNumHomeworks()];
        for (int i = 0; i < Main.getNumHomeworks(); i++) {
            mHomeworkText[i] = new JTextField(5);
            panelHomework.add(mHomeworkText[i]);
        }

        // Create a Panel to contain Exam label and exam scores
        JPanel panelExam = new JPanel();
        JLabel exam = new JLabel("Exam: ");
        panelExam.add(exam);
        mExamText = new JTextField[Main.getNumExams()];
        for (int i = 0; i < Main.getNumExams(); i++) {
            mExamText[i] = new JTextField(5);
            panelExam.add(mExamText[i]);
        }

        // Create a panel to contain the Clear, Save, and Exit buttons
        JPanel panelButtons = new JPanel();
        JButton mClearButton = new JButton("Clear");
        mClearButton.addActionListener(this);
        panelButtons.add(mClearButton);
        JButton mSaveButton = new JButton("Save");
        mSaveButton.addActionListener(this);
        panelButtons.add(mSaveButton);
        JButton mExitButton = new JButton("Exit");
        mExitButton.addActionListener(this);
        panelButtons.add(mExitButton);

        // Create a panel to hold the other panels (a master container)
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.add(panelSearch);
        panelMain.add(panelHomework);
        panelMain.add(panelExam);
        panelMain.add(panelButtons);

        setTitle(PROGRAM_TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        // Disable X button to ensure Exit button is clicked so data is saved.
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // Add panelMain to the View.
        add(panelMain);

        // If you are a Mac user, you may need to call the pack() method which is
        // inherited from
        // java.awt.Window() now to pack the View before displaying it. Windows and
        // Linux users do
        // not need to do this, although if you do, it will not cause any problems.
        pack();

        setVisible(true);
    }

    /**
     * Called when one of the JButtons is clicked. Detects which button was clicked
     * and handles it.
     */
    @Override
    public void actionPerformed(ActionEvent pEvent) {
        if (pEvent.getActionCommand().equals("Search")) {
            // Clear display, set student's full name (or clear if not found),
            // and enter student scores into boxes
            clearNumbers();
            String lastName = mStudentName.getText();
            if (lastName == "") {
                messageBox("Please enter the student's last name.");
            } else {
                Student.setCurrStudent(getMain().search(lastName));
                if (Student.getCurrStudent() == null) {
                    mStudentName.setText("");
                    messageBox("Student not found. Try again.");
                } else {
                    displayStudent(Student.getCurrStudent());
                }
            }

        } else if (pEvent.getActionCommand().equals("Save")) {
            // If accessing data of an existing student, save any input scores
            if (Student.getCurrStudent() != null) {
                saveStudent(Student.getCurrStudent());
            }

        } else if (pEvent.getActionCommand().equals("Clear")) {
            clear();

        } else if (pEvent.getActionCommand().equals("Exit")) {
            // If modifying an existing student, save. Exit program.
            if (Student.getCurrStudent() != null) {
                saveStudent(Student.getCurrStudent());
            }
            getMain().exit();
        }
    }

    /**
     * Called when the Clear button is clicked. Clears all of the text fields by
     * setting the contents of each to the empty string.
     *
     * After clear() returns, no student information is being edited or displayed
     * and mStudent has been set to null.
     *
     */
    private void clear() {
        mStudentName.setText("");
        clearNumbers();
        Student.setCurrStudent(null);
    }

    /**
     * Clears the homework and exam fields. Uses static references to Main
     * constants.
     */
    private void clearNumbers() {
        for (int i = 0; i < Main.getNumHomeworks(); i++) {
            mHomeworkText[i].setText("");
        }
        for (int i = 0; i < Main.getNumExams(); i++) {
            mExamText[i].setText("");
        }
    }

    /**
     * Displays the homework and exam scores for a student in the mHomeworkText and
     * mExamText text fields. Uses static references to Main constants.
     *
     * @param pStudent is the Student whose scores we are going to use to populate
     *                 the text fields.
     *
     */
    private void displayStudent(Student pStudent) {
        // Display full name in search box, instead of just last name entered
        mStudentName.setText(pStudent.getFullName());
        for (int i = 0; i <= Main.getNumHomeworks() - 1; i++) {
            int hw = pStudent.getHomework(i);
            String hwstr = Integer.toString(hw);
            mHomeworkText[i].setText(hwstr);
        }
        for (int i = 0; i <= Main.getNumExams() - 1; i++) {
            int exam = pStudent.getExam(i);
            String examstr = Integer.toString(exam);
            mExamText[i].setText(examstr);
        }
    }

    /**
     * Accessor method for mMain.
     */
    private Main getMain() {
        return mMain;
    }

    /**
     * Displays a message box containing some text. Note: read the Java 8 API page
     * for JOptionPane to see what the constructor arguments are to
     * showMessageDialog(). You want to pass the appropriate "thing" for the first
     * argument so your message dialog window will be centered in the middle of the
     * View frame. If your View frame is centered in the middle of your screen then
     * you did not pass the right "thing".
     */
    public void messageBox(String pMessage) {
        JOptionPane.showMessageDialog(this, pMessage, "Alert", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Retrieves the homework and exam scores for pStudent from the text fields and
     * writes the results to the Student record in the Roster. Contains static
     * references to Main constants.
     * 
     * @param pStudent Student whose data to be saved.
     *
     */
    private void saveStudent(Student pStudent) {
        for (int i = 0; i <= Main.getNumHomeworks() - 1; i++) {
            String hwstr = mHomeworkText[i].getText();
            int hw = Integer.parseInt(hwstr);
            pStudent.setHomework(i, hw);
        }
        for (int i = 0; i <= Main.getNumExams() - 1; i++) {
            String examstr = mExamText[i].getText();
            int exam = Integer.parseInt(examstr);
            pStudent.setExam(i, exam);
        }
    }

    /**
     * Mutator method for mMain.
     */
    private void setMain(Main pMain) {
        mMain = pMain;
    }
}
