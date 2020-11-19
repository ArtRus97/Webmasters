package com.example.webmasters.adapters;

import android.content.Context;
import com.example.webmasters.models.webstore.Product;

import java.util.List;

public class CartAdapter extends ProductAdapter {

    public CartAdapter(Context context, List<Product> products) {
        super(context, products);
    }
}
