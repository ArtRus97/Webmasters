package com.example.webmasters.types;

/**
 * IAnimation defines an interface for general-purpose
 * animation classes.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 * <p>
 * v 1.0.0 Base interface created.
 */
public interface IAnimation {
    /**
     * getName returns the name of the animation.
     *
     * @return the name of the animation as String.
     */
    public String getName();

    /**
     * getValue returns the current value of
     * the animation.
     *
     * @return the current value of the animation as a float.
     */
    public float getValue();


    public boolean getAllowReversed();

    /**
     * isPlaying returns true if the animation is currently playing.
     *
     * @return is the animation currently playing as a boolean.
     */
    public boolean isPlaying();

    public boolean getIsReversed();

    /**
     * getFPS returns the how many times in a second animation updates.
     *
     * @return animation frames per second as an int.
     */
    public int getFPS();

    /**
     * getInterval returns the interval of animation updates in milliseconds.
     *
     * @return animation update interval as a float.
     */
    public float getInterval();

    /**
     * getMinimum returns the minimum value of the animation.
     *
     * @return the minimum value of the animation as a float.
     */
    public float getMinimum();

    /**
     * getMaximum returns the maximum value of the animation.
     *
     * @return the maximum value of the animation as a float.
     */
    public float getMaximum();

    /**
     * getChangePerSecond returns the amount animation
     * value changes per second.
     *
     * @return the change of animation value in seconds as a float.
     */
    public float getChangePerSecond();
}
