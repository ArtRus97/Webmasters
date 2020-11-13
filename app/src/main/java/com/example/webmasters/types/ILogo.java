package com.example.webmasters.types;

/**
 * ILogo defines an interface for logo classes.
 * @author JIkaheimo (Jaakko Ikäheimo)
 *
 * v 1.0.0 Base interface created.
 * v 1.1.0 Text typefaces added.
 * v 1.2.0 Shape and shape property accessors added.
 */
public interface ILogo extends ICanvasDrawable {
    /**
     * getText returns the text of logo.
     * @return the text of logo as IText.
     */
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
