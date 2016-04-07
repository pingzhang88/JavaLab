import java.util.ArrayList;
import java.util.Objects;

/**
 * Solve problems 1-6 on page 951 of the course textbook.
 *
 * @author Ping Zhang Nov. 2015
 */
public class Recursion {

    /**
     * 1. Recursive Multiplication a recursive function that accepts two
     * argument, perform multiplication as repeated addition
     *
     * @param x integer value
     * @param y integer value
     * @return the multiplication result
     */
    public static int multiply(int x, int y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        if (x == 1) {
            return y;
        } else if (y == 1) {
            return x;
        }

        return multiply(x - 1, y) + y;
    }

    public static int multiplyByAddition(int x, int y) {

        int sign = 1;
        if (x > 0 && y < 0) {
            sign = -1;
        }
        if (x < 0 && y > 0) {
            sign = -1;
        }
        

        int xx = x < 0 ? -x : x;
        int yy = y < 0 ? -y : y;
        int tmp;
        if (xx > yy) {
            tmp = xx;
            xx = y;
            yy = tmp;
        }

        return multiply(xx, yy) / sign;
    }

    /**
     * 2. isMember Method The method should search an array for a specified
     * value, and return true if the value is found in the array, or false if
     * the value is not found in the array.
     *
     * @param <T> generic type of the element of the array
     * @param list an array with generic type T
     * @param t the specified value to search
     * @return the search result
     */
    public static <T> boolean isMember(T[] list, T t) {
        return isMember(list, t, list.length - 1);
    }

    private static <T> boolean isMember(T[] list, T t, int endPoint) {
        if (endPoint == 0) {
            return list[endPoint].equals(t);
        }
        return list[endPoint].equals(t) || isMember(list, t, endPoint - 1);
    }

    /**
     * 3. String Reverser A recursive method that accepts a string as its
     * argument and prints the string in reverse order
     *
     * @param inputS
     * @return the string in reverse order
     */
    public static String stringReverser(String inputS) {
        //String inputS = Objects.requireNonNull(s, "Argument Object should NOT be null");
        final int LENGTH = inputS.length();
        if (LENGTH == 1 || LENGTH == 0) {
            return inputS;
        }
        return stringReverser(inputS, 0, LENGTH - 1);
    }

    public static String stringReverser(String inputS, int left, int right) {
        if (left == right) {
            return "" + inputS.charAt(left);
        }
        if (left > right) {
            return "";
        }
        return inputS.charAt(right) + stringReverser(inputS, left + 1, right - 1) + inputS.charAt(left);
    }

    /**
     * 4. maxElement Method a recursive method that returns the largest value in
     * an array that is passed as an argument.
     *
     * @param <E> generic type that implement Comparable
     * @param list
     * @return the largest value
     */
    public static <E extends Comparable<E>> E maxElement(E[] input) {
        E[] list = Objects.requireNonNull(input, "Argument Object should NOT be null");
        final int LENGTH = list.length;
        return maxElement(list, LENGTH - 1);
    }

    private static <E extends Comparable<? super E>> E maxElement(E[] list, int lastIndex) {
        if (lastIndex == 0) {
            return list[0];
        }
        E max = maxElement(list, lastIndex - 1);

        if (max.compareTo(list[lastIndex]) < 0) {
            return list[lastIndex];
        } else {
            return max;
        }

    }

    /**
     * 5. Palindrome Detector A boolean method that uses recursion to determine
     * if a String argument is a palindrome.
     *
     * @param s
     * @return true is the input String is palindrome
     */
    public static boolean isPalindrome(String input) {
        char[] charToFilter = new char[]{',', '.', '+'};
        String sanitizedS = stringFilter(input);
        String s = Objects.requireNonNull(sanitizedS, "Require Non Null");
        final int LENGTH = s.length();

        return isPalindrome(s, 0, LENGTH - 1);
    }

    private static boolean isPalindrome(String s, int startPoint, int endPoint) {
        if (startPoint >= endPoint) {
            return true;
        }
        return (s.charAt(startPoint) == s.charAt(endPoint)) && isPalindrome(s, startPoint + 1, endPoint - 1);
    }

    /**
     * 6. Character Counter A method that uses recursion to count the number of
     * times a specific character occurs in an array of characters
     *
     * @param <T> generic type of the elements
     * @param list the array to be processed
     * @param t the specific element to be counted
     * @return the number of times of the specified element
     */
    public static <T> int countRepetition(T[] listInput, T tInput) {
        T[] list = Objects.requireNonNull(listInput, "List require non null");
        T t = Objects.requireNonNull(tInput, "Require Non Null");
        final int LENGTH = list.length;

        return countRepetition(list, t, LENGTH - 1);

    }

    private static <T> int countRepetition(T[] list, T t, int cursor) {

        if (cursor < 0) {
            return 0;
        }
        return ((list[cursor].equals(t)) ? 1 : 0) + countRepetition(list, t, cursor - 1);
    }
    
    public static int smallest(int[] array) {
        return smallest(array, array.length);
    }

    public static int smallest(int[] array, int cursor) {

        if (cursor == 1) {
            return array[0];
        }
        int trunk = smallest(array, cursor - 1);
        int tail = array[cursor - 1];
        return (tail < trunk) ? tail : trunk;
    }

    public static int smallestIndex(int[] array) {
        return smallestIndex(array, array.length);
    }

    public static int smallestIndex(int[] array, int cursor) {

        if (cursor == 1) {
            return 0;
        }
        int trunkSmallestIndex = smallestIndex(array, cursor - 1);

        int trunk = array[trunkSmallestIndex];
        int tail = array[cursor - 1];

        if (tail < trunk) {
            return cursor - 1;
        } else {
            return trunkSmallestIndex;
        }

    }
    
    public static String stringFilter(String s) {
        char[] originArray = s.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < originArray.length; ++i) {
            list.add(originArray[i]);
        }
        for (int i = 0; i < list.size(); ++i) {
            if (!Character.isAlphabetic(list.get(i))) {
                list.remove(i--);
            }
        }

        StringBuilder sb = new StringBuilder("");

        list.stream().forEach((element) -> {
            sb.append(element);
        });
        return (sb.toString());
    }    

}
