/**
 * CLASS: Student (Student.java)
 *
 * DESCRIPTION:
 * Blueprint for Student object, containing student data read from input file.
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
 * The Student class stores the gradebook information for one Student.
 *
 * Note that Student implements the java.lang.Comparable<Student> interface
 * because we are going to be sorting the roster of students by last name so we
 * need to be able to compare the last names of two students A and B to
 * determine if A < B, or if A = B, or if A > B. See the compareTo() method.
 */
public class Student implements Comparable<Student> {

    /**
     * mCurrStudent is a reference to the Student object which is currently being
     * displayed and edited in the View. It should only be accessed via
     * accessor/mutator methods.
     */
    private static Student mCurrStudent;

    /**
     * mExamList is an ArrayList of Integers storing the student's exam scores.
     */
    private ArrayList<Integer> mExamList;

    /**
     * The student's first name.
     */
    private String mFirstName;

    /**
     * mHomework List is an ArrayList of Integers storing the student's homework
     * scores.
     */
    private ArrayList<Integer> mHomeworkList;

    /**
     * The student's last name.
     */
    private String mLastName;

    /**
     * Ctor
     * 
     * @param pFirstName Student first name
     * @param pLastName  Student last name
     */
    public Student(String pFirstName, String pLastName) {
        setFirstName(pFirstName);
        setLastName(pLastName);
        setExamList(new ArrayList<>());
        setHomeworkList(new ArrayList<>());
    }

    /**
     * Adds an exam score to the exam list
     *
     * @param pScore Score to add to list
     */
    public void addExam(int pScore) {
        getExamList().add(pScore);
    }

    /**
     * Adds a homework score to the homework list
     *
     * @param pScore Score to add to list
     */
    public void addHomework(int pScore) {
        getHomeworkList().add(pScore);
    }

    /**
     * Implements comparable for sorting purposes
     *
     * @param pStudent is a Student
     *
     *                 This method compares the last name of 'this' Student to the
     *                 last name of pStudent to determine if the last name of 'this'
     *                 Student is <, =, or > the last name of pStudent. It is called
     *                 during the quick sort routine in Sorter.partition().
     */
    @Override
    public int compareTo(Student pStudent) {
        return getLastName().compareTo(pStudent.getLastName());
    }

    /**
     * Accessor method for mCurrStudent.
     */
    public static Student getCurrStudent() {
        return mCurrStudent;
    }

    /**
     * Accessor method to retrieve an exam score from the list of exams.
     *
     * @param pNum The exam number for which we wish to retrieve the score.
     *
     * @return The exam score.
     *
     */
    public int getExam(int pNum) {
        return getExamList().get(pNum);
    }

    /**
     * Accessor method for mExamList.
     */
    private ArrayList<Integer> getExamList() {
        return mExamList;
    }

    /**
     * Accessor method for mFirstName.
     */
    public String getFirstName() {
        return mFirstName;
    }

    /**
     * Returns the student's full name in the format: "lastname, firstname".
     */
    public String getFullName() {
        return mLastName + ", " + mFirstName;
    }

    /**
     * Accessor method to retrieve a homework score from the list of homeworks.
     *
     * @param pNum The homework number for which we wish to retrieve the score.
     *
     * @return The homework score.
     *
     */
    public int getHomework(int pNum) {
        return getHomeworkList().get(pNum);
    }

    /**
     * Accessor method for mHomeworkList.
     */
    private ArrayList<Integer> getHomeworkList() {
        return mHomeworkList;
    }

    /**
     * Accessor method for mLastName.
     */
    public String getLastName() {
        return mLastName;
    }

    /**
     * Mutator method for mCurrStudent.
     */
    public static void setCurrStudent(Student pCurrStudent) {
        mCurrStudent = pCurrStudent;
    }

    /**
     * Mutator method to store an exam score into the list of exam scores.
     *
     * @pNum is the index into the list of exams, where 0 is the index of the first
     *       exam score.
     */
    public void setExam(int pNum, int pScore) {
        getExamList().set(pNum, pScore);
    }

    /**
     * Mutator method for mExamList.
     */
    private void setExamList(ArrayList<Integer> pExamList) {
        mExamList = pExamList;
    }

    /**
     * Mutator method for mFirstName.
     */
    public void setFirstName(String pFirstName) {
        mFirstName = pFirstName;
    }

    /**
     * Mutator method to store a homework score into the list of homework scores.
     *
     * @pNum is the index into the list of homeworks, where 0 is the index of the
     *       first hw score.
     */
    public void setHomework(int pNum, int pScore) {
        getHomeworkList().set(pNum, pScore);
    }

    /**
     * Mutator method for mHomeworkList.
     */
    private void setHomeworkList(ArrayList<Integer> pHomeworkList) {
        mHomeworkList = pHomeworkList;
    }

    /**
     * Mutator method for mLastName.
     */
    public void setLastName(String pLastName) {
        mLastName = pLastName;
    }

    /**
     * Returns a String representation of this Student. The format of the returned
     * string shall be such that the Student information can be printed to the
     * output file in this format:
     *
     * lastname firstname exam1 exam2 exam2 hw1 hw2 hw3 hw4 hw5
     *
     * where the fields are separated by spaces, except there is not space following
     * hw5.
     */
    @Override
    public String toString() {
        String result = getLastName() + " " + getFirstName() + " ";
        for (Integer exam : getExamList()) {
            result += exam + " ";
        }
        for (Integer hw : getHomeworkList()) {
            result += hw + " ";
        }
        return result.trim();
    }
}
