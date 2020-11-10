package com.example.webmasters.types;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * IText defines an interface for classes of text format.
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public interface IText extends ICanvasDrawable {
    String getValue();
    int getColor();
    int getSize();

    boolean isItalic();
    boolean isBold();

    /**
     * spAsPixels converts the given sp text size to the
     * corresponding pixel value.
     * @param context (Context)
     * @param textSize (float)
     * @return sp as pixels.
     */
    static float spAsPixels(Context context, float textSize) {
        return textSize * context.getResources().getDisplayMetrics().scaledDensity;
    }
}
