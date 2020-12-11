package com.example.webmasters.converters;

import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.models.webstore.Product;

import java.util.Locale;

public class ProductConverter {

    public static String formatPrice(final float price) {
        return String.format(Locale.ENGLISH, "%.2f€", price);
    }

    public static String formatDescription(final String description) {
        int descLength = Math.min(description.length(), 80);
        return description.substring(0, descLength) + "...";
    }

    public static String formatAmount(Product product) {
        return (product instanceof CartProduct) ? ((CartProduct) product).getAmount() + "" : "";
    }

    public static Integer setVisibility(Product product) {
        if (product instanceof CartProduct)
            return 0;
        else
            return 8;
    }
}
