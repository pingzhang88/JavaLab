import static asg3.Cipher.decode;
import static asg3.Cipher.encode;
import static asg3.Cipher.sopln;
import java.util.Random;

/**
 * Tests Cipher Class with random generated String messages.
 *
 * March 2016
 *
 * @author Ping Zhang
 */
public class TestAsg3 {

    public static void main(String[] args) {
        while (true) {
            for (int i = 30; i < 500; i++) {
                String message = randString(i);
//                sopln(message);
//                sopln(encode(message));
//                sopln(decode(encode(message)));     
                boolean result = decode(encode(message)).equals(message);
                sopln(result);
                if (!result) {
                    System.exit(9);
                }
            }
        }

    public static String randString(int length) {

        if (length < 0) {
            return null;
        } else {
            if (length == 0) {
                return "";
            } else {
                String tmp = "";
                for (int i = 0; i < length; i++) {
                    tmp += randChar();
                }
                return tmp;
            }
        }
    }

    public static char randChar() {
        Random rd = new Random();
        int rand = rd.nextInt(256);
        return (char) rand;
    }

}
