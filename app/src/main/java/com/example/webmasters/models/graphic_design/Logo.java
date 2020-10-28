package com.example.webmasters.models.graphic_design;

import com.example.webmasters.types.ILogo;

public class Logo implements ILogo {
    Text mText = new Text();

    @Override
    public void setText(String text) {
        mText.text = text;
    }

    @Override
    public String getText() {
        return mText.text;
    }

    @Override
    public void setTextSize(float textSize) {
        mText.fontSize = textSize;
    }

    @Override
    public float getTextSize() {
        return mText.fontSize;
    }

    @Override
    public void setTextColor(int color) {
        mText.color = color;
    }

    @Override
    public int getTextColor() {
        return mText.color;
    }
}
