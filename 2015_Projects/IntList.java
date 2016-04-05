import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;

/**
 * IntList models a list of integers and implement several array operations
 *
 * @author Ping Zhang Sept. 2015
 * @version %I%, %G%
 * @since 1.0
 */
public class IntList {

    public static Scanner console = new Scanner(System.in);

    private int[] items;

    public static final String SEPERATOR = " ";

    public IntList() {
        items = new int[5];
        fillArrayInt(items);
    }

    /**
     * Class Constructor that prompts user to enter n integer values and
     * initializes the array elements to user values sequentially
     *
     * @param n the length of items array within the new IntList to be created
     */
    public IntList(int n) {
        if (n <= 0) {
            sopln("Invalid Array elements number");
            return;
        }
        items = new int[n];
        fillArrayInt(items);
    }

    /**
     * A Constructor that create this items with the same length and elements
     * with the argument array
     *
     * @param arr an int array whose elements be copied to create a new array
     */
    public IntList(int[] arr) {
        if (arr == null) {
            sopln("Argument is a null reference.");
            return;
        }
        if (arr.length == 0) {
            sopln("Argument should have at least one element");
            return;
        }
        items = new int[arr.length];
        System.arraycopy(arr, 0, items, 0, items.length);
        sopln("The array has been instanciated.");
    }

    /**
     * A Copy Constructor that invokes constructor IntList(int[] arr) with the
     * argument's items
     *
     * @param intlist0
     */
    public IntList(IntList intlist0) {
        this(intlist0.items);
    }

    /**
     *
     * @return the length of this.items
     */
    public int capacity() {
        return items.length;
    }

    /**
     * Reverses the order of the elements in this.items array T(n) = 3n/2
     */
    public void reverse() {
        int tmp;
        final int LENGTH = items.length;

        int left = 0;
        int right = LENGTH - 1;
        while (left < right) {
            tmp = items[left];
            items[left] = items[right];
            items[right] = tmp;
            ++left;
            right = LENGTH - 1 - left;
        }
    }

    /**
     * invokes maxIndexList() to get a list of max value indexes
     *
     * @return the index of the first largest element in this.items array
     * @see maxIndexList()
     */
    public int maxIndex() {
        int[] maxIndexList = maxIndexList();
        return maxIndexList[0];
    }

    /**
     * invokes maxIndex() for the first max value index
     *
     * @return the value of the largest element in this.items array
     * @see maxIndex()
     */
    public int maxValue() {
        return items[maxIndex()];
    }

    /**
     *
     * @return a list of all the indexes with the largest value
     */
    public int[] maxIndexList() {
        final int LENGTH = items.length;
        /**
         * maxIndexList a new array to hold the possible maximum length of
         * largest value, which is the this.items.length, in case all elements
         * have the same values.
         */
        int[] maxIndexList = new int[LENGTH];
        //assume the first index has the largest value, and saves the first index 0 
        // to maxIndexList[0]
        int maxIndex = maxIndexList[0] = 0;
        //countMaxIndex counts numbers of largest values
        int countMaxIndex = 1;

        for (int m = 1; m < LENGTH; m++) {
            //when a bigger value is found, reset the maxIndex and maxIndexList[0]
            // also reset the number of largest values to 1
            if (items[maxIndex] < items[m]) {
                maxIndex = maxIndexList[0] = m;
                countMaxIndex = 1;
            } //if another element with the same value is found, add this index 
            //to maxIndexList array and increment countMaxIndex
            else if (items[maxIndex] == items[m]) {
                maxIndexList[countMaxIndex] = m;
                countMaxIndex++;
            }
        }
        //Create a new array for return
        int[] maxIndexListReturn = new int[countMaxIndex];
        //copy the valid index list from maxIndexList to maxIndexListReturn, 
        //the length is countMaxIndex: the number of largest values
        System.arraycopy(maxIndexList, 0, maxIndexListReturn, 0, countMaxIndex);

        return maxIndexListReturn;
    }

    /**
     * invokes minIndexList() to get a list of smallest value indexes
     *
     * @return the index of the first smallest element in this.items array
     * @see minIndexList()
     */
    public int minIndex() {
        int[] minIndexList = minIndexList();
        return minIndexList[0];
    }

    /**
     * invokes minIndex() for the first smallest value index
     *
     * @return the value of the smallest element in this.items array
     * @see minIndex()
     */
    public int minValue() {
        return items[minIndex()];
    }

