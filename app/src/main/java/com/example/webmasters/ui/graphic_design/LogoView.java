package com.example.webmasters.ui.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.types.ILogo;
import com.example.webmasters.types.IShape;
import com.example.webmasters.types.IText;

import java.util.function.Consumer;


public class LogoView extends View {
    private SwipeListener mSwipeListener = null;
    private DrawSettings mSettings = new DrawSettings(this);
    public final Path mPath = new Path();
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private Consumer<Float> mOnScaleCallback = null;

    public LogoView(Context context) {
        this(context, null);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            private static final int SWIPE_MIN_DISTANCE = 120;
            private static final int SWIPE_MAX_OFF_PATH = 250;
            private static final int SWIPE_THRESHOLD_VELOCITY = 200;

            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2, float vX, float vY) {

                if (Math.abs(event1.getX() - event2.getX()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }
                // right to left swipe
                if (event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(vY) > SWIPE_THRESHOLD_VELOCITY) {
                    if (mSwipeListener != null) {
                        mSwipeListener.onSwipeUp();
                        invalidate();
                    }

                }
                // left to right swipe
                else if (event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(vY) > SWIPE_THRESHOLD_VELOCITY) {
                    if (mSwipeListener != null) {
                        mSwipeListener.onSwipeDown();
                        invalidate();
                    }
                }

                return false;
            }
        });

        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                // Display less values when the user "zooms in".
                float scale = mSettings.shape.getScale();
                if (detector.getScaleFactor() > 1)
                    scale += 0.05f;
                    // Display more values when the user "zooms out".
                else
                    scale -= 0.05f;


                if (mOnScaleCallback != null)
                    mOnScaleCallback.accept(scale);

                return super.onScale(detector);
            }
        });
    }


    public void onScale(Consumer<Float> callback) {
        mOnScaleCallback = callback;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.translate(0, mSettings.yPosition);
        canvas.drawPath(mPath, mSettings.mDrawPaint);
        if (mSettings.shape != null)
            drawShape(canvas, mSettings.shape);
        if (mSettings.text != null)
            drawText(canvas, mSettings.text);
        canvas.restore();
    }


    protected void drawShape(Canvas canvas, IShape shape) {
        shape.drawOnCanvas(canvas, getContext());
    }

    protected void drawText(Canvas canvas, IText text) {
        text.drawOnCanvas(canvas, getContext());
    }


    public void setShape(IShape shape) {
        mSettings.shape = shape;
        invalidate();
    }

    public void setText(IText text) {
        mSettings.text = text;
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);

        /*
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

         */

        return true;
    }

    public void setSwipeListener(SwipeListener swipeListener) {
        mSwipeListener = swipeListener;
    }



    public static abstract class SwipeListener {
        public void onSwipeUp() {
        }

        ;

        public void onSwipeDown() {
        }

        ;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (oldHeight != 0 && height > oldHeight) {
            mSettings.yPosition = (height - oldHeight) / 2;
        } else {
            mSettings.yPosition = 0;
        }
    }
}

class DrawSettings {
    public IText text;
    public IShape shape;
    public int yPosition = 0;

    public final Paint mDrawPaint = new Paint();

    public DrawSettings(LogoView view) {
        initPaints();
    }

    /**
     * initPaints() initializes the paints used to draw logos.
     */
    private void initPaints() {


        mDrawPaint.setColor(Color.RED);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        mDrawPaint.setStrokeWidth(10);


    }


};
