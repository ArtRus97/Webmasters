package com.example.webmasters.converters;

import android.widget.EditText;

import androidx.databinding.InverseMethod;

import java.util.Locale;

public class Converter {
    public static String xToString(int x) {
        return String.format(Locale.ENGLISH, "X: %d", x);
    }

    public static String yToString(int y) {
        return String.format(Locale.ENGLISH, "Y: %d", y);
    }

    @InverseMethod("stringToInt")
    public static String intToString(int intValue) {
        return String.valueOf(intValue);
    }

    public static int stringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception _) {
            return 0;
        }

    }
}
