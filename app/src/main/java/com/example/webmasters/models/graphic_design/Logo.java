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
    public void setTextSize(int textSize) {
        mText.fontSize = textSize;
    }

    @Override
    public int getTextSize() {
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

    @Override
    public void setColor(int color) {

    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void setSize(int size) {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
