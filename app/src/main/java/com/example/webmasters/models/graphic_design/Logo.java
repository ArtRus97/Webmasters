package com.example.webmasters.models.graphic_design;

import com.example.webmasters.types.IShape;
import com.example.webmasters.types.IText;

class Logo extends AbstractLogo {
    final private Text mText = new Text();
    final private Shape mShape = new Shape();

    public IText getText() {
        return mText;
    }

    public IShape getShape() {
        return mShape;
    }

    public void setTextValue(String textValue) {
        mText.setValue(textValue);
    }

    public void setTextSize(int textSize) {
        mText.setSize(textSize);
    }

    public void setTextColor(int textColor) {
        mText.setColor(textColor);
    }

    public void setShapeColor(int color) {
        mShape.setColor(color);
    }

    public void setShapeScale(float scale) {
        mShape.setScale(scale);
    }

    protected void setTextX(int x) {
        mText.setX(x);
    };

    protected void setTextY(int y) {
        mText.setY(y);
    }


}
