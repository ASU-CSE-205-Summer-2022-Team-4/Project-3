/**
 * CLASS: Searcher (GradebookWriter.java)
 *
 * DESCRIPTION:
 * Implements recursive binary search in order to search student records for a given Student
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
 */

package proj3;

import java.util.ArrayList;

public class Searcher {

    /**
     * Searches ArrayList of Students, returning index of Student or -1 if not found
     * 
     * @param pList ArrayList to be searched
     * @param pKey  Name to search for
     * @return Integer: Index of pKey in pList, or -1 if not found.
     */
    public static int search(ArrayList<Student> pList, String pKey) {
        return recursiveBinarySearch(pList, pKey, 0, pList.size() - 1);
    }

    /**
     * Recursive binary search algorithm.
     * 
     * @param pList List to be searched
     * @param pKey  Item to be searched for
     * @param pLow  Lower index bound to search
     * @param pHigh Upper index bound to search
     * @return Integer: Index of pKey in pList, or -1 if not found.
     */
    public static int recursiveBinarySearch(ArrayList<Student> pList, String pKey, int pLow, int pHigh) {
        if (pLow > pHigh)
            return -1;
        int middle = (pLow + pHigh) / 2;
        if (pKey.compareTo(pList.get(middle).getLastName()) == 0)
            return middle;
        else if (pKey.compareTo(pList.get(middle).getLastName()) < 0) {
            return recursiveBinarySearch(pList, pKey, pLow, middle - 1);
        } else {
            return recursiveBinarySearch(pList, pKey, middle + 1, pHigh);
        }
    }
}
