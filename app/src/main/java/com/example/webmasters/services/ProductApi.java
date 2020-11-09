package com.example.webmasters.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.webmasters.models.webstore.Product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * ProductAPI
 */
abstract public class ProductApi {

    /**
     * fetchProducts fetches product data from product API.
     *
     * @param context  (Context)
     * @param callback (Consumer
     */
    static public void fetchProducts(Context context, Consumer<Product[]> callback) {
        // Create a request to fetch products data.
        JsonArrayRequest productsRequest = new JsonArrayRequest(Request.Method.GET, Constants.PRODUCTS_API, null,
                // Handle successful request.
                productsJSON -> {
                    Product[] products = new Product[productsJSON.length()];
                    // Iterate over fetch product data.
                    for (int iProduct = productsJSON.length() - 1; iProduct >= 0; iProduct--) {
                        // Convert JSON product object to Java object.
                        JSONObject productJSON = productsJSON.optJSONObject(iProduct);
                        products[iProduct] = Product.fromJSON(productJSON);
                    }
                    // Execute the set callback.
                    callback.accept(products);
                },
                // Throw an error if the request fails.
                error -> {
                    throw new Error(error.getMessage());
                }
        );
        // User API service to fetch data with the request.
        ApiService.getInstance(context).addToRequestQueue(productsRequest);
    }

}
