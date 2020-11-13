package com.example.webmasters.types;

import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.models.graphic_design.Text;

/**
 * ILogo defines an interface for logos.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 * <p>
 * v 1.0.0 Base interface created.
 * v 1.1.0 Text typefaces added.
 * v 1.2.0 Shape and shape property accessors added.
 */
public interface ILogo extends ICanvasDrawable {
    /**
     * getText returns the text of logo.
     *
     * @return the text of logo as Text.
     */
    Text getText();


    /**
     * getShape returns the shape of logo.
     *
     * @return the shape of logo as Shape.
     */
    Shape getShape();

    /**
     * getTextValue returns the value of logo text.
     *
     * @return logo text value as a String.
     */
    String getTextValue();

    /**
     * getTextSize returns the size of logo text.
     *
     * @return logo text size as an int.
     */
    int getTextSize();

    /**
     * getTextColor returns the color of logo text.
     *
     * @return logo text color as an int.
     */
    int getTextColor();

    /**
     * getTextX returns the horizontal coordinate of logo text.
     *
     * @return logo text horizontal coordinate as an int.
     */
    int getTextX();

    /**
     * getTextY returns the vertical coordinate of logo text.
     *
     * @return logo text vertical coordinate as an int.
     */
    int getTextY();

    boolean getTextBold();

    boolean getTextItalic();

    int getShapeColor();

    float getShapeScale();

    int getShapeX();

    int getShapeY();

}
