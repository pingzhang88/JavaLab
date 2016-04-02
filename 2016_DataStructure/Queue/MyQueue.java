import static java.lang.System.out;

/**
 * Implement a Generic Queue using a LinkedList
 *
 * March 2016
 *
 * @author PING ZHANG
 * @param <T> the type of elements held in this Queue
 */
public class MyQueue<T> {

    /**
     * Inner class represents the element of MyQueue
     *
     * @param <T> the type of elements held in this Node
     */
    private class Node<T> {

        private final T value;
        private Node<T> link;

        /**
         * the zero-parameter constructor setting up an empty Node
         */
        public Node() {
            this.value = null;
            this.link = null;
        }

        /**
         * constructor accepts a T value
         *
         * @param v the initial value for data member value
         */
        public Node(T v) {
            this.value = v;
            this.link = null;
        }

    }

    private Node<T> first;
    private Node<T> last;
    private int count;

    /**
     * The zero-parameter constructor setting up an empty queue
     */
    public MyQueue() {
        first = last = null;
        count = 0;
    }

    /**
     * Inserts the specified element into this queue
     *
     * @param t the element to add
     */
    public void addToQueue(T t) {
        Node<T> newNode = new Node<T>(t);
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last = last.link = newNode;
        }
        count++;
    }

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue or null if this queue is empty
     */
    public T deleteFromQueue() {
        if (isEmpty()) {
            sopln("Queue is empty. Cannot delete an element ");
            return null;
        } else {
            T tmp = first.value;
            if (count == 1) {
                clearQueue();
            } else {
                first = first.link;
                count--;
            }
            return tmp;
        }
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     *
     * @return the head of this queue, or null if this queue is empty
     */
    public T lookUp() {
        if (isEmpty()) {
            sopln("Queue is empty.");
            return null;
        } else {
            return first.value;
        }
    }

    /**
     * Reset the queue to being empty
     */
    public void clearQueue() {
        first = last = null;
        count = 0;
    }

    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * @return the number of elements in the queue
     */
    public int size() {
        return count;
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
