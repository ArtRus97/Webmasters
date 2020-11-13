package com.example.webmasters.models.graphic_design.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.webmasters.models.graphic_design.Animation;
import com.example.webmasters.types.ICanvasDrawable;
import com.example.webmasters.types.IShape;

import java.util.ArrayList;
import java.util.Collection;

/**
 * DrawableAnimator allows easier animation of different ICanvasDrawables.
 * @author Jikaheimo (Jaakko Ikäheimo)
 */
public class DrawableAnimator {
    // The handler used to run the animation method.
    private final Handler mAnimationHandler;
    // The view animator is used on.
    private final View mView;
    // Animation update interval in milliseconds.
    private long mInterval = 10;

    private Collection<Animation> mAnimations;

    /**
     * setInterval updates the animation interval
     * to be updated every given millisecond.
     *
     * @param milliseconds between animation updates as an integer.
     */
    public void setInterval(final int milliseconds) {
        mInterval = milliseconds;
        // Update animations with the new interval as well.
    }

    /**
     * setInterval updates the animation interval
     * to be updated every given second.
     * @param seconds (float)
     */
    public void setInterval(final float seconds) {
        setInterval((int)(seconds * 1000));
    }

    public DrawableAnimator(View view) {
        mView = view;
        mAnimations = new ArrayList<>();
        mAnimationHandler = new Handler(Looper.getMainLooper());
        mAnimationHandler.post(this::animate);
    }

    public void setAnimations(Collection<Animation> animations) {
        mAnimations = animations;
    }

    /**
     * drawShape will draw the given shape on the canvas
     * based on the current state of the animator.
     * @param canvas (Canvas) canvas being drawn on.
     * @param drawable (IShape) shape being drawn.
     */
    public void draw(@NonNull final Canvas canvas, @NonNull final ICanvasDrawable drawable) {
        Paint paint = drawable.getPaint(mView.getContext());

        // Apply animation to canvas.
        mAnimations.forEach(animation -> {
            animation.apply(paint);
            animation.apply(canvas);
            animation.apply(canvas, drawable);
        });

        drawable.drawOnCanvas(canvas, paint);
    }

    private void animate() {
        mView.invalidate();
        mAnimationHandler.postDelayed(this::animate, mInterval);
    }

}


