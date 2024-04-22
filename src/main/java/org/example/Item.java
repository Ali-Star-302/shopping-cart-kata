package org.example;

/**
 * Represents an item in the cart, read in from a JSON file
 */
public class Item {
    private String code;
    private int quantity;

    /**
     * Returns the code which identifies the item
     * @return The item code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the number of the item in the cart
     * @return Item quantity
     */
    public int getQuantity() {
        return quantity;
    }
}
