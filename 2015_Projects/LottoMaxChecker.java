package loto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * A class to check the Lotto Max bets
 * @author Ping Zhang
 */
public class LottoMaxChecker {

    public static void main(String[] args) {
        lotoCheck();
    }

    private static final List<ArrayList<String>> bitList = new ArrayList<>();

    public static void lotoCheck() {

        bitList.add(askStrArrayList("Please input Lotto Result list of 8 number strings", "Invalid Input", "0[1-9]|[1-4][0-9]*", 8));

        bitList.add(askStrArrayList("Please input bit 1 list of 7", "Invalid Input", "0[1-9]|[1-4][0-9]*", 7));
        bitList.add(askStrArrayList("Please input bit 2 list of 7", "Invalid Input", "0[1-9]|[1-4][0-9]*", 7));
        bitList.add(askStrArrayList("Please input bit 3 list of 7", "Invalid Input", "0[1-9]|[1-4][0-9]*", 7));
        System.out.println("----------------------------------------------------------Result");
        printArray(bitList.get(0), "_");
        System.out.println("----------------------------------------------------------My Bits");

        for (int j = 1; j < bitList.size(); j++) {
            for (int i = 0; i < bitList.get(0).size(); i++) {
                if (bitList.get(j).contains(bitList.get(0).get(i))) {
                    System.out.print(bitList.get(0).get(i));
                    System.out.print("_");
                    bitList.get(j).remove(bitList.get(0).get(i));
                } else {
                    System.out.print("___");
                }
                //new marks
                if (i == bitList.get(0).size() - 2) {
                    System.out.print("***");
                }

            }
            System.out.print("__");
            for (int i = 0; i < bitList.get(j).size(); i++) {
                System.out.print(bitList.get(j).get(i));
                System.out.print("_");
            }
            System.out.println();

        }

    }

    /**
     * Ask for input of numbers list in String format, generates an ArrayList
     * containing these number Strings
     *
     * @param S1 e.g. "Please input a integer number list"
     * @param S2 e.g. "Invalid input, must input a integer number only" 
     * @param Pattern Integer Pattern: "0|-*[1-9]\\d*"
     * @param size the amount of the numbers
     * @return the ArrayList that contains the numbers
     */
    public static ArrayList<String> askStrArrayList(String S1, String S2, String Pattern, int size) {
        Scanner console = new Scanner(System.in);
        System.out.println(S1);

        ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (console.hasNext(Pattern)) {
                strList.add(console.next());
            } else {
                System.out.println(S2);
                console.next();
            }
        }
        return strList;
    }

    /**
     * 
     * @param list The List to be printed
     * @param mySeparator The String to separate the list elements
     */
    public static void printArray(ArrayList<String> list, String mySeparator) {
        for (int index = 0; index < list.size(); index++) {
            System.out.print(list.get(index));
            if (index != list.size() - 1) {
                System.out.print(mySeparator);
            }
            if (index == list.size() - 2) {
                System.out.print("***");
            }
        }
        System.out.println();
    }

}
