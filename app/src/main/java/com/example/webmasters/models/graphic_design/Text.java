package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.databinding.*;

import com.example.webmasters.BR;
import com.example.webmasters.types.IText;
import com.google.firebase.firestore.Exclude;

/**
 * Text is a very basic implementation of IText interface.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 * <p>
 * v 1.0.0 Base class created.
 * v 1.0.1 Observer notifiers added.
 * v 1.1.0 Text shadow added.
 */
public class Text extends BaseObservable implements IText {
    // The shadow of the text.
    private Shadow mShadow;
    // The (x, y) position of the text.
    final private int[] mPosition = {0, 0};
    // The size of the text as SP.
    private int mSize = 12;
    // The color of the text.
    private int mColor = Color.WHITE;
    // The value of the text.
    private String mValue = "";
    // Is the text in italic typeface.
    private boolean mIsItalic = false;
    // Is the text in bold typeface.
    private boolean mIsBold = false;

    public Text() {
        setShadow(new Shadow("Text"));
    }

    final public void setShadow(final Shadow shadow) {
        // Update property listeners.
        if (mShadow != null)
            mShadow.removeOnPropertyChangedCallback(this.onShadowPropertyChanged);
        shadow.addOnPropertyChangedCallback(this.onShadowPropertyChanged);
        // Set shadow.
        mShadow = shadow;
        // Notify observers.
        notifyPropertyChanged(BR.shadow);
    }

    @Override
    @Bindable
    final public Shadow getShadow() {
        return mShadow;
    }

    final public void setSize(final int size) {
        if (mSize == size) return;
        mSize = size;
        notifyPropertyChanged(BR.size);
    }

    @Override
    @Bindable
    final public int getSize() {
        return mSize;
    }

    final public void setColor(final int color) {
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
        // Create new paint.
        final Paint paint = new Paint();
        // Configure paint based on the state of the text.
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(IText.spAsPixels(context, mSize));
        paint.setColor(mColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setTypeface(getTypeface());
        // Add shadow configurations.
        paint.setShadowLayer(5f, mShadow.getX(), mShadow.getY(), mShadow.getColor());
        // Return configured paint.
        return paint;
    }

    public void drawOnCanvas(final Canvas canvas, final Context context) {
        Paint paint = getPaint(context);
        drawOnCanvas(canvas, paint);
    }

    public void drawOnCanvas(final Canvas canvas, final Paint paint) {
        canvas.drawText(mValue, getX(), getY(), paint);
    }

    final protected OnPropertyChangedCallback onShadowPropertyChanged = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            notifyChange();
        }
    };
}
