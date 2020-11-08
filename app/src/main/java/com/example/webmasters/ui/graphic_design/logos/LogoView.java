package com.example.webmasters.ui.graphic_design.logos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.fonts.FontVariationAxis;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.webmasters.models.graphic_design.LogoViewModel;
import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.models.graphic_design.Text;
import com.example.webmasters.types.IShape;
import com.example.webmasters.types.IText;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class LogoView extends View {
    private SwipeListener mSwipeListener = null;
    private DrawSettings mSettings = new DrawSettings(this);
    public final Path mPath = new Path();
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private Consumer<Float> mShapeScaleListener = null;

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

                if (Math.abs(event1.getX() - event2.getX()) > SWIPE_MAX_OFF_PATH){
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
                if (detector.getScaleFactor() > 1)
                    setShapeScale(getShapeScale() + 0.05f);
                    // Display more values when the user "zooms out".
                else
                    setShapeScale(getShapeScale() - 0.05f);

                invalidate();

                if (mShapeScaleListener != null)
                    mShapeScaleListener.accept(getShapeScale());

                return super.onScale(detector);
            }
        });
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFlower(canvas);
        canvas.drawPath(mPath, mSettings.mDrawPaint);
        IText.drawOnCanvas(canvas, getContext(), mSettings.text);
    }

    private void drawFlower(Canvas canvas) {
        canvas.save();
        canvas.scale(mSettings.shapeScale, mSettings.shapeScale, getWidth() / 2f, getHeight() / 2f);
        float NUM_OVALS = 7f;
        for (int ovalIndex = 0; ovalIndex < NUM_OVALS; ovalIndex++) {
            double fraction = 2 * Math.PI * (ovalIndex / NUM_OVALS);
            float y = (float) (getHeight() / 2 + Math.sin(fraction) * 50);
            float x = (float) (getWidth() / 2 + Math.cos(fraction) * 50);
            canvas.drawCircle(x, y, 10, mSettings.shapePaint);
        }
        canvas.restore();
    }

    public void setShapeScale(float scale) {
        mSettings.shapeScale = scale;
        invalidate();
    }

    public float getShapeScale() {
        return mSettings.shapeScale;
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

    public void onShapeScaleChanged(Consumer<Float> callback) {
        mShapeScaleListener = callback;
    }

    public static abstract class SwipeListener {
        public void onSwipeUp(){};
        public void onSwipeDown(){};
    }
}

class DrawSettings {
    public IText text;
    public IShape shape;
    public float shapeScale = 1;
    public final Paint shapePaint = new Paint();

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

        shapePaint.setStyle(Paint.Style.STROKE);
        shapePaint.setStrokeWidth(100);
        shapePaint.setColor(Color.GREEN);
    }



};
