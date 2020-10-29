package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Color;

public class Text {
    public final float INITIAL_FONT_SIZE = 12f;
    public final int INITIAL_COLOR = Color.WHITE;
    public final String INITIAL_TEXT = "";

    public float fontSize = INITIAL_FONT_SIZE;
    public int color = INITIAL_COLOR;
    public String text = INITIAL_TEXT;

    public static float spAsPixels(Context context, int textSize) {
        return textSize * context.getResources().getDisplayMetrics().scaledDensity;
    }
}
