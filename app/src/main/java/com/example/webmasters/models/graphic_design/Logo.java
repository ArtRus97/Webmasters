package com.example.webmasters.models.graphic_design;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.webmasters.types.IShape;
import com.example.webmasters.types.IText;

public class Logo extends AbstractLogo {
    final private Text mText = new Text();
    final private Shape mShape = new Shape();

    @Bindable
    public IText getText() {
        return mText;
    }

    @Bindable
    public IShape getShape() {
        return mShape;
    }

    public void setTextValue(String textValue) {
        if (getTextValue().equals(textValue)) return;
        mText.setValue(textValue);
        notifyPropertyChanged(BR.textValue);
        notifyPropertyChanged(BR.text);
    }

    public void setTextSize(int textSize) {
        if (getTextSize() == textSize) return;
        mText.setSize(textSize);
        notifyPropertyChanged(BR.textSize);
        notifyPropertyChanged(BR.text);
    }

    public void setTextColor(int textColor) {
        if (getTextColor() == textColor) return;
        mText.setColor(textColor);
        notifyPropertyChanged(BR.textColor);
        notifyPropertyChanged(BR.text);
    }

    public void setShapeColor(int shapeColor) {
        if (getShapeColor() == shapeColor) return;
        mShape.setColor(shapeColor);
        notifyPropertyChanged(BR.shapeColor);
        notifyPropertyChanged(BR.shape);
    }

    public void setShapeScale(float scale) {
        if (getShapeScale() == scale) return;
        mShape.setScale(scale);
        notifyPropertyChanged(BR.shapeScale);
        notifyPropertyChanged(BR.shape);
    }

    public void setTextX(int x) {
        if (getTextX() == x) return;
        mText.setX(x);
        notifyPropertyChanged(BR.textX);
        notifyPropertyChanged(BR.text);
    }

    public void setTextY(int y) {
        if (getTextY() == y) return;
        mText.setY(y);
        notifyPropertyChanged(BR.textY);
        notifyPropertyChanged(BR.text);
    }

    public void setTextBold(boolean isBold) {
        if (getTextBold() == isBold) return;
        mText.setBold(isBold);
        notifyPropertyChanged(BR.textBold);
        notifyPropertyChanged(BR.text);
    }

    public void setTextItalic(boolean isItalic) {
        if (getTextItalic() == isItalic) return;
        mText.setItalic(isItalic);
        notifyPropertyChanged(BR.textItalic);
        notifyPropertyChanged(BR.text);
    }


}
