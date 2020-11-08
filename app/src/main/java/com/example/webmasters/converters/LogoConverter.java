package com.example.webmasters.converters;

import android.widget.EditText;

import androidx.databinding.InverseMethod;

import java.util.Locale;

public class LogoConverter {
    public static String formatX(int x) {
        return String.format(Locale.ENGLISH, "X: %3d", x);
    }

    public static String formatSize(int size) {
        return String.format(Locale.ENGLISH, "Size: %d", size);
    }

    public static String formatY(int y) {
        return String.format(Locale.ENGLISH, "Y: %3d", y);
    }

    @InverseMethod("percentToScale")
    public  static int  scaleToPercent(float scale) {
        return (int)(scale * 100);
    }

    public static float percentToScale(int percent) {
        return percent / 100f;
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
