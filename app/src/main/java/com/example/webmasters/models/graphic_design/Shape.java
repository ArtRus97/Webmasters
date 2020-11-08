package com.example.webmasters.models.graphic_design;

import android.graphics.Color;

import com.example.webmasters.types.IShape;

public class Shape implements IShape {
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
}
