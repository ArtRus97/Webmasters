package com.example.webmasters.models.webstore;

import org.json.JSONObject;

import java.net.URL;

public class Product {
    private String mId;
    private String mName;
    private String mDescription;
    private String mCategory;
    private float mPrice;
    private String mImageUrl;

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getCategory() {
        return mCategory;
    }

    public Float getPrice() {
        return mPrice;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    /**
     * fromJSON parses the given product data from JSON to Java object.
     * @param jsonProduct (JSONObject)
     * @return the parsed product as an object.
     */
    static public Product fromJSON(JSONObject jsonProduct) {
        Product product = new Product();
        product.mId = jsonProduct.optString("id");
        product.mName = jsonProduct.optString("name", "Unknown");
        product.mDescription = jsonProduct.optString("description", "Missing");
        product.mCategory = jsonProduct.optString("category", "Unknown");
        product.mPrice = (float) jsonProduct.optDouble("price", 0.0);
        product.mImageUrl = jsonProduct.optString("imageUrl", "");
        return product;
    }
}
