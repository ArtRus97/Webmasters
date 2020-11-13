package com.example.webmasters.models.graphic_design;

/**
 * AnimationSettings defines a container class for settings
 * used to configure an animation. Also specifies the default
 * settings used.
 *
 * @author (Jikaheimo) Jaakko Ikäheimo
 */
public abstract class AnimationSettings {
    // The fps of animation updates.
    public int fps = 60;
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
