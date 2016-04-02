import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Implements a Generic Stack using an ArrayList
 * 
 * Feb 2016
 *
 * @author PING ZHANG
 * @param <T>
 */
public class MyStack<T> {

    private final ArrayList<T> list;

    /**
     * Zero-parameter constructor: instantiate an empty stack
     */
    public MyStack() {
        list = new ArrayList<T>();
    }

    /**
     * Copy Constructor: return a shallow copy. If T is a final Class, the
     * element of the new list won't be affected by any modification to
     * otherStack.
     *
     * @param otherStack The MyStack object to be copied.
     */
    public MyStack(MyStack<T> otherStack) {
        Objects.nonNull(otherStack);

        list = new ArrayList<T>();

        if (otherStack.isEmpty()) {
            sopln("OtherStack is empty.");
            return;
        }

        for (int i = 0; i < otherStack.getSize(); i++) {
            list.add(otherStack.list.get(i));
        }
    }

    /**
     * @return true if list doesn't have any element
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Pushes an object onto the top of this stack.
     *
     * @param t the item to be pushed into this stack.
     */
    public void push(T t) {
        list.add(t);
    }

    /**
     * Removes the object at the top of this stack and returns that object as
     * the value of this function.
     *
     * @return The object at the top of this stack.
     */
    public T pop() {
        if (getSize() > 0) {
            return list.remove(getSize() - 1);
        } else {
            return null;
        }
    }

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     *
     * @return the object at the top of this stack.
     */
    public T lookUp() {
        if (getSize() > 0) {
            return list.get(getSize() - 1);
        } else {
            return null;
        }
    }

    /**
     * @return size of the stack
     */
    public int getSize() {
        return list.size();
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
