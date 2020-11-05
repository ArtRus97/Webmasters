package com.example.webmasters.converters;

import android.widget.EditText;

import androidx.databinding.InverseMethod;

public class Converter {
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
