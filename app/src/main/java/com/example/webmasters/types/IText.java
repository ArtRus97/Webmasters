package com.example.webmasters.types;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * IText defines an interface for classes of text format.
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public interface IText {
    int getX();
    int getY();

    String getValue();
    int getColor();
    int getSize();

    boolean isItalic();
    boolean isBold();

    /**
     * getTypeface returns a Typeface for the given text component.
     * @param text (IText)
     * @return Typeface of the thext.
     */
    static Typeface getTypeface(IText text) {
        int typeface;
        if (text.isItalic() && text.isBold())
            typeface = Typeface.BOLD_ITALIC;
        else if (text.isBold())
            typeface = Typeface.BOLD;
        else if (text.isItalic())
            typeface = Typeface.ITALIC;
        else
            typeface = Typeface.NORMAL;
        return Typeface.create(Typeface.DEFAULT, typeface);
    }

    /**
     * getPaint returns a Paint configured for the given text component.
     * @param context (Context)
     * @param text (IText)
     * @return a paint for the given text.
     */
    static Paint getPaint(Context context, IText text) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(spAsPixels(context, text.getSize()));
        paint.setColor(text.getColor());
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(IText.getTypeface(text));
        return paint;
    }

    /**
     * drawOnCanvas draws the given text on canvas.
     * @param canvas (Canvas)
     * @param context (Context)
     * @param text (IText)
     */
    static void drawOnCanvas(Canvas canvas, Context context, IText text) {
        Paint paint = getPaint(context, text);;
        canvas.drawText(text.getValue(), text.getX(), text.getY(), paint);
    }

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
