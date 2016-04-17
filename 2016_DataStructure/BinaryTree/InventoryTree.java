import static java.lang.System.out;
import java.util.Objects;

/**
 * Implementing a Binary Search Tree using links.
 * April 2016
 * @author Ping Zhang
 */
public class InventoryTree {

    private class TreeNode {

        private Item data;
        private TreeNode leftLink;
        private TreeNode rightLink;

        /**
         * Zero-parameter constructor
         */
        TreeNode() {
        }

        /**
         * Constructor which accepts an Item data
         *
         * @param data
         */
        TreeNode(Item data) {
            this.data = new Item(data);
            this.leftLink = null;
            this.rightLink = null;
        }

    }

    private TreeNode root;

    /**
     * Zero-parameter constructor to initialize an empty tree
     */
    public InventoryTree() {
        root = null;
    }

    /**
     * Constructor which initializes the root field
     *
     * @param root
     */
    public InventoryTree(TreeNode node) {
        this.root = node;
    }

    /**
     * Copy constructor
     *
     * @param oldTree the InventoryTree to be copied
     */
    public InventoryTree(InventoryTree oldTree) {
        if (oldTree != null) {
            TreeNode oldTreeRoot = oldTree.root;
            if (oldTreeRoot == null) {
                root = null;
            } else {
                root = new TreeNode();
                copyConstructorHelper(root, oldTreeRoot, '\u0000');
            }
        }
    }

    /**
     * A helper for the copy constructor (Always check logic about root when the
     * whole tree is missing.)
     *
     * @param currentNode the Node of this InventoryTree to be manipulated
     * @param oldTreeRoot the root of a subTree of the oldTree
     * @param leftOrRight A flog of left or right side
     */
    private void copyConstructorHelper(TreeNode currentNode, TreeNode oldTreeRoot, char leftOrRight) {
        if (oldTreeRoot != null) {
            if (leftOrRight == 'L') {
                currentNode = currentNode.leftLink = new TreeNode(oldTreeRoot.data);
            } else if (leftOrRight == 'R') {
                currentNode = currentNode.rightLink = new TreeNode(oldTreeRoot.data);
            } else {
                currentNode.data = new Item(oldTreeRoot.data);
            }
            copyConstructorHelper(currentNode, oldTreeRoot.leftLink, 'L');
            copyConstructorHelper(currentNode, oldTreeRoot.rightLink, 'R');
        }

    }

    /**
     * A overload helper for the copy constructor that use addItem() method
     *
     * @param oldTreeRoot the root of a subTree of the oldTree
     */
    private void copyConstructorHelper(TreeNode oldTreeRoot) {
        if (oldTreeRoot != null) {
            this.addItem(oldTreeRoot.data);
            copyConstructorHelper(oldTreeRoot.leftLink);
            copyConstructorHelper(oldTreeRoot.rightLink);
        }
    }

    /**
     * Add an Item to the binary search tree.
     *
     * @param newItem the Item to be added to the Inventory
     * @return true if the Item was added; false if the Item is already in the
     * tree (same item number).
     */
    public boolean addItem(Item newItem) {
        Objects.requireNonNull(newItem);
        String newItemNumber = newItem.getItemNumber();
        Objects.requireNonNull(newItemNumber);

        TreeNode parent = searchParent(newItemNumber);
        TreeNode current = getCurrent(newItemNumber, parent);
        if (current == null) {
            TreeNode newNode = new TreeNode(newItem);
            if (parent == null) {
                root = newNode;
            } else {
                Item parentItem = parent.data;
                if (parentItem == null) {
                    sopln("Data Corrupt: some Node has null data.");
                    return false;
                } else {
                    if (leftOrRight(newItemNumber, parentItem.getItemNumber()) == 'L') {
                        parent.leftLink = newNode;
                    } else {
                        parent.rightLink = newNode;
                    }
                }
            }
            return true;
        } else {
            sopln("Dublicate Item.");
            return false;
        }

    }

