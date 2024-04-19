package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        return 0;
    }

    public int calculateSpecialPrice() {
        return 0;
    }
}
