package com.example.webmasters.ui.graphic_design.logos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.webmasters.models.graphic_design.LogoViewModel;
import com.example.webmasters.models.graphic_design.Text;

import java.util.function.Function;
import java.util.function.Supplier;


public class LogoView extends View {
    private DrawSettings mSettings = new DrawSettings(this);
    public final Path mPath = new Path();
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;

    public LogoView(Context context) {
        this(context, null);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
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
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });

        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                return super.onScale(detector);
            }
        });
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawFlower(canvas);
        canvas.drawPath(mPath, mSettings.mDrawPaint);
        canvas.drawText(mSettings.getText(), mSettings.getTextX(), mSettings.getTextY(), mSettings.getTextPaint());
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

    public void setTextX(int xPosition) {
        mSettings.setTextPosition(xPosition, mSettings.getTextY());
        invalidate();
    }

    public void setTextY(int yPosition) {
        mSettings.setTextPosition(mSettings.getTextX(), yPosition);
        invalidate();
    }

    public void setText(String text) {
        mSettings.setText(text);
        invalidate();
    }

    public void setTextSize(float textSize) {
        mSettings.setTextSize(textSize);
        invalidate();
    }

    public void setTextColor(int color) {
        mSettings.setTextColor(color);
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        mScaleGestureDetector.onTouchEvent(event);

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

        return true;
    }
}

class DrawSettings {
    public float shapeScale = 1;
    private int[] mTextPosition;
    private String mText = "";
    private final Paint mTextPaint = new Paint();
    public final Paint shapePaint = new Paint();

    public final Paint mDrawPaint = new Paint();

    public DrawSettings(LogoView view) {
        mTextPosition = new int[]{view.getWidth() / 2, view.getHeight() / 2};
        initPaints();
    }

    /**
     * initPaints() initializes the paints used to draw logos.
     */
    private void initPaints() {
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setAntiAlias(true);

        mDrawPaint.setColor(Color.RED);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        mDrawPaint.setStrokeWidth(10);

        shapePaint.setStyle(Paint.Style.STROKE);
        shapePaint.setStrokeWidth(100);
        shapePaint.setColor(Color.GREEN);
    }

    public void setText(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    public void setTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
    }

    public void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
    }

    public void setTextPosition(int x, int y) {
        mTextPosition[0] = x;
        mTextPosition[1] = y;
    }

    public int getTextX() {
        return mTextPosition[0];
    }

    public int getTextY() {
        return mTextPosition[1];
    }

    public int[] getTextPosition() {
        return mTextPosition;
    }

    public Paint getTextPaint() {
        return mTextPaint;
    }


};
