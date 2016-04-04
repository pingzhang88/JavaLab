import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.System.out;
import java.util.Scanner;
/**
 * A class to implement Magic Square
 *
 * @author Ping Zhang
 */
public class MagicSquare {

    public static void main(String[] arg) {
        draw();
    }

    private static final Scanner SCAN = new Scanner(System.in);

    public static void draw() {
        final int LENGTH = askInt("How big is the Matrix? ",
                "Invalid input, must input an odd number only",
                "\\d*[13579]");

        int[][] ms = new int[LENGTH][LENGTH];
        final String SEPERATOR = " ";

        int row = 0;
        int column = LENGTH / 2;

        for (int l = 0; l < LENGTH * LENGTH; ++l) {

            ms[row][column] = l + 1;

            //works with line 41 ++row.
            int rowNext = (row - 1 + LENGTH) % LENGTH;
            int columnNext = (column + 1) % LENGTH;

            if (ms[rowNext][columnNext] != 0) {
                ++row;
            } else {
                row = rowNext;
                column = columnNext;
            }
        }

        for (int m = 0; m < LENGTH; ++m) {
            for (int l = 0; l < LENGTH; ++l) {
                System.out.printf("%3d%s", ms[m][l], SEPERATOR);
            }
            sopln("");
        }

        sopln(checkSumEqual(ms));
    }

    public static boolean checkSumEqual(int[][] a) {
        int[][] sumArray = new int[2][a.length];
        for (int m = 0; m < a.length; ++m) {
            sumArray[0][m] = rowSumMultipleArray(a, m);
        }
        for (int n = 0; n < a.length; ++n) {
            sumArray[1][n] = columnSumMultipleArray(a, n);
        }
        int sum = ((int) Math.pow(a.length, 3) + a.length) / 2; // (N^3+N)/2
        sopln("the sum of line is " + sum);

        for (int m = 0; m < sumArray.length; ++m) {
            for (int n = 0; n < sumArray[m].length; ++n) {
                if (sum != sumArray[m][n]) {
                    return false;
                }
            }
        }
        return true;

    }

    public static int rowSumMultipleArray(int[][] a, int row) {
        int sum = 0;
        for (int l = 0; l < a[row].length; ++l) {
            sum += a[row][l];
        }
        return sum;
    }

    public static int columnSumMultipleArray(int[][] a, int column) {
        int sum = 0;
        for (int l = 0; l < a.length; ++l) {
            if (a[l].length >= column) {
                sum += a[l][column];
            }
        }
        return sum;
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
            if (SCAN.hasNext(Pattern)) {
                isValidInput = TRUE;
                value = SCAN.nextInt();
            } else {
                sopln(S2);
                isValidInput = FALSE;
                SCAN.next();
            }
        } while (!isValidInput);
        return value;
    }

}
