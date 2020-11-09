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
    public Paint getPaint(Context context);

    /**
     * drawOnCanvas draws the given canvas drawable on canvas.
     * @param canvas (Canvas)
     * @param context (Context)
     */
    public void drawOnCanvas(Canvas canvas, Context context);
}
