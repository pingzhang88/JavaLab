package Asg2B;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.System.out;
import java.util.Scanner;

/**
 * NumberList models a list of wrapper class:Number and implement array operations
 *
 * @author Ping Zhang Sept. 2015
 * @version %I%, %G%
 * @since 1.0
 */
public class NumberList {

    public static Scanner console = new Scanner(System.in);

    private Number[] items;

    /**
     * This is used in toString to separate array elements
     */
    public static final String SEPERATOR = " ";

    /**
     * Class Constructor
     */
    public NumberList() {
        items = new Number[5];
        fillArrayNumber(items);
    }

    /**
     * Class Constructor that prompts user to enter n integer values and
     * initializes the array elements to user values sequentially
     *
     * @param n the length of items array within the new NumberList to be
     * created
     */
    public NumberList(int n) {
        if (n <= 0) {
            sopln("Invalid Array elements number");
            return;
        }
        items = new Number[n];
        fillArrayNumber(items);
    }

    /**
     * A Constructor that create this items with the same length and elements
     * with the argument array
     *
     * @param arr an integer array whose elements be copied to create a new
     * array
     */
    public NumberList(int[] arr) {
        if (arr == null) {
            sopln("Argument is a null reference.");
            System.exit(0);//return;
        }
        if (arr.length == 0) {
            sopln("Argument should have at least one element");
            System.exit(0);
        }
        items = new Number[arr.length];

        for (int m = 0; m < items.length; m++) {
            //this.set(m,new Number(arr[m]));
            this.items[m] = new Number(arr[m]);
        }
    }

    /**
     * A Copy Constructor
     *
     * @param numlist0 another NumberLIst object whose items to be copied
     *
     */
    public NumberList(NumberList numlist0) {

        if (numlist0 == null) {
            sopln("Argument is a null reference.");
            System.exit(0);
        }
        final int LENGTH = numlist0.items.length;
        if (LENGTH == 0) {
            sopln("Argument should have at least one element");
            System.exit(0);
        }

        items = new Number[LENGTH];  // allocate array of references

        for (int m = 0; m < LENGTH; ++m) {
            this.items[m] = new Number(numlist0.items[m].get());

        }
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
        Number tmp;
        final int LENGTH = items.length;

        int left = 0;
        int right = LENGTH - 1;
        while (left < right) {
            tmp = items[left];
            items[left] = items[right];
            items[right] = tmp;
            ++left;
            --right;
            //right = LENGTH-1-left;
        }

    }

    /**
     * invokes maxIndexList() to get a list of max value indexes
     *
     * @return the index of the first largest element in this.items array
     */
    public int maxIndex() {
        int[] maxIndexList = maxIndexList();
        return maxIndexList[0];
    }

    /**
     * invokes maxIndex() for the first max value index
     *
     * @return the value of the largest element in this.items array Use {@link #maxIndex()
     * }
     */
    public int maxValue() {
        return items[maxIndex()].get();
    }

    /**
     * maxIndexList a new array to hold the possible maximum length of largest
     * value, which is the this.items.length, in case all elements have the same
     * values.
     *
     * @return a list of all the indexes with the largest value
     */
    public int[] maxIndexList() {
        final int LENGTH = items.length;
        int[] maxIndexList = new int[LENGTH];
        //assume the first index has the largest value, and saves the first index 0 
        // to maxIndexList[0]
        int maxIndex = maxIndexList[0] = 0;
        //countMaxIndex counts numbers of largest values
        int countMaxIndex = 1;

        for (int m = 1; m < LENGTH; m++) {
            //when a bigger value is found, reset the maxIndex and maxIndexList[0]
            // also reset the number of largest values to 1
            if (items[maxIndex].get() < items[m].get()) {
                maxIndex = maxIndexList[0] = m;
                countMaxIndex = 1;
            } //if another element with the same value is found, add this index 
            //to maxIndexList array and increment countMaxIndex
            else if (items[maxIndex].get() == items[m].get()) {
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
     * @return the index of the first smallest element in this.items array Use {@link #minIndexList()
     * }
     */
    public int minIndex() {
        int[] minIndexList = minIndexList();
        return minIndexList[0];
    }

    /**
     * invokes minIndex() for the first smallest value index
     *
     * @return the value of the smallest element in this.items array use {@link #minInde()
     * }
     */
    public int minValue() {
        return items[minIndex()].get();
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
            if (items[minIndex].get() > items[m].get()) {
                minIndex = minIndexList[0] = m;
                countMinIndex = 1;
            } else if (items[minIndex].get() == items[m].get()) {
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
    public Number get(int index) {
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
    public void set(int index, Number value) {
        if (index >= items.length || index < 0) {
            sopln("Invalid index: " + index);
            sopln("index must be in the range [0, " + items.length + "]");
            sopln("Program is now terminated.");
            System.exit(0);
        }
        items[index] = new Number(value);

    }

    @Override
    public String toString() {
        String value = "";
        if (items == null) {
            sopln("items is null.");
        } else {
            for (int index = 0; index < items.length; index++) {
                value += (items[index]).toString();
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
     * @param otherlist the NumberList to be "appended" to this.items' elements
     * @return a new NumberList consists of this.items and otherlist
     */
    public NumberList combine(NumberList otherlist) {
        if (otherlist == null) {
            sopln("the NumberList is null");
            System.exit(0);
        }
        final int OLD_LENGTH = this.items.length;
        final int NEW_LENGTH = OLD_LENGTH + otherlist.items.length;
        int[] newArray = new int[NEW_LENGTH];

        for (int m = 0; m < OLD_LENGTH; ++m) {
            newArray[m] = this.items[m].get();
        }
        for (int m = OLD_LENGTH; m < NEW_LENGTH; ++m) {
            newArray[m] = otherlist.items[m - OLD_LENGTH].get();
        }

        // System.arraycopy(this.items, 0, newArray, 0, this.items.length);
        // System.arraycopy(otherlist.items, 0, newArray, this.items.length, otherlist.items.length);
        return new NumberList(newArray);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final NumberList other = (NumberList) obj;

        if (this.items.length != other.items.length) {
            return false;
        }

        for (int k = 0; k > this.items.length; ++k) {
            if (this.items[k].equals(other.items[k])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ask user to input integer number to initialize the argument array
     *
     * @param list the integer[] to be initialized
     */
    public static void fillArrayNumber(Number[] list) {
        String S0 = "Please input " + list.length + " integer numbers";
        sopln(S0);

        String S2 = "Invalid input, must input a integer number only";
        String myPattern = "0|-*[1-9]\\d*";

        for (int index = 0; index < list.length; index++) //list[index] = console.nextInt();
        {
            String S1 = "Value " + (index + 1) + " ?";
            //Boxing
            list[index] = new Number(askInt(S1, S2, myPattern));//new99
        }
        sopln("The array has been instanciated.");
    }

    /**
     *
     * @param obj the Object to be printed
     */
    public static void sopln(Object obj) {
        out.println(obj);
    }

    /**
     * Ask user to type in a integer and validates the input
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
