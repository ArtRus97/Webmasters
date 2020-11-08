package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import com.example.webmasters.types.IShape;

public class Shape implements IShape {
    private String mName = "Unnamed";
    private int[] mPosition = {0, 0};
    private int mColor = Color.GREEN;
    private float mScale = 1.0f;

    public Shape() {}

    public Shape(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setX(int x) {
        mPosition[0] = x;
    }

    public int getX() {
        return mPosition[0];
    }

    public void setY(int y) {
        mPosition[1] = y;
    }

    public int getY() {
        return mPosition[1];
    }

    void setColor(int color) {
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    void setScale(float scale) {
        mScale = scale;
    }

    public float getScale() {
        return mScale;
    }


    public Paint getPaint(Context context) {
        // Create new paint.
        Paint paint = new Paint();
        // Configure paint based on the state of the shape.
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setColor(mColor);
        // Return configured paint.
        return paint;
    }


    /**
     * drawOnCanvas handles the pipeline of drawing the shape on canvas.
     *
     * Notes: Use onDraw to customize how the shape is being drawn.
     * @param canvas (Canvas) being drawn on.
     * @param context (Context)
     */
    final public void drawOnCanvas(Canvas canvas, Context context) {
        onPreDraw(canvas);
        Paint paint = getPaint(context);
        onDraw(canvas, paint);
        onPostDraw(canvas);
    }

    /**
     * onDraw handles the actual drawing of the shape on canvas.
     *
     * Notes: Override this method to create custom-rendered shapes.
     * @param canvas (Canvas) being drawn on.
     * @param paint (Paint) used to draw the shape.
     */
    public void onDraw(Canvas canvas, Paint paint) {
        float NUM_OVALS = 7f;
        for (int ovalIndex = 0; ovalIndex < NUM_OVALS; ovalIndex++) {
            double fraction = 2 * Math.PI * (ovalIndex / NUM_OVALS);
            float y = (float) (getY() + Math.sin(fraction) * 50);
            float x = (float) (getX() + Math.cos(fraction) * 50);
            canvas.drawCircle(x, y, 10, paint);
        }
    }

    /**
     * onPreDraw gets executed just before the shape is drawn on canvas.
     * @param canvas (Canvas) being drawn on.
     *
     * Notes: Add any draw setups or canvas transforms here.
     */
    private void onPreDraw(Canvas canvas) {
        canvas.save();
        // Scale the canvas based on the state of the shape.
        canvas.scale(mScale, mScale, getX(), getY());
    }

    /**
     * onPostDraw gets executed just after the shape has been drawn on canvas.
     * @param canvas (Canvas) being drawn on.
     *
     * Notes: Add any draw cleanups here.
     */
    private void onPostDraw(Canvas canvas) {
        canvas.restore();
    }

    @NonNull
    final public String toString() {
        return mName;
    }
}
