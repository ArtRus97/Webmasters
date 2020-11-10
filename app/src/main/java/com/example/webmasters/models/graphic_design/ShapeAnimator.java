package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.webmasters.types.IShape;

public class ShapeAnimator {

    private final View mView;
    private final Handler mAnimationHandler = new Handler(Looper.getMainLooper());
    private final Runnable mAnimationRunnable = new Runnable(){
        public void run(){
            mRotation += 10;
            if (mRotation >= 360)
                mRotation = 0;
            mView.invalidate();
            mAnimationHandler.postDelayed(this, 100);
        }
    };

    public ShapeAnimator(View view) {
        mView = view;
        mAnimationRunnable.run();
    }

    private float mRotation = 0;

    public void drawShape(Canvas canvas, IShape shape) {
        Context context = mView.getContext();
        canvas.save();
        canvas.rotate(mRotation, shape.getX(), shape.getY());
        shape.drawOnCanvas(canvas, context);
        canvas.restore();
    }
}
