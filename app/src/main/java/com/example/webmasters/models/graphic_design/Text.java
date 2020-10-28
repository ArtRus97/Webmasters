package com.example.webmasters.models.graphic_design;

import android.content.Context;

public class Text {
    public float fontSize = 0;
    public int color = 0;
    public String text = "";

    public static float spAsPixels(Context context, int textSize) {
        return textSize * context.getResources().getDisplayMetrics().scaledDensity;
    }
}
