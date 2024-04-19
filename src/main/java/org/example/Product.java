package org.example;

public class Product {
    private final String itemCode;
    private final int unitPrice;
    private final SpecialPrice specialPrice;

    public Product(String itemCode, int unitPrice, SpecialPrice specialPrice) {
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.specialPrice = specialPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public SpecialPrice getSpecialPrice() {
        return specialPrice;
    }
}
