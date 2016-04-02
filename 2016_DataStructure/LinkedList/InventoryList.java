import java.util.Objects;

/**
 * Implements a ordered LinkedList of Product Item objects.
 *
 * Feb 2016
 *
 * @author Ping Zhang
 */
public class InventoryList {

    ItemNode first;
    ItemNode last;

    public InventoryList() {
        first = null;
        last = null;
    }

    public ItemNode getLast() {
        if (first == null) {
            return null;
        }
        if (first.itemNode == null) {
            return first;
        }
        ItemNode preV = first;
        while (preV.itemNode != null) {
            preV = preV.itemNode;
        }
        return preV;
    }

    /**
     * Copy Constructor
     *
     * @param list0 the original InventoryList to be copied
     */
    public InventoryList(InventoryList list0) {

        if (list0 == null) {
            System.out.println("list0 should not be null");
            first = null;
            return;
        }

        ItemNode oldFirst = list0.first;
        ItemNode oldPresent = null;
        if (oldFirst != null) {
            first = new ItemNode(new Item(oldFirst.data));
            oldPresent = oldFirst.itemNode;
        }

        ItemNode present = first;

        while (oldPresent != null) {

            ItemNode previous = present;

            present = new ItemNode(new Item(oldPresent.data));

            previous.itemNode = present;

            oldPresent = oldPresent.itemNode;

        }

    }

    /**
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * To add a new Item object to the list
     *
     * @param item new Item object to add to the list.
     * @return true when the item is added to the list successfully
     */
    public boolean addItem(Item item) {

        Objects.requireNonNull(item);

        String itemNumber = item.getItemNumber();
        ItemNode previous = thePrevious(itemNumber);

        ItemNode present = thePresent(previous);

        if (findItem(present, itemNumber) != null) {
            System.out.println("Adding failure, " + itemNumber);
            return false;
        }

        ItemNode newNode = new ItemNode(item);

        if (present == first) {
            first = newNode;
        }
        previous.itemNode = newNode;
        newNode.itemNode = present;

        System.out.println(item.getItemNumber() + " added to the list.");
        return true;

    }

    /**
     * To check whether a item with specific itemNumber is the member of a list
     *
     * @param itemNumber the itemNumber to search
     * @return Item object found
     */
    public Item findItem(String itemNumber) {

        Objects.requireNonNull(itemNumber);

        ItemNode previous = thePrevious(itemNumber);
        ItemNode present = thePresent(previous);

        return findItem(present, itemNumber);
    }

    /**
     * A helper function for {@link #findItem(java.lang.String) }
     * It also used by the other methods to add, delete, change the list nodes.
     *
     * @param present the smallest ItemNode, which is bigger than or equal the
     * target ItemNode
     * @param itemNumber the item number to search
     * @return Item object found
     */
    private Item findItem(ItemNode present, String itemNumber) {

        if (present != null && present.data.getItemNumber().compareTo(itemNumber) == 0) {
            System.out.println(itemNumber + " found.");
            return present.data;
        }
        System.out.println(itemNumber + " not found.");
        return null;
    }

    /**
     * @param itemNumber the itemNumber of the target item
     * @return the biggest ItemNode, which is smaller than the target ItemNode,
     * should not return null.
     */
    private ItemNode thePrevious(String itemNumber) {

        Objects.requireNonNull(itemNumber);

        ItemNode present = first;
        ItemNode previous = new ItemNode();
        previous.itemNode = first;

        while (present != null && present.data.getItemNumber().compareTo(itemNumber) < 0) {
            previous = present;
            present = present.itemNode;
        }
        return previous;
    }

    /**
     * @param previous the biggest ItemNode, which is smaller than the target
     * ItemNode
     * @return the smallest ItemNode, which is bigger than or equal the target
     * ItemNode
     */
    private ItemNode thePresent(ItemNode previous) {
        Objects.requireNonNull(previous);
        return previous.itemNode;
    }

    /**
     * A method to remove the Item with the specified itemNumber from the list.
     *
     * @param itemNumber the item's number to delete
     * @return true if the specified Item is found and removed from the list.
     */
    public boolean deleteItem(String itemNumber) {

        Objects.requireNonNull(itemNumber);

        ItemNode previous = thePrevious(itemNumber);

        ItemNode present = thePresent(previous);

        if (findItem(present, itemNumber) == null) {
            System.out.println("Delete Failure, " + itemNumber + " not exists in the list.");
            return false;
        }

        if (present == first) {
            first = present.itemNode;
        }
        previous.itemNode = present.itemNode;

        System.out.println(itemNumber + " is Deleted.");
        return true;

    }

