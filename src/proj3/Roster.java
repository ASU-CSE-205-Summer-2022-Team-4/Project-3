/**
 * CLASS: Roster (Roster.java)
 *
 * DESCRIPTION:
 * Object blueprint for "Roster" which is composed of Student data read from input file.
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

import java.util.ArrayList;

/**
 * The Roster class encapsulates an ArrayList<Student> object named mStudentList
 * which stores the information for each student that was read from
 * "gradebook.dat" when the app started.
 */
public class Roster {

    /**
     * Declare mStudentList as ArrayList<Student>
     */
    private ArrayList<Student> mStudentList;

    /**
     * Composes ArrayList of Students
     */
    public Roster() {
        setStudentList(new ArrayList<Student>());
    }

    /**
     * Adds pstudent to the ArrayList
     */
    public void addStudent(Student pStudent) {
        mStudentList.add(pStudent);
    }

    /**
     * Searches mStudentList for a Student with pLastName.
     */
    public Student getStudent(String pLastName) {
        int index = Searcher.search(getStudentList(), pLastName);
        if (index == -1)
            return null;
        else {
            return getStudentList().get(index);
        }
    }

    /**
     * Accessor method for mStudentList.
     */
    public ArrayList<Student> getStudentList() {
        return mStudentList;
    }

    /**
     * Mutator method for mStudentList.
     */
    private void setStudentList(ArrayList<Student> pStudentList) {
        mStudentList = pStudentList;
    }

    /**
     * Called to sort the Student Roster
     */
    public void sortRoster() {
        Sorter.sort(getStudentList());
    }

    /**
     * Returns a String representation of this Roster. toString() methods are very
     * handy for debugging because given access to a Roster object, say named
     * roster, then you can print the entire roster in one statement:
     * System.out.println(roster);
     */
    @Override
    public String toString() {
        String result = "";
        for (Student student : getStudentList()) {
            result += student + "\n";
        }
        return result;
    }
}
