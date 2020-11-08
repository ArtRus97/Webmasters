package com.example.webmasters.models.graphic_design;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.webmasters.types.IShape;
import com.example.webmasters.types.IText;

public class LogoViewModel extends AbstractLogo {
    private Logo mLogo = new Logo();

    @Bindable
    public IText getText() {
        return mLogo.getText();
    }

    @Bindable
    public IShape getShape() {
        return mLogo.getShape();
    }

    public void setTextValue(String text) {
        if (mLogo.getTextValue().equals(text)) return;
        mLogo.setTextValue(text);
        notifyPropertyChanged(BR.textValue);
        notifyPropertyChanged(BR.text);
    }

    public void setTextSize(int textSize) {
        if (mLogo.getTextSize() == textSize) return;
        mLogo.setTextSize(textSize);
        notifyPropertyChanged(BR.textSize);
        notifyPropertyChanged(BR.text);
    }

    public void setTextColor(int textColor) {
        if (mLogo.getTextColor() == textColor) return;
        mLogo.setTextColor(textColor);
        notifyPropertyChanged(BR.textColor);
        notifyPropertyChanged(BR.text);
    }

    public void setShapeColor(int shapeColor) {
        if (mLogo.getShapeColor() == shapeColor) return;
        mLogo.setShapeColor(shapeColor);
        notifyPropertyChanged(BR.shapeColor);
        notifyPropertyChanged(BR.shape);
    }

    public void setShapeScale(float scale) {
        if (mLogo.getShapeScale() == scale) return;
        mLogo.setShapeScale(scale);
        notifyPropertyChanged(BR.shapeScale);
        notifyPropertyChanged(BR.shape);
    }

    public void setTextX(int x) {
        if (getTextX() == x) return;
        mLogo.setTextX(x);
        notifyPropertyChanged(BR.textX);
        notifyPropertyChanged(BR.text);
    }

    public void setTextY(int y) {
        if (getTextY() == y) return;
        mLogo.setTextY(y);
        notifyPropertyChanged(BR.textY);
        notifyPropertyChanged(BR.text);
    }
}