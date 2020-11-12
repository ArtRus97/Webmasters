package com.example.webmasters.models.graphic_design;

/**
 * AnimationSettings defines a container class for settings
 * used to configure an animation.
 *
 * @author (Jikaheimo) Jaakko Ikäheimo
 */
public abstract class AnimationSettings {
    // The interval of animation updates in milliseconds.
    public float interval = 0f;
    // How much the animation value gets changed in a second.
    public float changePerSecond = 1f;
    // Initial animation value.
    public float initialValue = 0f;
    // The minimum animation value.
    public float minimum = 0f;
    // The maximum animation value.
    public float maximum = 0f;
    // Is the animation reversing.
    public boolean reverse = false;
}
