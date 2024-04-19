package org.example;

public class SpecialPrice {
    private final int quantity;
    private final int price;

    public SpecialPrice (int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
