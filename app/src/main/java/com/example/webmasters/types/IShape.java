package com.example.webmasters.types;

import com.example.webmasters.models.graphic_design.Shadow;

/**
 * IShape defines an interface for shape classes.
 * @author JIkaheimo (Jaakko Ikäheimo)
 *
 * v 1.0.0 Base interface created.
 */
public interface IShape extends ICanvasDrawable {
    /**
     * getShadow returns the shadow of shape.
     * @return the shadow of shape as Shadow.
     */
    Shadow getShadow();

    /**
     * getType returns the type of shape.
     * @return the type of shape as ShapeType.
     */
    ShapeType getType();

    /**
     * getParameter returns the parameter of shape.
     * @return the parameter of shapes as int.
     */
    int getParameter();

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
