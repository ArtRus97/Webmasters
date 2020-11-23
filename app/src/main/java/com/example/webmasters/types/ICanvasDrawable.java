package com.example.webmasters.types;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * ICanvasDrawable provides an common interface for canvas drawable classes.
 * @author JIkaheimo (Jaakko Ikäheimo)
 *
 * v 1.0.0 Base interface created.
 * v 1.1.0 X and Y coordinates added.
 * v 1.2.0 Extract position interface to IPositionable.
 */
public interface ICanvasDrawable extends IPositionable {
    /**
     * getPaint returns a Paint configured for the canvas drawable.
     * @param context (Context)
     * @return configured paint for the canvas drawable.
     */
    Paint getPaint(Context context);

    /**
     * drawOnCanvas draws the canvas drawable on the canvas.
     * @param canvas (Canvas) being drawn on.
     * @param context (Context)
     */
    void drawOnCanvas(Canvas canvas, Context context);

    /**
     * drawOnCanvas draws the canvas drawable on the canvas
     * by using the given paint.
     * @param canvas (Canvas) being drawn on.
     * @param paint (Paint) paint used to draw the drawable.
     */
    void drawOnCanvas(Canvas canvas, Paint paint);
}
