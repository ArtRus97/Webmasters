package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.webmasters.types.IShape;

public class Shape implements IShape {
    private String mName = "Default";
    private int[] mPosition = {0, 0};
    public int mColor = Color.GREEN;
    public float mScale = 1.0f;

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
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setColor(mColor);

        return paint;
    }


    public void drawOnCanvas(Canvas canvas, Context context) {
        Paint paint = getPaint(context);

        canvas.save();
        canvas.scale(mScale, mScale, getX(), getY());

        float NUM_OVALS = 7f;
        for (int ovalIndex = 0; ovalIndex < NUM_OVALS; ovalIndex++) {
            double fraction = 2 * Math.PI * (ovalIndex / NUM_OVALS);
            float y = (float) (getY() + Math.sin(fraction) * 50);
            float x = (float) (getX() + Math.cos(fraction) * 50);
            canvas.drawCircle(x, y, 10, paint);
        }
        canvas.restore();
    }
}
