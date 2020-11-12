package com.example.webmasters.types;

/**
 * IShape defines an interface for shape classes.
 * @author JIkaheimo (Jaakko Ikäheimo)
 *
 * v 1.0.0 Base interface created.
 */
public interface IShape extends ICanvasDrawable {
    /**
     * getColor returns the color of shape.
     * @return the color of text as int.
     */
    int getColor();

    /**
     * getScale returns the scale of shape.
     * @return the scale of shape as float.
     */
    float getScale();
}
