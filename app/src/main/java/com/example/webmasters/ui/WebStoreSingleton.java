package com.example.webmasters.ui;

import java.util.ArrayList;

public class WebStoreSingleton {
    // static variable single_instance of type Singleton
    private static WebStoreSingleton single_instance = null;

    public ArrayList<Product> products = new ArrayList<>();

    // private constructor restricted to this class itself
    private WebStoreSingleton() {

        for (int i=1; i<4; i++) {
            Product product = new Product();
            product.title = "Title" + i;
            product.desc = "Product description from singleton";
            product.price = i + 0.0;

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
        Integer id;
        String title;
        String desc;
        Double price;

        public Integer getId() { return id; }
        public String getTitle() { return title; }
        public String getDesc() { return desc; }
        public Double getPrice() { return price; }
    }
}
