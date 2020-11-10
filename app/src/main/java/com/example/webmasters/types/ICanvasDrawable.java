package com.example.webmasters.types;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * ICanvasDrawable provides an common interface for canvas drawable classes.
 * @author JIkaheimo (Jaakko Ikäheimo)
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
     * @param canvas (Canvas)
     * @param context (Context)
     */
    void drawOnCanvas(Canvas canvas, Context context);

    /**
     * drawOnCanvas draws the canvas drawable on the canvas
     * by using the custom paint.
     * @param canvas (Canvas)
     * @param paint (Patnt)
     */
    void drawOnCanvas(Canvas canvas, Paint paint);

    int getX();
    int getY();
}
