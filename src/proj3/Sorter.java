/**
 * CLASS: Sorter (Sorter.java)
 *
 * DESCRIPTION:
 * Sorts ArrayList of students in ascending order by last name.
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

public class Sorter {

    /**
     * Sorts given ArrayList of students.
     * 
     * @param pList List to be sorted
     */
    public static void sort(ArrayList<Student> pList) {
        quickSort(pList, 0, pList.size() - 1);
    }

    /**
     * Utilized in quick sort algorithm; partitions list into smaller sublists.
     * 
     * @param pList     List to be partitioned
     * @param fromIndex Beginning index
     * @param toIndex   Ending index
     * @return Returns "right" index, as used in recursive quick sort algorithm
     */
    private static int partition(ArrayList<Student> pList, int fromIndex, int toIndex) {
        int leftIndex = fromIndex - 1;
        int rightIndex = toIndex + 1;
        Student pivot = pList.get(fromIndex);
        while (leftIndex < rightIndex) {
            leftIndex++;
            while (pList.get(leftIndex).compareTo(pivot) < 0) {
                leftIndex++;
            }
            rightIndex--;
            while (pList.get(rightIndex).compareTo(pivot) > 0) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                swap(pList, leftIndex, rightIndex);
            }
        }
        return rightIndex;
    }

    /**
     * Recursive quick sort as taught in lecture.
     * 
     * @param pList     List to be sorted
     * @param fromIndex Beginning index
     * @param toIndex   Ending index
     */
    public static void quickSort(ArrayList<Student> pList, int fromIndex, int toIndex) {
        if (fromIndex >= toIndex)
            return;
        int partitionIndex = partition(pList, fromIndex, toIndex);
        quickSort(pList, fromIndex, partitionIndex);
        quickSort(pList, partitionIndex + 1, toIndex);
    }

    /**
     * Swaps two items in a given list; utilized for quick sort algorithm
     * 
     * @param pList      List containing items to be swapped
     * @param leftIndex  Index of first item to swap
     * @param rightIndex Index of second item to swap
     */
    private static void swap(ArrayList<Student> pList, int leftIndex, int rightIndex) {
        Student tmp = pList.get(leftIndex);
        pList.set(leftIndex, pList.get(rightIndex));
        pList.set(rightIndex, tmp);
    }
}
