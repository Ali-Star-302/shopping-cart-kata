package org.example;

/**
 * Represents a specific product and it's attributes
 */
public class Product {
    private final String itemCode;
    private final int unitPrice;
    private final SpecialPrice specialPrice;

    /**
     * Creates a new product which stores information about products which could be added to the cart
     * @param itemCode The identifier for the product
     * @param unitPrice The price of a single product
     * @param specialPrice An offer associated with the product
     */
    public Product(String itemCode, int unitPrice, SpecialPrice specialPrice) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.specialPrice = specialPrice;
    }

    /**
     * Returns the code which identifies the product
     * @return Item code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Returns the price of a single product
     * @return Unit price
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    /**
     * Returns the offer associated with this product
     * @return Special price
     */
    public SpecialPrice getSpecialPrice() {
        return specialPrice;
    }
}
