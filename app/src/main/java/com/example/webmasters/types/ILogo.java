package com.example.webmasters.types;

import androidx.databinding.InverseMethod;

public interface ILogo {
    IText getText();
    IShape getShape();

    String getTextValue();
    int getTextSize();
    int getTextColor();
    int getTextX();
    int getTextY();
    boolean getTextBold();
    boolean getTextItalic();

    int getShapeColor();
    float getShapeScale();
    int getShapeX();
    int getShapeY();
}