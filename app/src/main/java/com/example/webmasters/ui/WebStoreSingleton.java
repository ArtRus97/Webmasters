package com.example.webmasters.ui;

import java.util.ArrayList;

public class WebStoreSingleton {
    // static variable single_instance of type Singleton
    private static WebStoreSingleton single_instance = null;

    public ArrayList<String> productNames = new ArrayList<>();
    public String desc;
    public Double[] prices;

    public ArrayList<Product> products = new ArrayList<>();

    // private constructor restricted to this class itself
    private WebStoreSingleton() {

        productNames.add("Title1");
        productNames.add("Title2");
        productNames.add("Title3");

        desc = "Product description from singleton";

        prices[0] = 1.0;
        prices[1] = 2.0;
        prices[2] = 3.0;

        for (int i=0; i<3; i++) {
            Product product = new Product();
            product.title = "Title" + i;
            product.desc = desc;
            product.price = i + 1.0;

            products.add(product);
        }
    }

    // static method to create instance of Singleton class
    public static WebStoreSingleton Singleton() {
        // To ensure only one instance is created
        if (single_instance == null) {
            single_instance = new WebStoreSingleton();
        }
        return single_instance;
    }

    public static class Product {
        String title;
        String desc;
        Double price;

        public String getTitle() { return title; }
        public String getDesc() { return desc; }
        public Double getPrice() { return price; }
    }
}
