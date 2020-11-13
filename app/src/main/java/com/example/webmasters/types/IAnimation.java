package com.example.webmasters.types;

import androidx.databinding.InverseMethod;

public interface IAnimation {
    public String getName();

    public float getValue();

    public boolean getAllowReversed();

    public boolean isPlaying();

    public boolean getIsReversed();

    public int getFPS();

    public float getInterval();

    public float getMinimum();

    public float getMaximum();

    public float getChangePerSecond();
}
