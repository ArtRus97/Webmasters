package com.example.webmasters.ui;

import android.content.Context;
import android.widget.Toast;

import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.services.ProductApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class WebStoreSingleton {
    // static variable single_instance of type Singleton
    private static WebStoreSingleton mInstance = null;
    private Context mContext;
    public final HashMap<String, Product> mProducts = new HashMap<>();

    // private constructor restricted to this class itself
    private WebStoreSingleton(Context context) {
        mContext = context;
    }

    public void getProducts(Consumer<List<Product>> handler) {
        if (mProducts.isEmpty())
            ProductApi.fetchProducts(mContext, products -> {
                // Fetch product data from API.
                for (Product product : products)
                    mProducts.put(product.getId(), product);
                handler.accept(new ArrayList<>(mProducts.values()));
            });
        // Otherwise just return the fetched products.
        else
            handler.accept(new ArrayList<>(mProducts.values()));
    }

    public Product getProduct(String id) {
        return mProducts.get(id);
    }

    // static method to create instance of Singleton class
    public static synchronized WebStoreSingleton getInstance(Context context) {
        // To ensure only one instance is created
        if (mInstance == null)
            mInstance = new WebStoreSingleton(context);
        return mInstance;
    }
}
