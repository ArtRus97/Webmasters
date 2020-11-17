package com.example.webmasters.models.graphic_design;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.webmasters.BR;
import com.example.webmasters.types.IAnimation;
import com.example.webmasters.types.ICanvasDrawable;

import java.util.Locale;

/**
 * Animation is an abstract base class for animations used to animate different
 * canvas drawable graphics.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public abstract class Animation extends BaseObservable implements IAnimation {
    /**
     * Immutable properties
     **/
    // Minimum boundary of the animation.
    public final float MINIMUM;
    // Maximum boundary of the animation.
    public final float MAXIMUM;
    // Name of the animation.
    public final String NAME;

    /**
     * Mutable properties
     */
    // The amount animation value changes in second.
    private float mChangePerSecond;
    // Frames per second for the animation
    private int mFPS;
    // The default value of the animation.
    private float mDefault;
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
    public Animation(final String name, final AnimationSettings settings) {
        NAME = name;
        // Map animation settings to properties.
        mChangePerSecond = settings.changePerSecond;
        mValue = settings.initialValue;
        mDefault = mValue;
        mFPS = settings.fps;
        MINIMUM = settings.minimum;
        MAXIMUM = settings.maximum;
        mAllowReverse = settings.reverse;
    }


    /**
     * setChangePerSecond sets the amount animation changes in a second.
     *
     * @param changePerSecond (float)
     */
    final public void setChangePerSecond(final float changePerSecond) {
        if (mChangePerSecond == changePerSecond) return;
        mChangePerSecond = changePerSecond;
        notifyPropertyChanged(BR.changePerSecond);
        notifyPropertyChanged(BR.duration);
    }

    @Override
    @Bindable
    final public float getChangePerSecond() {
        return mChangePerSecond;
    }


    @Override
    @Bindable
    final public boolean getAllowReversed() {
        return mAllowReverse;
    }

    final public void setIsPlaying(final boolean isPlaying) {
        mValue = mDefault;
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
    public float getMaximum() {
        return MAXIMUM;
    }

    @Override
    @Bindable
    final public float getMinimum() {
        return MINIMUM;
    }

    /**
     * setDuration sets the animation duration.
     */
    final public void setDuration(float duration) {
        setChangePerSecond((MAXIMUM - MINIMUM) / duration);
    }

    /**
     * getDuration returns the duration of the animation in seconds.
     *
     * @return duration of the animation as float.
     */
    @Bindable
    final public float getDuration() {
        return (MAXIMUM - MINIMUM) / mChangePerSecond;
    }


    /**
     * setIsReversed sets the animation to allow reversed transition.
     *
     * @param isReversed (boolean)
     */
    final public void setIsReversed(final boolean isReversed) {
        mAllowReverse = isReversed;
    }

    @Override
    @Bindable
    final public boolean getIsReversed() {
        return mAllowReverse;
    }

    final public void setFPS(int fps) {
        Log.d("ASD", fps + "");
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
    final public float getInterval() {
        return (1f / mFPS * 1000);
    }

    @Override
    @Bindable
    final public float getValue() {
        return mValue;
    }


    @Override
    @Bindable
    final public String getName() {
        return NAME;
    }


    final public void apply(final Paint paint) {
        update();
        transformPaint(paint);
    }

    final public void apply(final Canvas canvas) {
        update();
        canvas.save();
        transformCanvas(canvas);
    }

    final public void apply(final Canvas canvas, final ICanvasDrawable drawable) {
        update();
        canvas.save();
        transformCanvas(canvas, drawable);
    }

    protected void transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
    }

    protected void transformCanvas(Canvas canvas) {
    }

    /**
     * transformPaint transform the given paint with the animation values.
     * @param paint (Paint) to transform.
     */
    protected void transformPaint(Paint paint) {
        // Method stub: Override this.
    }

    /**
     * shouldUpdate returns true if the animation should update based
     * on its internal state.
     *
     * @return should animation update as boolan.
     */
    private boolean shouldUpdate() {
        long millisSinceUpdate = System.currentTimeMillis() - mLastUpdate;
        return (millisSinceUpdate > getInterval()) && isPlaying();
    }

    /**
     * getChangePerInterval returns the amount animation value
     * changes every interval.
     *
     * @return the change of animation per interval as a float.
     */
    private float getChangePerInterval() {
        return getInterval() / 1000f * mChangePerSecond;
    }


    /**
     * update handles the update of animation.
     */
    private void update() {
        if (!shouldUpdate()) return;

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

    public static String formatDuration(float duration) {
        return String.format(Locale.ENGLISH, "Duration (s) %4.2f:", duration);
    }

    public static String formatFPS(int fps) {
        return String.format(Locale.ENGLISH, "FPS %d:", fps);
    }
}


