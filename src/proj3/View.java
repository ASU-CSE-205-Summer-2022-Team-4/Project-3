/**
 * CLASS: View (View.java)
 *
 * DESCRIPTION:
 * TODO INSERT DESCRIPTION HERE
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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The View class implements the GUI. It is a subclass of JFrame and implements the ActionListener
 * interface so that we can respond to user-initiated GUI events.
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
    
    /**
     * When the View() ctor is called from Main.run() to create the View, run() passes a reference
     * to the Main object as the argument to View(). We save that reference into mMain and then
     * later we can use mMain to communicate with the Main class.
     *
     * mMain is made accessible within this class via accessor/mutator methods getMain() and
     * setMain(). It shall not be directly accessed.
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
     * The View constructor creates the GUI interface and makes the frame visible at the end.
     *
     * @param pMain is an instance of the Main class. This links the View to the Main class so
     * they may communicate with each other.
     */
    public View(Main pMain) {

        /**
         * Save a reference to the Main object pMain into instance var mMain by calling setMain().
         */
        setMain(pMain);

        // PSEUDOCODE:
        // Create a JPanel named panelSearch which uses the FlowLayout
        // Add a JLabel "Student Name: " to panelSearch
        // Create mStudentName and make the field 25 cols wide
        // Add mStudentName to the panel
        // Create mSearchButton with the label "Search"
        // Make this View the action listener for the button
        // Add the button to the panel
        //TODO ENTER CODE HERE
		JPanel panelSearch = new JPanel();
		JLabel studentName = new JLabel("Studentn Name: ");
		panelSearch.add(studentName);
		mStudentName = new JTextField(25);
		panelSearch.add(mStudentName);
		mSearchButton = new JButton("Search");
		mSearchButton.addActionListener(this);
		panelSearch.add(mSearchButton);


        // PSEUDOCODE:
        // Create a JPanel named panelHomework which uses the FlowLayout
        // Add a JLabel "Homework: " to the panel
        // Create mHomeworkText which is an array of JTextFields, one for each homework assignment
        // For i = 0 to the number of homework assignments Do
        //     Create a textfield mHomeworkText[i] displaying 5 cols
        //     Add mHomeworkText[i] to the panel
        // End For
        // Note: DO NOT HARDCODE THE NUMBER OF HOMEWORK ASSIGNMENTS
        //TODO ENTER CODE HERE
		JPanel panelHomework = new JPanel()
		panelHomework.add(new JLabel("Homework: "));
		mHomeworkText = JTextField[Main.getNumHomeworks()];
		for(int i = 0; i < Main.getNumHomeworks(); i++){
			mHomeworkText[i] = new JTextField(5);
			panelHomework.add(mHomeworkText[i]);
		}

        // Create the exam panel which contains the "Exam: " label and the two exam text fields.
        // The pseudocode is omitted because this code is very similar to the code that creates the
        // panelHomework panel above.
        // Note: DO NOT HARDCODE THE NUMBER OF EXAMS
        //TODO ENTER CODE HERE
		JPanel panelExam = new JPanel();
		panelExam.add(new JLabel("Exam: "));
		mExamText = JTextField[Main.getNumExams()];
		for(int i = 0; i < Main.getNumExams(); i++){
			mExamText[i] = new JTextField(3);
			panelExam.add(mHomeworkText[i]);
		}

        // PSEUDOCODE:
        // Create a JPanel named panelButtons using FlowLayout
        // Create the Clear button mClearButton labeled "Clear"
        // Make this View the action listener for mClearButton
        // Add the  Clear button to the panel
        // Repeat the three above statements for the Save button
        // Repeat the three above statements for the Exit button
        //TODO ENTER CODE HERE
		JPanel panelButtons = new JPanel();
		mClearButton = new JButton("Clear");
		mClearButton.addActionListener(this);
		panelButtons.add(mClearButton);
		mSaveButton = new JButton("Save");
		mSaveButton.addActionListener(this);
		panelButtons.add(mSaveButton);
		mExitButton = new JButton("Clear");
		mExitButton.addActionListener(this);
		panelButtons.add(mExitButton);

        // PSEUDOCODE:
        // Create a JPanel named panelMain using a vertical BoxLayout
        // Add panelSearch to panelMain.
        // Add panelHomework to panelMain
        // Add panelExam to panelMain
        // Add panelButtons to panelMain
        //TODO ENTER CODE HERE
		JPanel panelMain = new JPanel().setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		panelMain.add(panelSearch);
		panelMain.add(panelHomework);
		panelMain.add(panelExam);
		panelMain.add(panelButtons);

        // Set the title of the View to whatever you want by calling setTitle()
        setTitle("Gred :: Gradebook Editor");
        
        // Set the size of the View to FRAME_WIDTH x FRAME_HEIGHT
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        // Make the View non-resizable
        setResizable(false);
        
        // Set the default close operation to JFrame.DO_NOTHING_ON_CLOSE. This disables the X close
        // button in the title bar of the View so now the only way to exit the program is by click-
        // ing the Exit button. This ensures that Main.exit() will be called so it will write the
        // student records back out to the gradebook database.
        //TODO ENTER CODE HERE
		JFrame.setDefaultLookAndFeelDecorated(DO_NOTHING_ON_CLOSE);
        
        // Add panelMain to the View.
        add(panelMain);
        
        // If you are a Mac user, you may need to call the pack() method which is inherited from
        // java.awt.Window() now to pack the View before displaying it. Windows and Linux users do
        // not need to do this, although if you do, it will not cause any problems.
        // ???
		pack();

        // Now display the View by calling setVisible().
        setVisible(true);
    }

    /**
     * actionPerformed()
     *
     * Called when one of the JButtons is clicked. Detects which button was clicked and handles it.
     *
     * Make sure to write the @Override annotation to prevent accidental overloading because we are
     * overriding JFrame.actionPerformed().
     *
     */
    @Override
    public void actionPerformed(ActionEvent pEvent) {
/*
	     *         If the curr student object saved in the Student class is null Then
	     *             Call messageBox() to display "Student not found. Try again."
	     *         Else
	     *             Retrieve the curr student from the Student class and pass it to displayStudent()
	     *         End if
	     *     End If
	     *
	     * Else if the source of the event was the Save button Then
	     *     If Student.getCurrStudent() is not null Then Call saveStudent(Student.getCurrStudent())
	     *
	     * Else if the source of the event was the Clear button Then
	     *     Call clear()
	     *
	     * Else if the source of the event was the Exit button Then
	     *     If Student.getCurrStudent() is not null Then Call saveStudent(Student.getCurrStudent())
	     *     Call getMain().exit() to terminate the application
	     * End If
	     * end actionPerformed
    	 */
		if(pEvent.getActionCommand().equals("Search")){
            clearNumbers();
			String lastName = mStudentName.getText();
			if(lastName == ""){
				messageBox("Please enter the student's last name.");
			} else {
				Student.setCurrStudent(getMain().search(lastName));
				if(Student.)
			}
        }
    }

    /**
     * clear()
     *
     * Called when the Clear button is clicked. Clears all of the text fields by setting the
     * contents of each to the empty string.
     *
     * After clear() returns, no student information is being edited or displayed and mStudent
     * has been set to null.
     *
     */
    private void clear() {
    	/*
    	 *	PSEUDOCODE:
    	 * method clear() : void
    	 *     Set the mStudentName text field to ""
    	 *     Clear the numbers in the homework and exam fields by calling clearNumbers()
    	 *     Set the current Student object in the Student class to null
    	 * end clear
    	 */
		mStudentName.setText("");
		clearNumbers();
		Student.setCurrStudent(null);
    }

    /**
     * clearNumbers()
     *
     * Clears the homework and exam fields.
     *
     * 
     */
    private void clearNumbers() {
    	// DO NOT HARCODE THE NUMBER OF HOMEWORKS AND EXAMS. Call the constant accessor methods in Main.
    	//TODO ENTER CODE HERE


    }
    
    /**
     * displayStudent()
     *
     * Displays the homework and exam scores for a student in the mHomeworkText and mExamText text
     * fields.
     *
     * @param pStudent is the Student who's scores we are going to use to populate the text fields.
     *
     */
    private void displayStudent(Student pStudent) {
    	/*
    	 * PSEUDOCODE:
    	 * method displayStudent(pStudent : Student) : void
    	 *     For i = 0 to Main.getNumHomeworks - 1 Do
    	 *         int hw = pStudent.getHomework(i)
    	 *         String hwstr = convert hw to a String (Hint: Integer.toString())
    	 *         mHomeworkText[i].setText(hwstr)
    	 *     End For
	     *     Write a similar for loop to place the student's exams scores into the text fields
	     * end displayStudent
	     *
	     * DO NOT HARCODE THE NUMBER OF HOMEWORKS AND EXAMS. Call the constant accessor methods in
	     * Main.
    	 */
    }

    /**
     * Accessor method for mMain.
     */ 
    private Main getMain() {
        return mMain;
    }    

    /**
     * messageBox()
     *
     * Displays a message box containing some text. Note: read the Java 8 API page for JOptionPane
     * to see what the constructor arguments are to showMessageDialog(). You want to pass the 
     * appropriate "thing" for the first argument so your message dialog window will be centered
     * in the middle of the View frame. If your View frame is centered in the middle of your screen
     * then you did not pass the right "thing".
     *
     * PSEUDOCODE:
     * method messageBox(pMessage : String) : void
     *     Call JOptionPane.showMessageDialog() to display pMessage.
     * end messageBox
     */
    public void messageBox(String pMessage) {
        JOptionPane.showMessageDialog(this, pMessage, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * saveStudent()
     *
     * Retrieves the homework and exam scores for pStudent from the text fields and writes the
     * results to the Student record in the Roster.
     *
     */
    private void saveStudent(Student pStudent) {
    	/*
    	 * PSEUDOCODE:
	     * method saveStudent(pStudent : Student) : void
	     *     For i = 0 to Main.getNumHomeworks - 1 Do
	     *         String hwstr = mHomeworkText[i].getText()
	     *         int hw = convert hwstr to an int (Hint: Integer.parseInt())
	     *         Call pStudent.setHomework(i, hw)
	     *     End For
	     *     Write a similar for loop to save the exam scores in pStudent
	     * end method saveStudent
	     *
	     * DO NOT HARDCODE THE NUMBER OF HOMEWORKS AND EXAMS
    	 */
    }
    
    /**
     * Mutator method for mMain.
     */ 
    private void setMain(Main pMain) {
        mMain = pMain;
    }    

}
