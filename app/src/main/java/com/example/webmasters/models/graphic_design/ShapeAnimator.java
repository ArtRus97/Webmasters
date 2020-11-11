package com.example.webmasters.models.graphic_design;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.webmasters.types.IShape;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ShapeAnimator handles animation of shapes.
 * @author Jikaheimo (Jaakko Ikäheimo)
 */
public class ShapeAnimator {
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
        mAnimations.forEach(animation -> animation.setInterval(milliseconds));
    }

    /**
     * setInterval updates the animation interval
     * to be updated every given second.
     * @param seconds (float)
     */
    public void setInterval(final float seconds) {
        setInterval((int)(seconds * 1000));
    }

    public ShapeAnimator(View view) {
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
     * @param shape (IShape) shape being drawn.
     */
    public void drawShape(@NonNull final Canvas canvas, @NonNull final IShape shape) {
        Paint paint = shape.getPaint(mView.getContext());

        int numRestores = mAnimations.stream().reduce(0, (acc, animation) -> {
            animation.apply(paint);
            acc += animation.apply(canvas) ? 1 : 0;
            acc += animation.apply(canvas, shape) ? 1 : 0;
            return acc;
        }, Integer::sum);

        shape.drawOnCanvas(canvas, paint);

        for (int i = 0; i < numRestores; i++) {
            canvas.restore();
        }
    }

    private void animate() {
        mView.invalidate();
        mAnimationHandler.postDelayed(this::animate, mInterval);
    }

}


