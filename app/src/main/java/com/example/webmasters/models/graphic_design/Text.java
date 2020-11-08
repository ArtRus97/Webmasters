package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.databinding.InverseMethod;
import androidx.databinding.ObservableField;

import com.example.webmasters.types.IText;

/**
 * A very basic implementation of IText interface.
 *
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

    public String getValue() {
        return mValue;
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

    /**
     * getTypeface returns a Typeface of the text.
     *
     * @return Typeface of the thext.
     */
    public Typeface getTypeface() {
        int typeface;

        if (mIsItalic && mIsBold)
            typeface = Typeface.BOLD_ITALIC;
        else if (mIsBold)
            typeface = Typeface.BOLD;
        else if (mIsItalic)
            typeface = Typeface.ITALIC;
        else
            typeface = Typeface.NORMAL;

        return Typeface.create(Typeface.DEFAULT, typeface);
    }


    public Paint getPaint(Context context) {
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(IText.spAsPixels(context, mSize));
        paint.setColor(mColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(getTypeface());

        return paint;
    }

    public void drawOnCanvas(Canvas canvas, Context context) {
        Paint paint = getPaint(context);
        canvas.drawText(mValue, getX(), getY(), paint);
    }

}
