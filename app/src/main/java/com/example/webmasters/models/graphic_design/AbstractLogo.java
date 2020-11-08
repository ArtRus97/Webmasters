package com.example.webmasters.models.graphic_design;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.webmasters.types.ILogo;

/**
 * AbstractLogo implements some common ILogo interface methods.
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
abstract public class AbstractLogo extends BaseObservable implements ILogo {
    @Bindable
    public String getTextValue() {
        return getText().getValue();
    }

    @Bindable
    public int getTextSize() {
        return getText().getSize();
    }

    @Bindable
    public int getTextColor() {
        return getText().getColor();
    }

    @Bindable
    public int getTextX() {
        return getText().getX();
    }

    @Bindable
    public int getTextY() {
        return getText().getY();
    }

    @Bindable
    public float getShapeScale() {
        return getShape().getScale();
    }

    @Bindable
    public int getShapeColor() {
        return getShape().getColor();
    }

    @Bindable
    public int getShapeX() {
        return getShape().getX();
    }

    @Bindable
    public int getShapeY() {
        return getShape().getY();
    }
}
