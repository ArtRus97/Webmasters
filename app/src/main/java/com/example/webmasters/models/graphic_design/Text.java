package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Color;

import androidx.databinding.ObservableField;

public class Text {
    public int size = 12;
    public int color = Color.WHITE;
    public String value = "";

    public static float spAsPixels(Context context, float textSize) {
        return textSize * context.getResources().getDisplayMetrics().scaledDensity;
    }
}
