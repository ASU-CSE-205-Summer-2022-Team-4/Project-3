/**
 * CLASS: Main (Main.java)
 *
 * DESCRIPTION:
 * Implements a gradebook tracking students in 'gradebook.dat', allowing the user to search for
 * and edit student grades on homework assignments and exams.
 * User can Search for students to view or modify existing grades.
 * Any changes made to grades in this GUI will be saved to 'gradebook.dat' upon saving or closing
 * the program. 
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

import java.io.FileNotFoundException;
import javax.swing.JFrame;

/**
 * The Main class containing the main() and run() methods.
 */
public class Main {

    /*
     * The number of exams given in the course.
     */
    private static final int NUM_EXAMS = 3;

    /*
     * The number of homework assignments in the course.
     */
    private static final int NUM_HOMEWORKS = 5;

    /**
     * The Roster of students that is read from the input file "gradebook.dat".
     */
    private Roster mRoster;

    /**
     * A reference to the View object.
     */
    private View mView;

    /**
     * This is where execution starts. Instantiate a Main object and then call
     * run().
     */
    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * exit() is called when the Exit button in the View is clicked. When we exit we
     * have to write the roster to the output file "gradebook.dat". Then we exit the
     * program with a code of 0.
     *
     * We open the file and write the roster to it in a try-catch block, where we
     * catch a FileNotFoundException that will be thrown if for some reason, we
     * cannot open "gradebook.dat" for writing.
     *
     */
    public void exit() {
        try (GradebookWriter gbWriter = new GradebookWriter("gradebook.dat")) {
            gbWriter.writeGradebook(getRoster());
            System.exit(0);
        } catch (FileNotFoundException e) {
            getView().messageBox("Could not open 'gradebook.dat' for writing. Exiting without saving.");
            System.exit(-1);
        }
    }

    /**
     * This method returns the number of exams in the class by returning the
     * constant NUM_EXAMS.
     */
    public static final int getNumExams() {
        return NUM_EXAMS;
    }

    /**
     * This method returns the number of homework assignments in the class by
     * returning the constant NUM_HOMEWORKS.
     */
    public static final int getNumHomeworks() {
        return NUM_HOMEWORKS;
    }

    /**
     * Accessor method for mRoster.
     */
    private Roster getRoster() {
        return mRoster;
    }

    /**
     * Accessor method for mView.
     */
    private View getView() {
        return mView;
    }

    /**
     * run() is the main routine and is called from main().
     *
     */
    private void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setView(new View(this));
        try {
            GradebookReader gbReader = new GradebookReader("gradebook.dat");
            setRoster(gbReader.readGradebook()); // reads and creates a Roster of Students from file
        } catch (FileNotFoundException e) {
            getView().messageBox("Could not open 'gradebook.dat' for reading. Exiting.");
            System.exit(-1);
        }
    }

    /**
     * search() is called when the Search button is clicked in the View. The input
     * parameter is the last name of the Student to search the roster for. Call
     * getStudent(pLastName) on the Roster object (call getRoster() to get the
     * reference to the Roster) to get a reference to the Student with that last
     * name. If the student is not located, getStudent() returns null.
     *
     * @param pLastName The last name of the student who we will search the Roster
     *                  for.
     */
    public Student search(String pLastName) {
        return getRoster().getStudent(pLastName);
    }

    /**
     * Mutator method for mRoster.
     */
    private void setRoster(Roster pRoster) {
        mRoster = pRoster;
    }

    /**
     * Mutator method for mView.
     */
    private void setView(View pView) {
        mView = pView;
    }
} // end Main
