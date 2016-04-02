import static java.lang.System.out;

/**
 * Implements Caesar Cipher Encoding message using Queue
 *
 * March 2016
 *
 * @author PING ZHANG
 */
public class Cipher {

    private static final String keyString = "0 -1 1 0 -1000 999 -4 -1 -6 7 -4 5 -2 -3 888 -99999 0";
    private static MyQueue<Integer> keyQueue;

    private static final char ENCODE = '+';
    private static final char DECODE = '-';
    private static final String UPPER = "upper";
    private static final String LOWER = "lower";

    /**
     * Initiate a keyQueue and fill it with keys containing in keyString
     */
    private static void fillKeyQueue() {
        keyQueue = new MyQueue<Integer>();
        String[] array = keyString.split(" ");
        for (String element : array) {
            keyQueue.addToQueue(Integer.parseInt(element));
        }
    }

    /**
     * Retrieve and return the key in the head and move it to the tail of the
     * queue.
     *
     * @param ch indicate the operation is encode or decode.
     * @return the key in the head.
     */
    private static int getKeyAndRotate(char ch) {

        Integer key = keyQueue.deleteFromQueue();
        keyQueue.addToQueue(key);

        if (ch == ENCODE) {
            return key;
        } else if (ch == DECODE) {
            return key * (-1);
        } else {
            sopln("Invalid Key Direction.");
            return Integer.MIN_VALUE;
        }

    }

    /**
     * Encode the given message using the encoding key list.
     *
     * @param message the given string need to be processed
     * @return the encoded message.
     */
    public static String encode(String message) {
        return enDeCode(message, ENCODE);
    }

    /**
     * Decode the given message using the encoding key list.
     *
     * @param message the given string need to be processed
     * @return the decoded message.
     */
    public static String decode(String message) {
        return enDeCode(message, DECODE);
    }

    /**
     * A method does both encode and decode work.
     *
     * @param message the given string need to be processed
     * @param encodeOrDecode indicates the operation is encode or decode.
     * @return the processed message
     */
    private static String enDeCode(String message, char encodeOrDecode) {

        fillKeyQueue();
        StringBuilder sb = new StringBuilder();

        //the pattern "(?~^)" split a string into each single character.
        String[] tokens = message.split("(?!^)");

        for (String token : tokens) {
            if (token.matches("[A-Z]")) {
                token = convert(token, encodeOrDecode, UPPER);
            } else if (token.matches("[a-z]")) {
                token = convert(token, encodeOrDecode, LOWER);
            }
//            else {
//                sopln("Not alphabet, do nothing.");
//            }
            sb.append(token);
        }
        return sb.toString();
    }

    /**
     * Convert a alphabet letter to another alphabet letter by adding a
     * key(positive or negative), which should be wrapped around to the
     * beginning or end of the alphabet
     *
     * @param token the alphabet letter needs to process
     * @param encodeOrDecode indicates the operation is encode or decode.
     * @param capital indicates the token is upper or lower case
     * @return the converted letter
     */
    private static String convert(String token, char encodeOrDecode, String capital) {

        char tokenCh = token.charAt(0);
        int key = getKeyAndRotate(encodeOrDecode);
        char ALPHA_BASE = '\u0000';
        final int RANGE = (int) ('Z' - 'A') + 1;

        switch (capital) {
            case UPPER:
                ALPHA_BASE = 'A';
                break;
            case LOWER:
                ALPHA_BASE = 'a';
                break;
            default:
                sopln("Invalide input, capital can ONLY be UPPER or LOWER");
        }

        //For a large key, say 999, or -999 to work, must use "+key%RANGE" instead of "+key"
        //"+RANGE" works for minus key.
        tokenCh = (char) (((int) tokenCh - (int) ALPHA_BASE + RANGE + key % RANGE) % RANGE + (int) ALPHA_BASE);

        return Character.toString(tokenCh);
    }

    /**
     * A simplified System.out.println(String s)
     *
     * @param obj any object to be printed
     */
    public static void sopln(Object obj) {
        out.println(obj);
    }

}
