package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.webmasters.BR;
import com.example.webmasters.types.IShape;

public class Shape extends BaseObservable implements IShape {
    private String mName = "Unnamed";
    private int[] mPosition = {0, 0};
    private int mColor = Color.GREEN;
    private float mScale = 1.0f;

    public Shape() {
    }

    public Shape(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    final public String getName() {
        return mName;
    }

    final public void setX(int x) {
        if (getX() == x) return;
        mPosition[0] = x;
        notifyPropertyChanged(BR.x);
    }

    @Override
    @Bindable
    final public int getX() {
        return mPosition[0];
    }

    final public void setY(int y) {
        if (getY() == y) return;
        mPosition[1] = y;
        notifyPropertyChanged(BR.y);
    }

    @Override
    @Bindable
    final public int getY() {
        return mPosition[1];
    }

    final public void setColor(int color) {
        if (mColor == color) return;
        mColor = color;
        notifyPropertyChanged(BR.color);
    }

    @Override
    @Bindable
    final public int getColor() {
        return mColor;
    }

    final public void setScale(final float scale) {
        if (mScale == scale) return;
        mScale = scale;
        notifyPropertyChanged(BR.scale);
    }

    @Override
    @Bindable
    final public float getScale() {
        return mScale;
    }


    @Override
    public Paint getPaint(final Context context) {
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
     * <p>
     * Notes: Use onDraw to customize how the shape is being drawn.
     *
     * @param canvas  (Canvas) being drawn on.
     * @param context (Context)
     */
    final public void drawOnCanvas(final Canvas canvas, final Context context) {
        Paint paint = getPaint(context);
        drawOnCanvas(canvas, paint);
    }

    /**
     * drawOnCanvas handles the pipeline of drawing the shape on canvas
     * with custom paint provided.
     *
     * @param canvas (Canvas) being drawn on.
     * @param paint  (Paint) custom paint.
     */
    final public void drawOnCanvas(final Canvas canvas, final Paint paint) {
        onPreDraw(canvas);
        onDraw(canvas, paint);
        onPostDraw(canvas);
    }

    /**
     * onDraw handles the actual drawing of the shape on canvas.
     * <p>
     * Notes: Override this method to create custom-rendered shapes.
     *
     * @param canvas (Canvas) being drawn on.
     * @param paint  (Paint) used to draw the shape.
     */
    protected void onDraw(final Canvas canvas, final Paint paint) {
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
     *
     * @param canvas (Canvas) being drawn on.
     *               <p>
     *               Notes: Add any draw setups or canvas transforms here.
     */
    private void onPreDraw(final Canvas canvas) {
        canvas.save();
        // Scale the canvas based on the state of the shape.
        canvas.scale(mScale, mScale, getX(), getY());
    }

    /**
     * onPostDraw gets executed just after the shape has been drawn on canvas.
     *
     * @param canvas (Canvas) being drawn on.
     *               <p>
     *               Notes: Add any draw cleanups here.
     */
    private void onPostDraw(final Canvas canvas) {
        canvas.restore();
    }

    @NonNull
    final public String toString() {
        return mName;
    }
}
