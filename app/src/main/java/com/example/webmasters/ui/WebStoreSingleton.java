package com.example.webmasters.ui;

import android.content.Context;

import android.util.Log;
import com.example.webmasters.models.webstore.CartItem;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.services.FirebaseService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;

public class WebStoreSingleton {
    final private String TAG = "WebStoreSingleton";
    private static WebStoreSingleton mInstance = null;
    private final List<Product> mProducts = new ArrayList<>();
    private List<CartProduct> mCart = null;
    private FirebaseService mFirebase = new FirebaseService();

    // Private constructor to restrict instances of this class.
    private WebStoreSingleton() {
    }

    /**
     * getProducts reads all available products from the database.
     *
     * @param callback being called after products data is available.
     */
    public void getProducts(Consumer<List<Product>> callback) {
        if (mProducts.isEmpty()) {
            // Fetch products from database if they have not been fetched.
            mFirebase.getProducts(products -> {
                mProducts.addAll(products);
                callback.accept(mProducts);
            });
        } else {
            // In case products have been already fetched.
            callback.accept(mProducts);
        }
    }


    public void addToCart(String productId, int numItems, Consumer<CartProduct> callback) {
        getCartProduct(productId, cartProduct -> {
            // Create new card product based on the id if one does not exist.
            if (cartProduct == null) {
                Log.d(TAG, "Adding a new product into shopping cart...");
                // Convert product to CartProduct.
                Gson gson = new Gson();
                Product product = getProduct(productId);
                cartProduct = gson.fromJson(gson.toJson(product), CartProduct.class);
                // Add new product.
                mCart.add(cartProduct);
            } else {
                Log.d(TAG, "Product has been already added into shopping cart!");
            }
            // Update the number of items.
            cartProduct.setAmount(numItems + cartProduct.getAmount());
            // Store new cart product to database.
            mFirebase.addToCart(cartProduct, product -> {
                if (callback != null) callback.accept(product);
            });
        });
    }

    public void removeFromCart(Product product, Consumer<Integer> callback) {
        mFirebase.removeFromCart((CartProduct)product, removedProduct -> {
            int position = mCart.indexOf(product);
            mCart.remove(removedProduct);
            if (callback != null) callback.accept(position);
        });
    }

    public void clearCart(Consumer<Void> callback) {
        mCart.forEach(product -> {
            removeFromCart(product, product1 -> {
                if (mCart.isEmpty()) callback.accept(null);
            });
        });
    }

    public final void getCart(Consumer<List<CartProduct>> handler) {
        if (mCart == null) {
            mCart = new ArrayList<>();
            mFirebase.getCart(cartProducts -> {
                mCart.addAll(cartProducts);
                handler.accept(mCart);
            });
        } else {
            handler.accept(mCart);
        }
    }

    public Product getProduct(String productId) {
        return mProducts
                .stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }


    public void getCartProduct(String productId, Consumer<CartProduct> callback) {
        getCart(cartProducts -> {
            CartProduct cartProduct = cartProducts.stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst()
                    .orElse(null);
            callback.accept(cartProduct);
        });
    }


    // static method to create instance of Singleton class
    public static synchronized WebStoreSingleton getInstance() {
        // To ensure only one instance is created
        if (mInstance == null)
            mInstance = new WebStoreSingleton();
        return mInstance;
    }
}
