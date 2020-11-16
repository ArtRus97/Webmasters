package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;
import androidx.databinding.ObservableField;

import com.example.webmasters.BR;
import com.example.webmasters.types.IText;
import com.google.firebase.firestore.Exclude;

/**
 * A very basic implementation of IText interface.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class Text extends BaseObservable implements IText {
    final private int[] mPosition = {0, 0};
    private int mSize = 12;
    private int mColor = Color.WHITE;
    private String mValue = "";
    private boolean mIsItalic = false;
    private boolean mIsBold = false;

    public void setSize(int size) {
        if (mSize == size) return;
        mSize = size;
        notifyPropertyChanged(BR.size);
    }

    @Override
    @Bindable
    public int getSize() {
        return mSize;
    }

    public void setColor(int color) {
        if (mColor == color) return;
        mColor = color;
        notifyPropertyChanged(BR.color);
    }

    @Override
    @Bindable
    public int getColor() {
        return mColor;
    }

    public void setValue(String value) {
        if (mValue.equals(value)) return;
        mValue = value;
        notifyPropertyChanged(BR.value);
    }

    @Override
    @Bindable
    final public String getValue() {
        return mValue;
    }

    final public void setX(final int x) {
        if (getX() == x) return;
        mPosition[0] = x;
        notifyPropertyChanged(BR.x);
    }

    @Override
    @Bindable
    final public int getX() {
        return mPosition[0];
    }

    final public void setY(final int y) {
        if (getY() == y) return;
        mPosition[1] = y;
        notifyPropertyChanged(BR.y);
    }

    @Override
    @Bindable
    final public int getY() {
        return mPosition[1];
    }

    final public void setBold(final boolean bold) {
        if (mIsBold == bold) return;
        mIsBold = bold;
        notifyPropertyChanged(BR.bold);
    }

    @Override
    @Bindable
    final public boolean isBold() {
        return mIsBold;
    }

    final public void setItalic(final boolean italic) {
        if (mIsItalic == italic) return;
        mIsItalic = italic;
        notifyPropertyChanged(BR.italic);
    }

    @Override
    @Bindable
    final public boolean isItalic() {
        return mIsItalic;
    }

    /**
     * getTypeface returns a Typeface of the text.
     *
     * @return Typeface of the thext.
     */
    @Exclude
    final public Typeface getTypeface() {
        final int typeface;

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


    final public Paint getPaint(final Context context) {
        final Paint paint = new Paint();

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
        drawOnCanvas(canvas, paint);
    }

    public void drawOnCanvas(Canvas canvas, Paint paint) {
        canvas.drawText(mValue, getX(), getY(), paint);
    }

}