    /**
     *
     * @return a list of all the indexes with the smallest value
     */
    public int[] minIndexList() {
        int[] minIndexList = new int[items.length];
        int minIndex = minIndexList[0] = 0;
        int countMinIndex = 1;

        for (int m = 1; m < items.length; m++) {
            if (items[minIndex] > items[m]) {
                minIndex = minIndexList[0] = m;
                countMinIndex = 1;
            } else if (items[minIndex] == items[m]) {
                minIndexList[countMinIndex] = m;
                countMinIndex++;
            }
        }
        int[] minIndexListReturn = new int[countMinIndex];
        System.arraycopy(minIndexList, 0, minIndexListReturn, 0, countMinIndex);

        return minIndexListReturn;
    }

    /**
     *
     * @param index the index to retrieve a value from this.items
     * @return the element of specific index of this.items
     */
    public int get(int index) {
        if (index >= items.length || index < 0) {
            sopln("Invalid index: " + index);
            sopln("index must be in the range [0, " + (items.length - 1) + "]");
            sopln("Program is now terminated.");
            System.exit(0);
        }
        return items[index];
    }

    /**
     *
     * @param index the index of this.items whose value to be set
     * @param value the value to be set on the specific index of this.items
     */
    public void set(int index, int value) {
        if (index >= items.length || index < 0) {
            sopln("Invalid index: " + index);
            sopln("index must be in the range [0, " + items.length + "]");
            sopln("Program is now terminated.");
            System.exit(0);
        }
        items[index] = value;

    }

    @Override
    public String toString() {
        String value = "";
        if (items == null) {
            sopln("items is null.");
        } else {
            for (int index = 0; index < items.length; index++) {
                value += (items[index]);
                if (index != items.length - 1) {
                    value += (SEPERATOR);
                }
            }
        }
        return value;
    }

    /**
     * Returns a newly constructed list filled with the elements of ‘this’ list
     * followed by the elements of otherlist
     *
     * @param otherlist the IntList to be "appended" to this.items' elements
     * @return a new IntList consists of this.items and otherlist
     */
    public IntList combine(IntList otherlist) {
        if (otherlist == null) {
            sopln("the IntList is null");
            System.exit(0);
        }
        int newLength = this.items.length + otherlist.items.length;
        int[] newArray = new int[newLength];

        System.arraycopy(this.items, 0, newArray, 0, this.items.length);
        System.arraycopy(otherlist.items, 0, newArray, this.items.length, otherlist.items.length);

        return new IntList(newArray);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final IntList other = (IntList) obj;

        if (this.items.length != other.items.length) {
            return false;
        }

        for (int k = 0; k > this.items.length; ++k) {
            if (this.items[k] != other.items[k]) {
                return false;
            }
        }
        return true;
    }

    /**
     * A simplified print method
     *
     * @param obj
     */
    public static void sopln(Object obj) {
        out.println(obj);
    }

    /**
     * Ask user to input integer number to initialize the argument array
     *
     * @param list the integer[] to be initialized
     */
    public static void fillArrayInt(int[] list) {
        String S0 = "Please input " + list.length + " integer numbers";
        sopln(S0);

        String S2 = "Invalid input, must input a integer number only";
        String myPattern = "0|-*[1-9]\\d*";

        for (int index = 0; index < list.length; index++) {
            String S1 = "Value " + (index + 1) + " ?";
            list[index] = askInt(S1, S2, myPattern);
        }
        sopln("The array has been instanciated.");
    }

    /**
     * asks user to type in a integer and validates the input
     *
     * @param S1 the String prompt asking for input, for example: "Please input
     * a integer number"
     * @param S2 the String prompt alert an invalid input, for example: "Invalid
     * input, must input a integer number only"
     * @param Pattern the String Pattern for validate input for example:
     * "0|-*[1-9]\\d*" for all integer
     * @return the integer that is validated
     */
    public static int askInt(String S1, String S2, String Pattern) {
        sopln(S1);
        boolean isValidInput;
        int value = 0;

        do {
            if (console.hasNext(Pattern)) {
                isValidInput = TRUE;
                value = console.nextInt();
            } else {
                sopln(S2);
                isValidInput = FALSE;
                console.next();
            }
        } while (!isValidInput);
        return value;
    }

}
