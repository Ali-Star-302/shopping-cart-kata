package org.example;

/**
 * The main class used to run the program
 */
public class Main {
    /**
     * The main method of the program
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        CheckoutSystem checkoutSystem = new CheckoutSystem();
        checkoutSystem.addProduct(new Product("A", 50, new SpecialPrice(3, 140)));
        checkoutSystem.addProduct(new Product("B", 35, new SpecialPrice(2, 60)));
        checkoutSystem.addProduct(new Product("C", 25, null));
        checkoutSystem.addProduct(new Product("D", 12, null));

        checkoutSystem.consumeDataSource("/data-set-1.json");

        System.out.println("Cart sub total: " + checkoutSystem.calculateSubTotal());
    }
}