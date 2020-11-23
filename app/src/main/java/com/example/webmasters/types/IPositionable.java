package com.example.webmasters.types;

/**
 * IPositionable defines an interface for positionable classes.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 * <p>
 * v 1.0.0 Base interface created.
 */
public interface IPositionable {
    /**
     * getX returns the x coordinate of the positionable.
     *
     * @return x coordinate as int.
     */
    int getX();

    /**
     * getY returns the y coordinate of the positionable.
     *
     * @return y coordinate as int.
     */
    int getY();
}
