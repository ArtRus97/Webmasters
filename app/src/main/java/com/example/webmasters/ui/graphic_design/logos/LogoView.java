package com.example.webmasters.ui.graphic_design.logos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;


public class LogoView extends View {
    private DrawSettings mSettings = new DrawSettings(getContext());
    private Paint paintLogo = new Paint();

    public LogoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        canvas.drawText(mSettings.text, centerX, centerY, mSettings.textPaint);
    }

    public void setText(String text) {
        mSettings.text = text;
        invalidate();
    }

    public void setTextSize(float textSize) {
        //mSettings.setTextSize(textSize);
    }


}

class DrawSettings {
    private Context mContext;
    public final int DEFAULT_TEXT_SIZE = 17;

    public String text = "";
    public final Paint textPaint = new Paint();

    public DrawSettings(Context context) {
        mContext = context;
        initPaints();
    }

    /**
     * initPaints() initializes the paints used to draw logos.
     */
    private void initPaints() {
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setTextSize(int textSize) {
        float scaledSizeInPixels = textSize * mContext.getResources().getDisplayMetrics().scaledDensity;
        textPaint.setTextSize(scaledSizeInPixels);
    }
};
