package com.example.webmasters.types;

public interface ILogo {
    void setText(String text);
    String getText();

    void setTextSize(int size);
    int getTextSize();

    void setTextColor(int color);
    int getTextColor();

    void setShapeColor(int color);
    int getShapeColor();

    void setShapeScale(float scale);
    float getShapeScale();
}