/**
 * CLASS: GradebookWriter (GradebookWriter.java)
 *
 * DESCRIPTION:
 * After modifying Student exam or homework scores in main UI, this enables the program
 * to save the updated information back to 'gradebook.dat'. 
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
import java.io.PrintWriter;

/**
 * GradebookWriter inherits from PrintWriter and writes the gradebook info to
 * the file whose name is passed to the ctor.
 */
public class GradebookWriter extends PrintWriter {

    /**
     * Call the super class ctor that takes a String as the argument, i.e,
     * PrintWriter(String). The PrintWriter ctor opens the file named by pFname for
     * writing. It will throw a FileNotFoundException if the file could not be
     * opened for writing. We throw the exception here as well where it will
     * eventually be caught in Main.exit() -- see SR 7.
     *
     * @param pFname The name of the output file to be opened for writing.
     */
    public GradebookWriter(String pFname) throws FileNotFoundException {
        super(pFname);
    }

    /**
     * Writes the gradebook info to the output file which was opened in the ctor.
     * 
     * @param pRoster The roster of students.
     *
     */
    public void writeGradebook(Roster pRoster) {
        for (Student student : pRoster.getStudentList()) {
            println(student);
        }
        close();
    }
}
