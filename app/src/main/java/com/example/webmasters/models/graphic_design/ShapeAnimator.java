package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.webmasters.types.IShape;

/**
 * ShapeAnimator handles animation of shapes.
 * @author Jikaheimo (Jaakko Ikäheimo)
 */
public class ShapeAnimator {
    // Animation update interval in milliseconds.
    private long mInterval = 10;
    // Rotation in degrees per second (eg. 1 = full rotation per second)
    private float mRotationInSecond = 90;
    // Current rotation of the shape in degrees.
    private float mCurrentRotation = 0;

    /**
     * setInterval updates the animation interval
     * to be updated every given millisecond.
     *
     * @param milliseconds between animation updates as an integer.
     */
    public void setInterval(int milliseconds) {
        mInterval = milliseconds;
    }

    /**
     * setInterval updates the animation interval
     * to be updated every given second.
     * @param seconds (float)
     */
    public void setInterval(float seconds) {
        setInterval((int)(seconds * 1000));
    }

    /**
     * getRotationPerInterval return the current
     * rotation per interval of the animator as degrees.
     * @return interval rotation as degrees.
     */
    public float getRotationPerInterval() {
        return mInterval / 1000f * mRotationInSecond;
    }

    private final View mView;
    private final Handler mAnimationHandler = new Handler(Looper.getMainLooper());
    private final Runnable mAnimationRunnable = new Runnable(){
        public void run(){
            mCurrentRotation += getRotationPerInterval();
            if (mCurrentRotation >= 360)
                mCurrentRotation = 0;
            mView.invalidate();
            mAnimationHandler.postDelayed(this, mInterval);
        }
    };

    public ShapeAnimator(View view) {
        mView = view;
        mAnimationRunnable.run();
    }


    /**
     * drawShape will draw the given shape on the canvas
     * based on the current state of the animator.
     * @param canvas (Canvas) canvas being drawn on.
     * @param shape (IShape) shape being drawn.
     */
    public void drawShape(@NonNull Canvas canvas, @NonNull IShape shape) {
        Context context = mView.getContext();
        canvas.save();
        canvas.rotate(mCurrentRotation, shape.getX(), shape.getY());
        shape.drawOnCanvas(canvas, context);
        canvas.restore();
    }
}
