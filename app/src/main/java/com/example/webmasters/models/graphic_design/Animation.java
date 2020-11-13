package com.example.webmasters.models.graphic_design;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;
import com.example.webmasters.BR;
import com.example.webmasters.types.IAnimation;
import com.example.webmasters.types.ICanvasDrawable;

import java.util.Locale;

/**
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public abstract class Animation extends BaseObservable implements IAnimation {
    // Minimum boundary of the animation.
    public final float MINIMUM;
    // Maximum boundary of the animation.
    public final float MAXIMUM;
    // Name of the animation.
    public final String NAME;
    // The amount animation value changes in second.
    private float mChangePerSecond;
    // Frames per second for the animation
    private int mFPS;
    // The current value of the animation.
    private float mValue;
    // Is animation allowed to reverse when it reaches its maximum.
    private boolean mAllowReverse = false;
    // Is animation currently reversed.
    private boolean mIsReverse = false;
    // Is animation currently playing.
    private boolean mIsPlaying = false;
    // Last time animation was updated.
    private long mLastUpdate = System.currentTimeMillis();

    /**
     * Constructor
     *
     * @param name     (String) of the animation
     * @param settings (AnimationSettings) used to configure the animation.
     */
    public Animation(String name, AnimationSettings settings) {
        NAME = name;
        // Map animation settings to properties.
        mChangePerSecond = settings.changePerSecond;
        mValue = settings.initialValue;
        mFPS = settings.fps;
        MINIMUM = settings.minimum;
        MAXIMUM = settings.maximum;
        mAllowReverse = settings.reverse;
    }


    final public void setChangePerSecond(final float change) {
        mChangePerSecond = change;
    }

    @Override
    @Bindable
    public float getChangePerSecond() {
        return mChangePerSecond;
    }


    @Override
    @Bindable
    public float getMaximum() {
        return MAXIMUM;
    }

    @Override
    @Bindable
    public boolean getAllowReversed() {
        return mAllowReverse;
    }

    public void setIsPlaying(boolean isPlaying) {
        mIsPlaying = isPlaying;
        notifyPropertyChanged(BR.playing);
    }

    @Override
    @Bindable
    public boolean isPlaying() {
        return mIsPlaying;
    }

    @Override
    @Bindable
    public float getMinimum() {
        return MINIMUM;
    }

    @Override
    @Bindable
    public boolean getIsReversed() {
        return mAllowReverse;
    }

    final public void setFPS(int fps) {
        Log.d("ASD", fps+"");
        if (mFPS == fps) return;
        mFPS = fps;
        notifyPropertyChanged(BR.fPS);
    }


    @Override
    @Bindable
    final public int getFPS() {
        return mFPS;
    }


    @Override
    @Bindable
    public float getInterval() {
        return (1f / mFPS * 1000);
    }

    @Override
    @Bindable
    public float getValue() {
        return mValue;
    }


    @Override
    @Bindable
    public String getName() {
        return NAME;
    }


    final public void apply(final Paint paint) {
        update();
        transformPaint(paint);
    }

    final public boolean apply(final Canvas canvas) {
        update();
        canvas.save();
        transformCanvas(canvas);
        return true;
    }

    final public boolean apply(final Canvas canvas, final ICanvasDrawable drawable) {
        update();
        canvas.save();
        transformCanvas(canvas, drawable);
        return true;
    }

    protected boolean transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
        return false;
    }

    protected boolean transformCanvas(Canvas canvas) {
        return false;
    }

    protected void transformPaint(Paint paint) {

    }

    private boolean shouldUpdate() {
        return (System.currentTimeMillis() - mLastUpdate) > getInterval();
    }

    private float getChangePerInterval() {
        return getInterval() / 1000f * mChangePerSecond;
    }

    /**
     * update handles the update of animation.
     */
    private void update() {
        if (!shouldUpdate() || !mIsPlaying) return;

        if (mAllowReverse && mIsReverse)
            mValue -= getChangePerInterval();
        else
            mValue += getChangePerInterval();


        mValue = Math.min(MAXIMUM, Math.max(MINIMUM, mValue));

        if (mValue >= MAXIMUM)
            if (mAllowReverse)
                mIsReverse = true;
            else
                mValue = MINIMUM;
        else if (mValue <= MINIMUM)
            mIsReverse = false;

        mLastUpdate = System.currentTimeMillis();
    }


    @NonNull
    final public String toString() {
        return getName();
    }

    public static String formatFPS(int fps) {
        return String.format(Locale.ENGLISH, "FPS %d:", fps);
    }
}


