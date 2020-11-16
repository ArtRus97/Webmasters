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

    /**
     * getTextBold returns true if the logo text is in bold.
     *
     * @return is logo text bold as boolean.
     */
    boolean getTextBold();

    /**
     * getTextItalic returns true if the logo text is in italic.
     *
     * @return is logo text italic as boolean
     */
    boolean getTextItalic();

    /**
     * getShapeColor returns the color of logo shape.
     *
     * @return logo shape color as int.
     */
    int getShapeColor();

    /**
     * getShapeScale returns the scale of logo shape.
     *
     * @return logo shape scale as float.
     */
    float getShapeScale();

    /**
     * getShapeX returns the horizontal coordinate of logo shape.
     *
     * @return logo horizontal coordinate as int.
     */
    int getShapeX();

    /**
     * getShapeY returns the vertical coordinate of logo shape.
     *
     * @return logo vertical coordinate as int.
     */
    int getShapeY();

    /**
     * getShapeType returns the type of logo shape.
     *
     * @return logo shape type as ShapeType.
     */
    ShapeType getShapeType();
}
