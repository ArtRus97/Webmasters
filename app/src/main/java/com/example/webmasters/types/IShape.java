package com.example.webmasters.types;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public interface IShape {
    int getX();
    int getY();

    int getColor();
    float getScale();

    /**
     * getPaint returns a Paint configured for the given shape.
     * @param context (Context)
     * @param shape (IShape)
     * @return
     */
    static Paint getPaint(Context context, IShape shape) {
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setColor(Color.GREEN);

        return paint;
    }

    /**
     * drawOnCanvas draws the given shape on canvas.
     * @param canvas (Canvas)
     * @param context (Context)
     * @param shape (IShape)
     */
    static void drawOnCanvas(Canvas canvas, Context context, IShape shape) {
        Paint paint = getPaint(context, shape);

        canvas.save();
        canvas.scale(shape.getScale(), shape.getScale(), canvas.getWidth() / 2f, canvas.getHeight() / 2f);
        float NUM_OVALS = 7f;
        for (int ovalIndex = 0; ovalIndex < NUM_OVALS; ovalIndex++) {
            double fraction = 2 * Math.PI * (ovalIndex / NUM_OVALS);
            float y = (float) (canvas.getHeight() / 2 + Math.sin(fraction) * 50);
            float x = (float) (canvas.getWidth() / 2 + Math.cos(fraction) * 50);
            canvas.drawCircle(x, y, 10, paint);
        }
        canvas.restore();
    }
}