    /**
     * Find the Item with the given itemNumber.
     *
     * @param key the itemNumber of the target Item to find.
     * @return an Item object if the item with the given number is in the tree.
     * It returns null otherwise.
     */
    public Item findItem(String key) {
        Objects.requireNonNull(key);

        TreeNode parent = searchParent(key);
        TreeNode current = getCurrent(key, parent);

        if (current == null) {
            sopln(key + " not exists in the Inventory");
            return null;
        } else {
            return current.data;
        }
    }

    /**
     * delete a given Item from the tree;
     *
     * @param key the itemNumber of the Item to be deleted.
     * @return true if the Item was deleted; false if the given Item cannot be
     * found or the quantity was not zero.
     */
    public boolean deleteItem(String key) {
        Objects.requireNonNull(key);

        TreeNode parent = searchParent(key);
        TreeNode current = getCurrent(key, parent);

        if (current == null) {
            sopln(key + " not exists in the Inventory Tree. Nothing to Delete.");
            return false;
        } else {
            if (current.leftLink == null) {
                //Node to delete has no children (a leaf)
                if (current.rightLink == null) {
                    //One Node Tree
                    if (parent == null) {
                        root = null;
                    } else {
                        Item parentItem = parent.data;
                        assert (parentItem != null) : "Data Corrupt: some Node has null data.";
                        if (leftOrRight(key, parentItem.getItemNumber()) == 'L') {
                            parent.leftLink = null;
                        } else {
                            parent.rightLink = null;
                        }

                    }
                    return true;
                } //Node to delete has a right child but no left child
                else {
                    current = current.rightLink;
                    if (parent == null) {
                        root = current;
                    } else {
                        Item parentItem = parent.data;
                        assert (parentItem != null) : "Data Corrupt: some Node has null data.";
                        if (leftOrRight(key, parentItem.getItemNumber()) == 'L') {
                            parent.leftLink = current;
                        } else {
                            parent.rightLink = current;
                        }
                    }
                }

            } else {
                // Node to delete has a left child, who doesn't have a right child
                if (current.leftLink.rightLink == null) {
                    TreeNode tmp = current;
                    current = current.leftLink;
                    current.rightLink = tmp.rightLink;
                    if (parent == null) {
                        root = current;
                    } else {
                        Item parentItem = parent.data;
                        assert (parentItem != null) : "Data Corrupt: some Node has null data.";
                        if (leftOrRight(key, parentItem.getItemNumber()) == 'L') {
                            parent.leftLink = current;
                        } else {
                            parent.rightLink = current;
                        }
                    }
                } //Node to delete has a left child, who has A right child
                else {
                    if (parent == null) {
                        root = current;
                    }
                    TreeNode successorParent = current.leftLink;
                    TreeNode successor = successorParent.rightLink;
                    while (successor.rightLink != null) {
                        successor = (successorParent = successor).rightLink;
                    }
                    current.data = successor.data;
                    successorParent.rightLink = successor.leftLink;
                }
            }
            return true;
        }
    }

    /**
     * Find the parent Node of the Node of the given itemNumber, Or the proper
     * position waiting for furthing operation: addItem(),
     * findItem(),deleteItem(), etc.
     *
     * @param key the id of target Item
     * @return the parent TreeNode of the target Item or the proper position,
     * null if it is a one-node tree.
     */
    private TreeNode searchParent(String key) {
        Objects.requireNonNull(key);

        TreeNode parent = null;
        TreeNode current = root;

        String currentNumber;
        while (current != null && current.data != null
                && key.compareTo(currentNumber = current.data.getItemNumber()) != 0) {
            parent = current;
            if (key.compareTo(currentNumber) < 0) {
                current = current.leftLink;
            } else {
                current = current.rightLink;
            }
        }

        return parent;
    }

