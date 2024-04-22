package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Handles the logic of storing products and the calculation of the subtotal for a given set of items
 */
public class CheckoutSystem {
    private HashMap<String, Product> productMap;
    private List<Item> items;

    /**
     * Creates a CheckoutSystem object and instantiates its attributes
     */
    public CheckoutSystem() {
        this.productMap = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Takes in a JSON file containing the items in the shopping cart and stores them as Item objects
     * @param filename The name of the data source, expected to be a JSON file
     */
    public void consumeDataSource(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = Main.class.getResourceAsStream(filename);
            items = objectMapper.readValue(inputStream, new TypeReference<List<Item>>() {});
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the items stored in the shopping cart, used for debugging
     */
    public void printShoppingCart() {
        for (Item item : items) {
            System.out.println("Code: " + item.getCode() + ", Quantity: " + item.getQuantity());
        }
    }

    /**
     * Adds information about a product to the system
     * @param newProduct The product to be added
     */
    public void addProduct(Product newProduct) {
        productMap.put(newProduct.getItemCode(), newProduct);
    }

    /**
     * Calculates the total price of the shopping cart taking into account special prices
     * @return The subtotal of the cart
     */
    public int calculateSubTotal() {
        int subtotal = 0;

        for (Item item : items) {
            // Skip the item if it's invalid
            if (!isValidItem( item))
                continue;

            Product currentProduct = productMap.get(item.getCode());
            // Just add the regular price if the product doesn't have a special price
            if (currentProduct.getSpecialPrice() == null) {
                subtotal += item.getQuantity() * currentProduct.getUnitPrice();
            }
            else { //Special items
                subtotal += calculateSpecialPrice(currentProduct, item.getQuantity());
            }
        }

        return subtotal;
    }

    /**
     * Calculates the price of products that have a special price
     * @param product The product with a special price
     * @param quantity How many of the product are in the cart
     * @return The total price of the inputted product
     */
    private int calculateSpecialPrice(Product product, int quantity) {
        int totalPrice = 0;
        SpecialPrice specialPrice = product.getSpecialPrice();
        int offerQuantity = specialPrice.getQuantity();
        int offerPrice = specialPrice.getPrice();

        if (quantity >= offerQuantity) {
            int numSpecialItemGroups = (int) Math.floor(quantity / offerQuantity);
            totalPrice += numSpecialItemGroups * offerPrice;
            quantity -= numSpecialItemGroups * offerQuantity;
        }
        totalPrice += quantity * product.getUnitPrice();

        return totalPrice;
    }

    /**
     * Checks if the given item from the cart is valid
     * @param item The item to check
     * @return Boolean indicating whether the item is valid
     */
    private boolean isValidItem(Item item) {
        // Handle a product in the data source not being in the system
        if (productMap.get(item.getCode()) == null) {
            System.out.println("Invalid product in cart: " + item.getCode());
            return false;
        }
        else if (item.getQuantity() <= 0) {
            System.out.println("Product with negative quantity: " + item.getCode());
            return false;
        }

        return true;
    }
}
