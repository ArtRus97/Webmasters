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

    @Override
    @Bindable
    public String getTextValue() {
        return getText().getValue();
    }


    abstract void setTextSize(int textSize);

    @Override
    @Bindable
    public int getTextSize() {
        return getText().getSize();
    }


    abstract void setTextColor(int textColor);

    @Override
    @Bindable
    public int getTextColor() {
        return getText().getColor();
    }


    abstract void setTextX(int textX);

    @Override
    @Bindable
    public int getTextX() {
        return getText().getX();
    }


    abstract void setTextY(int textY);

    @Override
    @Bindable
    public int getTextY() {
        return getText().getY();
    }


    abstract void setTextBold(boolean isBold);

    @Override
    @Bindable
    public boolean getTextBold() {
        return getText().isBold();
    }


    abstract void setTextItalic(boolean isItalic);

    @Override
    @Bindable
    public boolean getTextItalic() {
        return getText().isItalic();
    }


    abstract void setShapeScale(float shapeScale);

    @Override
    @Bindable
    public float getShapeScale() {
        return getShape().getScale();
    }


    abstract void setShapeColor(int shapeColor);

    @Override
    @Bindable
    public int getShapeColor() {
        return getShape().getColor();
    }


    abstract void setShapeX(int x);

    @Override
    @Bindable
    public int getShapeX() {
        return getShape().getX();
    }


    abstract void setShapeY(int y);

    @Override
    @Bindable
    public int getShapeY() {
        return getShape().getY();
    }
}
