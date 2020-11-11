package com.example.webmasters.models.graphic_design;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import com.example.webmasters.types.ICanvasDrawable;





public abstract class Animation {
    public final float MINIMUM;
    public final float MAXIMUM;
    public enum Type {
        Rotation,
        Blink
    }
    private String mName = "Unnamed";
    private AnimationSettings mSettings;
    private float mValue = 0;
    private boolean mIsReverse = false;

    public Animation(String name, AnimationSettings settings) {
        mSettings = settings;
        MINIMUM = settings.minimum;
        MAXIMUM = settings.maximum;
        mName = name;
    }

    final public void setChangePerSecond(final float change) {
        mSettings.changePerSecond = change;
    }

    final public void setInterval(final int milliseconds) {
        mSettings.interval = milliseconds;
    }

    final public String getName() {
        return mName;
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

        mValue = Math.min(MAXIMUM, Math.max(MINIMUM, mValue));

        if (mValue >= MAXIMUM)
            if (mSettings.reverse)
                mIsReverse = true;
            else
                mValue = MINIMUM;
        else if (mValue <= MINIMUM)
            mIsReverse = false;
    }

    static Animation blink(AnimationSettings settings) {
        settings.reverse = true;
        settings.maximum = 255;
        return new Animation("Blink", settings) {
            protected void transformPaint(final Paint paint) {
                paint.setAlpha((int) getValue());
            }
        };
    }

    static Animation rotation(AnimationSettings settings) {
        settings.maximum = 360f;
        settings.reverse = true;
        return new Animation("Rotation", settings) {
            final protected boolean transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
                canvas.rotate(getValue(), drawable.getX(), drawable.getY());
                return true;
            }
        };
    }

    @NonNull
    final public String toString() {
        return mName;
    }
}


