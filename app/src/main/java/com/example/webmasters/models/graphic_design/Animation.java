package com.example.webmasters.models.graphic_design;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.webmasters.types.ICanvasDrawable;



public abstract class Animation {
    private AnimationSettings mSettings;
    private float mValue = 0;
    private boolean mIsReverse = false;

    public Animation(AnimationSettings settings) {
        mSettings = settings;
        Log.d("ASD", mSettings.maximum+"f");
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

    private float getChangePerInterval() {
        return mSettings.interval / 1000f * mSettings.changePerSecond;
    }

    public float getValue() {
        return mValue;
    }

    public void update() {
        if (mSettings.reverse && mIsReverse) {
            mValue -= getChangePerInterval();
        } else {
            mValue += getChangePerInterval();
        }

        mValue = Math.min(mSettings.maximum, Math.max(mSettings.minimum, mValue));

        if (mValue >= mSettings.maximum)
            if (mSettings.reverse)
                mIsReverse = true;
            else
                mValue = mSettings.minimum;
        else if (mValue <= mSettings.minimum)
            mIsReverse = false;
    }

    static Animation blink(AnimationSettings settings) {
        return new Animation(settings) {
            protected void transformPaint(final Paint paint) {
                paint.setAlpha((int) getValue());
            }
        };
    }

    static Animation rotation(AnimationSettings settings) {

        return new Animation(settings) {
            final protected boolean transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
                canvas.rotate(getValue(), drawable.getX(), drawable.getY());
                return true;
            }
        };
    }

}

/**
 * AnimationSettings defines an abstract wrapper
 * class for settings used to customize animations.
 *
 * @author (Jikaheimo) Jaakko Ikäheimo
 */
abstract class AnimationSettings {
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

    public AnimationSettings() {
        config();
    }

    /**
     * config is called whenever new animation settings
     * gets created.
     *
     * Notes: Add any setup code here.
     */
    protected abstract void config();
}
