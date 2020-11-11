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
 */
public interface ICanvasDrawable {
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

    /**
     * getX returns the x coordinate of the drawable.
     * @return x coordinate as int.
     */
    int getX();

    /**
     * getY returns the y coordinate of the drawable.
     * @return y coordinate as int.
     */
    int getY();
}
