package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.databinding.InverseMethod;
import androidx.databinding.ObservableField;

import com.example.webmasters.types.IText;

/**
 * A very basic implementation of IText interface.
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class Text implements IText {
    private int[] mPosition = {0, 0};
    private int mSize = 12;
    private int mColor = Color.WHITE;
    private String mValue = "";
    private boolean mIsItalic = false;
    private boolean mIsBold = false;

    public void setSize(int size) {
        mSize = size;
    }

    public int getSize() {
        return mSize;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    public void setValue(String value) {
        mValue = value;
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

    public String getValue() {
        return mValue;
    }

    public void setBold(boolean bold) {
        mIsBold = bold;
    }

    public boolean isBold() {
        return mIsBold;
    }

    public void setItalic(boolean italic) {
        mIsItalic = italic;
    }

    public boolean isItalic() {
        return mIsItalic;
    }

}
