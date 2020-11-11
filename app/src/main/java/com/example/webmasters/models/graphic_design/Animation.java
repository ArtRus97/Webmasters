package com.example.webmasters.models.graphic_design;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import com.example.webmasters.types.ICanvasDrawable;





public abstract class Animation {
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
        mName = name;
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
        return new Animation("Blink", settings) {
            {
                settings.reverse = true;
                settings.maximum = 255;
            }

            protected void transformPaint(final Paint paint) {
                paint.setAlpha((int) getValue());
            }
        };
    }

    static Animation rotation(AnimationSettings settings) {
        return new Animation("Rotation", settings) {
            {
                settings.maximum = 360f;
            }
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


