package com.example.webmasters.models.webstore;

import org.json.JSONObject;

public class Product {
    private String mId;
    private String mName;
    private String mDescription;
    private String mCategory;
    private float mPrice;
    private String mImageUrl;

    final public void setId(final String id) {
        this.mId = id;
    }

    final public void setName(final String name) {
        this.mName = name;
    }

    final public void setDescription(final String description) {
        this.mDescription = description;
    }

    final public void setCategory(final String category) {
        this.mCategory = category;
    }

    public void setPrice(final float price) {
        this.mPrice = price;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    final public String getId() {
        return mId;
    }

    final public String getName() {
        return mName;
    }

    final public String getDescription() {
        return mDescription;
    }

    final public String getCategory() {
        return mCategory;
    }

    public float getPrice() {
        return mPrice;
    }

    final public String getImageUrl() {
        return mImageUrl;
    }

    /**
     * fromJSON parses the given product data from JSON to Java object.
     *
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
