package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * This class handles the logic of storing products and the calculation of the subtotal for a given set of items
 */
public class CheckoutSystem {
    private HashMap<String, Product> productMap;
    private List<Item> items;

    public CheckoutSystem() {
        this.productMap = new HashMap<>();
    }

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

    public void printItems() {
        for (Item item : items) {
            System.out.println("Code: " + item.getCode() + ", Quantity: " + item.getQuantity());
        }
    }

    public void addProduct(Product newProduct) {
        productMap.put(newProduct.getItemCode(), newProduct);
    }

    public int calculateSubTotal() {
        int subtotal = 0;

        for (Item item : items) {
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
}
