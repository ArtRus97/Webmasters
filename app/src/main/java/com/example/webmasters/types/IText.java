package com.example.webmasters.types;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.databinding.Bindable;
import com.example.webmasters.models.graphic_design.Shadow;

/**
 * IText defines an interface for classes of text format.
 * @author JIkaheimo (Jaakko Ikäheimo)
 *
 * v 1.0.0 Base interface created.
 * v 1.0.1 Utility static method added to convert sp value to pixels.
 * v 1.1.0 Italic and bold typefaces added.
 */
public interface IText extends ICanvasDrawable {
    /**
     * getShadow returns the shadow of text.
     * @return the shadow of text as Shadow.
     */
    Shadow getShadow();

    /**
     * getValue returns the displayed text value.
     * @return the value of text as String.
     */
    String getValue();

    /**
     * getColor returns the color of text.
     * @return the color of text as int.
     */
    int getColor();

    /**
     * getSize returns the size of text.
     * @return the size of text as int.
     */
    int getSize();

    /**
     * isItalic returns true if the text's
     * typeface is italic.
     * @return is text italic as boolean.
     */
    boolean isItalic();

    /**
     * isBold returns true if the text's
     * typeface is bold.
     * @return is text bold as boolean.
     */
    boolean isBold();

    /**
     * spAsPixels converts the given sp text size to the
     * corresponding pixel value.
     * @param context (Context)
     * @param textSize (float) size of the text as sp.
     * @return sp as pixels.
     */
    static float spAsPixels(Context context, float textSize) {
        return textSize * context.getResources().getDisplayMetrics().scaledDensity;
    }
}
