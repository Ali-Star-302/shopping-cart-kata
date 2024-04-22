package org.example;

/**
 * Represents an item in the cart, read in from a JSON file used for object mapping
 */
class Item {
    private String code;
    private int quantity;

    public String getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }
}
