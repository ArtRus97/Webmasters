package com.example.webmasters.types;

public interface ILogo {
    IText getText();
    IShape getShape();

    void setTextValue(String text);
    String getTextValue();

    void setTextSize(int size);
    int getTextSize();

    void setTextColor(int color);
    int getTextColor();

    int getTextX();
    int getTextY();

    void setShapeColor(int color);
    int getShapeColor();

    void setShapeScale(float scale);
    float getShapeScale();

    int getShapeX();
    int getShapeY();
}