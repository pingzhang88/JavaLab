import static java.lang.System.out;

/**
 * A class represents product Item.
 *
 * Feb 2016
 *
 * @author Ping Zhang
 */
public class Item implements Comparable<Item> {

    private String itemNumber;
    private String itemDescription;
    private int stockQuantity;
    private double unitPrice;

    public static void main(String[] args) {

    }

    public Item() {
        setItem("", "", 0, 0.0);
    }

    public Item(String itemNumber, String itemDescription, int stockQuantity, double unitPrice) {
        setItem(itemNumber, itemDescription, stockQuantity, unitPrice);
    }

    public Item(Item item) {
        this.itemNumber = item.itemNumber;
        this.itemDescription = item.itemDescription;
        this.stockQuantity = item.stockQuantity;
        this.unitPrice = item.unitPrice;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setItemNumber(String itemNumber) {
        if (itemNumber == null) {
            out.println("ItemNumber should not be null");
        }
        this.itemNumber = itemNumber;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public final void setItem(String itemNumber, String itemDescription, int stockQuantity, double unitPrice) {
        if (itemNumber == null) {
            out.println("ItemNumber should not be null");
        }
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%10d%10.2f", itemNumber, itemDescription, stockQuantity, unitPrice);
    }

    /**
     * To check equality of two Item objects by their itemNumbers' String value
     *
     * @return Returns true if the item number of the calling object is equal to
     * the item number of the parameter object
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return this.itemNumber.equals(other.itemNumber);
    }

    /**
     * To compare two Item objects by their itemNumbers
     *
     * @param item
     * @return the value of itemNumber's compareTo() method
     */
    @Override
    public int compareTo(Item item) {
        return this.itemNumber.compareTo(item.getItemNumber());
    }

}
