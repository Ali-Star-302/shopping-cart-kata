package org.example;

/**
 * Holds information about the conditions to satisfy an offer on a product
 */
public class SpecialPrice {
    private final int quantity;
    private final int price;

    /**
     * Creates a new offer that can be used on a product
     * @param quantity The amount of the product required to satisfy the offer
     * @param price The price for the specified quantity of product
     */
    public SpecialPrice (int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the number of products required to satisfy the offer
     * @return Quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the total price of the products in the offer
     * @return Price
     */
    public int getPrice() {
        return price;
    }
}
