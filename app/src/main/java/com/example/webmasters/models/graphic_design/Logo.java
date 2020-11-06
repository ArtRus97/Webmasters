package com.example.webmasters.models.graphic_design;

import com.example.webmasters.types.ILogo;

class Logo implements ILogo {
    Text text = new Text();
    Shape shape = new Shape();

    public void setText(String titleText) {
        text.value = titleText;
    }

    public String getText() {
        return text.value;
    }

    public void setTextSize(int textSize) {
        text.size = textSize;
    }

    public int getTextSize() {
        return text.size;
    }


    public void setTextColor(int textColor) {
        text.color = textColor;
    }

    public int getTextColor() {
        return text.color;
    }

    @Override
    public void setShapeColor(int color) {
        shape.color = color;
    }

    @Override
    public int getShapeColor() {
        return shape.color;
    }

    @Override
    public void setShapeScale(float scale) {
        shape.scale = scale;
    }

    @Override
    public float getShapeScale() {
        return shape.scale;
    }


}
