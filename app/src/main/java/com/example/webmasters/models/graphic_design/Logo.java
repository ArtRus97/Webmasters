package com.example.webmasters.models.graphic_design;

import com.example.webmasters.types.ILogo;

class Logo implements ILogo {
    Text text = new Text();

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


    public void setColor(int color) {

    }

    public int getColor() {
        return 0;
    }



    public void setSize(int size) {

    }

    public int getSize() {
        return 0;
    }
}
