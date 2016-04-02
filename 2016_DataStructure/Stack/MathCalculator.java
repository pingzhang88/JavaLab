import static java.lang.System.out;
import java.util.HashMap;
import java.util.Objects;

/**
 * Evaluates arithmetic expressions by using of a Generic Stack
 *
 * Feb 2016
 *
 * @author PING ZHANG
 */
public class MathCalculator {

    private String expression;
    private int result;
    private static final HashMap operators = new HashMap();

    /**
     * To initiate the operators and their priority, which is not identical to
     * Oracle Java definition for operators
     */
    static {
        operators.putIfAbsent('+', 1);
        operators.putIfAbsent('-', 1);
        operators.putIfAbsent('*', 2);
        operators.putIfAbsent('/', 2);
        operators.putIfAbsent('%', 2);
        operators.putIfAbsent('^', 3);
        operators.putIfAbsent('(', 0);
        operators.putIfAbsent(')', 0);
    }

    /**
     * zero-parameter constructor
     */
    public MathCalculator() {
        this.expression = null;
        this.result = 0;
    }

    /**
     * To evaluate the given arithmetic expression (parameter) to obtain the
     * result.
     *
     * @param exp0 arithmetic expression
     */
    public void evaluate(String exp0) {

//        expression = exp0 + " ";//To prevent indexOutBound exception.
        expression = exp0;
        int LENGTH = expression.length();

        MyStack<Character> oStack = new MyStack<>();
        MyStack<Integer> iStack = new MyStack<>();

        for (int index = 0; index < LENGTH; index++) {

            Character ch = expression.charAt(index);

            if (Character.isDigit(ch)) {
                String tmp = "" + ch;

                while ((index + 1) < LENGTH && Character.isDigit(expression.charAt(index + 1))) {
                    tmp += expression.charAt(++index);
                }
                iStack.push(Integer.parseInt(tmp));
            } else if (operators.containsKey(ch)) {

                boolean hasHighPriority = hasHighPriority(ch, oStack);

                if (ch.equals('(')) {
                    oStack.push(ch);
                } else if (ch.equals(')')) {
                    while ((!oStack.isEmpty()) && !oStack.lookUp().equals('(')) {
                        calculate(oStack, iStack);
                    }
                    if (!oStack.isEmpty()) {
                        oStack.pop();
                    }
                } else {
                    if (!hasHighPriority) {
                        do {
                            calculate(oStack, iStack);
                        } while (!hasHighPriority(ch, oStack));
                    }
                    //Common operation for both hasHighPriority and !hasHighPriority
                    oStack.push(ch);
                }
            } else {
                sopln("The character is neither a digit or a valid operator.");
            }
        }
        while (!oStack.isEmpty() && !iStack.isEmpty()) {
            calculate(oStack, iStack);
        }
    }

    /**
     * To perform a calculation, regarding to two operants and one operator
     *
     * @param oStack the MyStack instance storing operators that need to be
     * dealt with
     * @param iStack the MyStack instance storing integer operants that need to
     * be dealt with
     * @return true is calculation successes
     */
    private boolean calculate(MyStack<Character> oStack, MyStack<Integer> iStack) {

        Objects.nonNull(oStack);
        Objects.nonNull(iStack);
        if (iStack.isEmpty() || oStack.isEmpty()) {
            sopln("There is no operator or operant for calculation.");
            return false;
        }

        Integer operant2 = iStack.pop();
        Integer operant1 = iStack.pop();
        char operator = oStack.pop();

        switch (operator) {
            case '+':
                result = operant1 + operant2;
                break;
            case '-':
                result = operant1 - operant2;
                break;
            case '*':
                result = operant1 * operant2;
                break;
            case '/':
                if (operant2 == 0) {
                    sopln("/ by zero happens");
                    result = Integer.MIN_VALUE;
                } else {
                    result = operant1 / operant2;
                }
                break;
            case '%':
                if (operant2 == 0) {
                    sopln("/ by zero happens");
                    result = Integer.MIN_VALUE;
                } else {
                    result = operant1 % operant2;
                }
                break;
            case '^':
                result = (int) Math.pow(operant1, operant2);
                break;
            default:
                sopln("Unknown operator " + operator);
                return false;
        }
        iStack.push(result);
        return true;
    }

    /**
     * @return the arithmetic expression followed by the equal sign and then the
     * result
     */
    @Override
    public String toString() {
        return String.format("%s%s%d", expression, " = ", result);
    }

    /**
     * Check is the current Operator has a higher Priority than the operator on
     * top of the oStack
     *
     * @param ch the current operator
     * @param oStack the MyStack instance storing operators that need to be
     * dealt with
     * @return true is the current operator is higher priority than the top
     * operator of oStack regarding
     */
    private boolean hasHighPriority(Character ch, MyStack<Character> oStack) {
        Objects.nonNull(oStack);
        if (oStack.isEmpty()) {
            return true;
        } else {
            return getPriority(ch) > getPriority(oStack.lookUp());
        }
    }

    /**
     * Get the priority of given operator
     *
     * @param ch the operator whose priority is wanted
     * @return the priority of the given operator, MIN_VALUE if the operator
     * does not exist in the operator HashMap
     */
    private int getPriority(Character ch) {
        Objects.nonNull(ch);
        if (operators.containsKey(ch)) {
            return (int) operators.get(ch);
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * A simplified System.out.println(Object x)
     *
     * @param obj the object needs to be printed
     */
    public static void sopln(Object obj) {
        out.println(obj);
    }

}
