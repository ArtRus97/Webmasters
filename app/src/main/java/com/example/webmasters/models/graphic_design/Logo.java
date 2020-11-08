package com.example.webmasters.models.graphic_design;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.webmasters.types.IShape;
import com.example.webmasters.types.IText;

public class Logo extends AbstractLogo {
    final public float MIN_SCALE = 0.5f;
    final public float MAX_SCALE = 2.0f;

    final private Text mText = new Text();
    private Shape mShape = new Shape();

    @Bindable
    public IText getText() {
        return mText;
    }

    public void setShape(Shape shape) {
        // Update the new shape with the previous one.
        shape.setX(mShape.getX());
        shape.setY(mShape.getY());
        shape.setScale(mShape.getScale());
        shape.setColor(mShape.getColor());
        // Set shape.
        mShape = shape;
        // Notify observers.
        notifyPropertyChanged(BR.shape);
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
        // Make sure scaling does not go over boundaries.
        scale = Math.max(MIN_SCALE, Math.min(scale, MAX_SCALE));
        // Check for redundant operation.
        if (getShapeScale() == scale) return;
        // Update scale.
        mShape.setScale(scale);
        // Notify observers.
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

    public void setShapeX(int x) {
        if (getShapeX() == x) return;
        mShape.setX(x);
        notifyPropertyChanged(BR.shapeX);
        notifyPropertyChanged(BR.shape);
    }

    public void setShapeY(int y) {
        if (getShapeY() == y) return;
        mShape.setY(y);
        notifyPropertyChanged(BR.shapeY);
        notifyPropertyChanged(BR.shape);
    }
}
