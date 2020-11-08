package com.example.webmasters.models.graphic_design;

import android.graphics.Color;

import com.example.webmasters.types.IShape;

public class Shape implements IShape {
    public int mColor = Color.GREEN;
    public float mScale = 1.0f;

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
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
