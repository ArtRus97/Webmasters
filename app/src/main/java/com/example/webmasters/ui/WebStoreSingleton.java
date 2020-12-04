package com.example.webmasters.ui;

import android.content.Context;

import com.example.webmasters.models.webstore.CartItem;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.services.FirebaseService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class WebStoreSingleton {
    // static variable single_instance of type Singleton
    private static WebStoreSingleton mInstance = null;
    public final HashMap<String, Product> mProducts = new HashMap<>();
    public final HashMap<String, CartProduct> mCart = new HashMap<>();

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
        Gson gson = new Gson();
        Product product = mProducts.get(productId);
        CartProduct cartProduct = gson.fromJson(gson.toJson(product), CartProduct.class);
        cartProduct.setAmount(numItems);
        mCart.put(productId, cartProduct);
    }

    public void addToCartD(String productId, int numItems) {
        Gson gson = new Gson();
        Product product = mProducts.get(productId);
        CartProduct cartProduct = gson.fromJson(gson.toJson(product), CartProduct.class);
        cartProduct.setAmount(numItems);
        mCart.put(productId, cartProduct);
        (new FirebaseService()).addToCart(cartProduct);
    }

    public void removeFromCart(String productId) {
        mCart.remove(productId);
        (new FirebaseService()).removeFromCart(productId);
    }

    public List<CartProduct> getCart() {
        return new ArrayList<>(mCart.values());
    }

    public void getCartD(Consumer<List<CartProduct>> handler) {
        (new FirebaseService()).getCart(cart -> {
            for (CartProduct cartProduct : cart)
                mCart.put(cartProduct.getId(), cartProduct);
            handler.accept(new ArrayList<>(mCart.values()));
        });
    }

    // static method to create instance of Singleton class
    public static synchronized WebStoreSingleton getInstance(Context context) {
        // To ensure only one instance is created
        if (mInstance == null)
            mInstance = new WebStoreSingleton(context);
        return mInstance;
    }
}