    public boolean deleteFromList(String value) {
        ItemNode present = first, previous = new ItemNode();

        while ((present != null) && (present.data.getItemNumber().compareTo(value) < 0)) {
            previous = present;
            present = present.itemNode;
        }

        if (present == null) {
            System.out.println("Cannot delete " + value
                    + " from the list. It is not in the list.");
            return false;
        } else {
            if (present.data.getItemNumber().equals(value)) {

                if (present == first) {
                    first = present.itemNode;
                } else {
                    previous.itemNode = present.itemNode;
                }
                return true;

            } else {
                return false;
            }
        }
    }

    /**
     * To change the itemQuantity of the specified item to specified Quantity.
     *
     * @param itemNumber the itemNumber, whose Item's quantity is to be changed.
     * @param extraQuantity the quantity to be adjusted, could be negative or
     * positive
     * @return true if the adjustment is accomplished as request
     */
    public boolean adjustQuantity(String itemNumber, int extraQuantity) {

        Objects.requireNonNull(itemNumber);

        Item item = findItem(itemNumber);
        if (item != null) {
            int addition = item.getStockQuantity() + extraQuantity;
            if (addition <= 0) {
                System.out.println("adjustQuantity() Failed, the Addition <=0");
                return false;
            } else {
                item.setStockQuantity(addition);
                return true;
            }
        }
        System.out.println(itemNumber + " does Not exists in the list.");
        return false;
    }

    /**
     * To change the specified item's price
     *
     * @param itemNumber the itemNumber, whose Item's quantity is to be changed.
     * @param newPrice the new price to be reset to.
     * @return true if the change succeeds.
     */
    public boolean changeUnitPrice(String itemNumber, double newPrice) {

        Objects.requireNonNull(itemNumber);

        Item item = findItem(itemNumber);
        if (item != null) {
            if (newPrice <= 0) {
                System.out.println("changeUnitPrice() Failed, the new price <=0");
                return false;
            } else {
                item.setUnitPrice(newPrice);
                return true;
            }
        }
        System.out.println(itemNumber + " does Not exists in the list.");
        return false;

    }

    @Override
    public String toString() {
        ItemNode present = first;
        String tmp = "";
        while (present != null) {
            tmp += present.data;
            tmp += "\n";

            present = present.itemNode;
        }
        return tmp;
    }

    /**
     * Returns the content of the list but in decreasing order of item number.
     *
     * @return the String that concatenates all the data for each item
     */
    public String decreasingOrder() {
        return decreasingOrder(first);
    }

    /**
     * A helper for {@link #decreasingOrder() }
     */
    private String decreasingOrder(ItemNode present) {
        if (present == null) {
            return "";
        }
        return decreasingOrder(present.itemNode) + "\n" + present.data;
    }

    /**
     * A class to set up the content of node in the list.
     */
    public class ItemNode {

        Item data;
        ItemNode itemNode;

        public ItemNode() {
            data = null;
            itemNode = null;
        }

        public ItemNode(Item item) {
            data = new Item(item);
            itemNode = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }

    }

    public static InventoryList mergeLists(InventoryList list1, InventoryList list2) {
        Objects.requireNonNull(list1);
        Objects.requireNonNull(list2);

        if (list1.isEmpty()) {
            return list2;
        }
        if (list2.isEmpty()) {
            return list1;
        }
        InventoryList list3 = new InventoryList();
        ItemNode last = list3.first;
        ItemNode preS1 = list1.first;
        ItemNode preS2 = list2.first;

        while (preS1 != null && preS2 != null) {
            ItemNode feed = (preS1.data.compareTo(preS2.data) < 0) ? preS1 : preS2;
            list3.addItem(new Item(feed.data));
            if (feed == preS1) {
                preS1 = preS1.itemNode;
            } else if (feed == preS2) {
                preS2 = preS2.itemNode;
            }
        }
        if (preS1 != null) {
            list3.getLast().itemNode = preS1;
        } else if (preS2 != null) {
            list3.getLast().itemNode = preS2;
        }
        return list3;

    }

}
