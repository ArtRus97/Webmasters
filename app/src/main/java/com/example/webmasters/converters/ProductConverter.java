package com.example.webmasters.converters;

import java.util.Locale;

public class ProductConverter {

    public static String formatPrice(final float price) {
        return String.format(Locale.ENGLISH, "%.2f", price);
    }

    public static String formatDescription(final String description) {
        int descLength = Math.min(description.length(), 80);
        return description.substring(0, descLength) + "...";
    }
}