    /**
     * Get the correspondent TreeNode of the given parent Node.
     *
     * @param key the id of the current Node
     * @param parent the parent Node
     * @return the left or right Node of the parent, root when it is a one-node
     * tree.
     */
    private TreeNode getCurrent(String key, TreeNode parent) {
        if (parent == null) {
            return root;
        } else {
            String parentNumber = parent.data.getItemNumber();
            if (leftOrRight(key, parentNumber) == 'L') {
                return parent.leftLink;
            } else {
                return parent.rightLink;
            }
        }
    }

    /**
     * To decide the direction of the operation
     *
     * @param key the target Item's itemNumber
     * @param parentNumber the itemNumber of the parent Node
     * @return the direction
     */
    private char leftOrRight(String key, String parentNumber) {

        int keyComparison = key.compareTo(parentNumber);

        assert (keyComparison != 0) : "Something wrong.";
        if (keyComparison < 0) {
            return 'L';
        } else {
            return 'R';
        }
    }

    /**
     * Do an in-order traversal of the tree to set up a string with the Items in
     * increasing order; each Item should appear on a separate line
     *
     * @return the String of all the Items in ASC order.
     */
    @Override
    public String toString() {
//        return toStringHelper(root);
        return toStringVisual(root, 2);
    }

    /**
     * A helper method to concatenate the Items
     *
     * @param subTree the sub tree to be processed
     * @return
     */
    private String toStringHelper(TreeNode subTreeRoot) {
        if (subTreeRoot == null) {
            return "";
        } else {
            Item rootItem = subTreeRoot.data;
            assert (rootItem != null) : "Data Corrupt: some Node has null data.";
            return toStringHelper(subTreeRoot.leftLink)
                    + rootItem.toString() + "\n"
                    + toStringHelper(subTreeRoot.rightLink);
        }
    }

    /**
     * A helper method to generator a Visual Inventory Tree
     *
     * @param subTreeRoot the sub tree to be processed
     * @param spaceCount the spaceCount corresponding to the depth of the
     * recursion
     * @return
     */
    private String toStringVisual(TreeNode subTreeRoot, int spaceCount) {
        if (subTreeRoot == null) {
            return "";
        } else {
            Item rootItem = subTreeRoot.data;
            assert (rootItem != null) : "Data Corrupt: some Node has null data.";
            String liaison = "";
            if (subTreeRoot.leftLink != null && subTreeRoot.rightLink != null) {
                liaison = getSpaces(9 - 2) + "|";
            }
            return toStringVisual(subTreeRoot.rightLink, spaceCount + 9)
                    + getSpaces(spaceCount) + rootItem.getItemNumber() + liaison + "\n\n"
                    + toStringVisual(subTreeRoot.leftLink, spaceCount + 9);
        }
    }

    /**
     * To generate a space string
     *
     * @param spaceCount the amount of spaces
     * @return
     */
    private String getSpaces(int spaceCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < spaceCount; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    /**
     * Changes the unit price value to the given unit price.
     *
     * @param itemNumber the given data identifier
     * @param newPrice the target price for the change
     * @return true if the unit price was changed.
     */
    public boolean changeUnitPrice(String itemNumber, double newPrice) {
        Objects.requireNonNull(itemNumber);
        if (newPrice <= 0) {
            sopln("Invalid Price.");
            return false;
        } else {
            Item target = findItem(itemNumber);
            if (target == null) {
                return false;
            } else {
                target.setUnitPrice(newPrice);
                return true;
            }
        }
    }

    /**
     * Adjusts the quantity value by adding the given amount.
     *
     * @param itemNumber the given data identifier
     * @param extra the additional quantity for the adjustment
     * @return true if the quantity was adjusted and was non-negative
     */
    public boolean adjustQuantity(String itemNumber, int extra) {
        Objects.requireNonNull(itemNumber);

        Item target = findItem(itemNumber);
        if (target == null) {
            return false;
        } else {
            int newQuantity = target.getStockQuantity() + extra;
            if (newQuantity < 0) {
                sopln("New Quantity can not be negative.");
                return false;
            } else {
                target.setStockQuantity(newQuantity);
                return true;
            }
        }
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
