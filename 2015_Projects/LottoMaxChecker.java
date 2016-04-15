import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to check the Lotto Max bets
 *
 * @author Ping Zhang
 */
public class LottoMaxChecker {

    public static void main(String[] args) {
        lotoCheck();
    }

    private static final List<ArrayList<String>> betList = new ArrayList<>();

    public static void lotoCheck() {

        File lotoNumbers = new File("lotoNumbers.txt");
        try {
            BufferedReader bufferedReader;
            try (FileReader reader = new FileReader(lotoNumbers)) {
                bufferedReader = new BufferedReader(reader);
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    String[] params = s.split(",");
                    betList.add(new ArrayList<>(Arrays.asList(params)));
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(LottoMaxChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("----------------------------------------------------------Result");
        printArray(betList.get(0), "_");
        System.out.println("----------------------------------------------------------My Bets");

        for (int j = 1; j < betList.size(); j++) {
            for (int i = 0; i < betList.get(0).size(); i++) {
                if (betList.get(j).contains(betList.get(0).get(i))) {
                    System.out.print(betList.get(0).get(i));
                    System.out.print("_");
                    betList.get(j).remove(betList.get(0).get(i));
                } else {
                    System.out.print("___");
                }
                //new marks
                if (i == betList.get(0).size() - 2) {
                    System.out.print("***");
                }

            }
            System.out.print("__");
            for (int i = 0; i < betList.get(j).size(); i++) {
                System.out.print(betList.get(j).get(i));
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

