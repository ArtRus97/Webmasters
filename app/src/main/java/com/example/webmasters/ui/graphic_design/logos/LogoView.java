package com.example.webmasters.ui.graphic_design.logos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.webmasters.models.graphic_design.LogoViewModel;
import com.example.webmasters.models.graphic_design.Text;

import java.util.function.Function;
import java.util.function.Supplier;


public class LogoView extends View {
    private DrawSettings mSettings = new DrawSettings(getContext());

    public LogoView(Context context) {
        this(context, null);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        drawFlower(canvas);
        canvas.drawText(mSettings.getText(), centerX, centerY, mSettings.getTextPaint());
    }

    private void drawFlower(Canvas canvas) {
        float NUM_OVALS = 7f;
        for (int ovalIndex = 0; ovalIndex < NUM_OVALS; ovalIndex++) {
            double fraction = 2 * Math.PI * (ovalIndex / NUM_OVALS);
            float y = (float) (getHeight() / 2 + Math.sin(fraction) * 50);
            float x = (float) (getWidth() / 2 + Math.cos(fraction) * 50);
            canvas.drawCircle(x, y, 10, mSettings.shapePaint);
        }
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

}

class DrawSettings {
    private Context mContext;
    private String mText = "";
    private final Paint mTextPaint = new Paint();
    public final Paint shapePaint = new Paint();

    public DrawSettings(Context context) {
        mContext = context;
        initPaints();
    }

    /**
     * initPaints() initializes the paints used to draw logos.
     */
    private void initPaints() {
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

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
        mTextPaint.setTextSize(Text.spAsPixels(mContext, textSize));
    }

    public void setTextColor(int textColor) {
        mTextPaint.setColor(textColor);
    }

    public Paint getTextPaint() {
        return mTextPaint;
    }
};
