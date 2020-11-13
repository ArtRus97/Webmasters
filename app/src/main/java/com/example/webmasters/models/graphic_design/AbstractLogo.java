package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.webmasters.types.ILogo;

/**
 * AbstractLogo implements some basic functionality of ILogo interface.
 * <p>
 * v 1.0.0 Base class created.
 * v 1.1.0 Move ICanvasDrawable implementations/overrides from Logo to AbstractLogo
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
abstract public class AbstractLogo extends BaseObservable implements ILogo {

    /**
     * setTextValue sets the logo text value.
     * @param textValue (String) of logo text.
     */
    abstract void setTextValue(String textValue);

    @Override
    @Bindable
    final public String getTextValue() {
        return getText().getValue();
    }


    abstract void setTextSize(int textSize);

    @Override
    @Bindable
    final public int getTextSize() {
        return getText().getSize();
    }


    abstract void setTextColor(int textColor);

    @Override
    @Bindable
    final public int getTextColor() {
        return getText().getColor();
    }


    abstract void setTextX(int textX);

    @Override
    @Bindable
    final public int getTextX() {
        return getText().getX();
    }


    abstract void setTextY(int textY);

    @Override
    @Bindable
    final public int getTextY() {
        return getText().getY();
    }


    abstract void setTextBold(boolean isBold);

    @Override
    @Bindable
    final public boolean getTextBold() {
        return getText().isBold();
    }


    abstract void setTextItalic(boolean isItalic);

    @Override
    @Bindable
    final public boolean getTextItalic() {
        return getText().isItalic();
    }


    abstract void setShapeScale(float shapeScale);

    @Override
    @Bindable
    final public float getShapeScale() {
        return getShape().getScale();
    }


    abstract void setShapeColor(int shapeColor);

    @Override
    @Bindable
    final public int getShapeColor() {
        return getShape().getColor();
    }


    abstract void setShapeX(int x);

    @Override
    @Bindable
    final public int getShapeX() {
        return getShape().getX();
    }


    /**
     * setShapeY sets the logo shape y coordinate.
     *
     * @param y (int) coordinate of logo shape.
     */
    abstract void setShapeY(int y);

    @Override
    @Bindable
    final public int getShapeY() {
        return getShape().getY();
    }


    @Override
    final public int getX() {
        // Logo x-coordinate should always be at 0.
        return 0;
    }

    @Override
    final public int getY() {
        // Logo y-coordinate should always be at 0.
        return 0;
    }

    @Override
    final public Paint getPaint(Context context) {
        // Logo does not have a Paint itself.
        return null;
    }

    @Override
    final public void drawOnCanvas(Canvas canvas, Context context) {
        // Draw the sub-components.
        getShape().drawOnCanvas(canvas, context);
        getText().drawOnCanvas(canvas, context);
    }

    @Override
    final public void drawOnCanvas(Canvas canvas, Paint paint) {
        // Draw the sub-components with the given paint.
        getShape().drawOnCanvas(canvas, paint);
        getText().drawOnCanvas(canvas, paint);
    }
}
