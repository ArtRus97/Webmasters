package com.example.webmasters.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.services.FirebaseService;
import com.example.webmasters.services.ProductApi;
import com.example.webmasters.services.FirebaseService;
import com.example.webmasters.ui.web_store.CartActivity;
import com.example.webmasters.ui.web_store.ProductActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class WebStoreSingleton {
    // static variable single_instance of type Singleton
    private static WebStoreSingleton mInstance = null;
    public final HashMap<String, Product> mProducts = new HashMap<>();
    public ArrayList<Product> cart = new ArrayList<>();

    // private constructor restricted to this class itself
    private WebStoreSingleton(Context context) {
    }

    public void getProducts(Consumer<List<Product>> handler) {
        // Fetch products from database if they have not been fetched.
        if (mProducts.isEmpty()) {
            (new FirebaseService()).getProducts(products -> {
                for (Product product : products)
                    mProducts.put(product.getId(), product);
                handler.accept(new ArrayList<>(mProducts.values()));
            });
        }
        // Otherwise just return products.
        else
            handler.accept(new ArrayList<>(mProducts.values()));

    }

    public Product getProduct(String id) {
        return mProducts.get(id);
    }

    public void addToCart(String productId, int numItems) {
        for (int position=0; position <= numItems; position++)
                cart.add(mProducts.get(productId));
    }

    public List<Product> getCart() {
        return cart;
    }


    // static method to create instance of Singleton class
    public static synchronized WebStoreSingleton getInstance(Context context) {
        // To ensure only one instance is created
        if (mInstance == null)
            mInstance = new WebStoreSingleton(context);
        return mInstance;
    }
}
