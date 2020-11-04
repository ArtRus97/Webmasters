package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Color;

public class Text {
    public final int INITIAL_FONT_SIZE = 12;
    public final int INITIAL_COLOR = Color.WHITE;
    public final String INITIAL_TEXT = "";

    public int fontSize = INITIAL_FONT_SIZE;
    public int color = INITIAL_COLOR;
    public String text = INITIAL_TEXT;

    public static float spAsPixels(Context context, float textSize) {
        return textSize * context.getResources().getDisplayMetrics().scaledDensity;
    }
}
