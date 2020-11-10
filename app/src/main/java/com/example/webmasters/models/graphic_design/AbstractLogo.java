package com.example.webmasters.models.graphic_design;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.webmasters.types.ILogo;

/**
 * AbstractLogo implements some common ILogo interface methods.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
abstract public class AbstractLogo extends BaseObservable implements ILogo {

    abstract void setTextValue(String textValue);

    @Bindable
    public String getTextValue() {
        return getText().getValue();
    }


    abstract void setTextSize(int textSize);

    @Bindable
    public int getTextSize() {
        return getText().getSize();
    }


    abstract void setTextColor(int textColor);

    @Bindable
    public int getTextColor() {
        return getText().getColor();
    }


    abstract void setTextX(int textX);

    @Bindable
    public int getTextX() {
        return getText().getX();
    }


    abstract void setTextY(int textY);

    @Bindable
    public int getTextY() {
        return getText().getY();
    }


    abstract void setTextBold(boolean isBold);

    @Bindable
    public boolean getTextBold() {
        return getText().isBold();
    }


    abstract void setTextItalic(boolean isItalic);

    @Bindable
    public boolean getTextItalic() {
        return getText().isItalic();
    }


    abstract void setShapeScale(float shapeScale);

    @Bindable
    public float getShapeScale() {
        return getShape().getScale();
    }


    abstract void setShapeColor(int shapeColor);

    @Bindable
    public int getShapeColor() {
        return getShape().getColor();
    }


    abstract void setShapeX(int x);

    @Bindable
    public int getShapeX() {
        return getShape().getX();
    }


    abstract void setShapeY(int y);

    @Bindable
    public int getShapeY() {
        return getShape().getY();
    }
}
