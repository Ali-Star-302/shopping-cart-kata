package org.example;



public class Main {
    public static void main(String[] args) {
        Product productA = new Product("A", 50, new SpecialPrice(3, 140));
        Product productB = new Product("B", 35, new SpecialPrice(2, 60));
        Product productC = new Product("C", 25, null);
        Product productD = new Product("D", 12, null);

        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.addProduct(productA);
        checkoutSystem.addProduct(productB);
        checkoutSystem.addProduct(productC);
        checkoutSystem.addProduct(productD);

        checkoutSystem.consumeDataSource("/data-set-1.json");

        System.out.println(checkoutSystem.calculateSubTotal());
    }
}