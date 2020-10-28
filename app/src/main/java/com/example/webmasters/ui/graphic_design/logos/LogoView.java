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
    private Paint paintLogo = new Paint();

    // Central position getters.
    private final Function<Integer, Float> getCenter = (value) -> value / 2f;
    public final Supplier<Float> getCenterX = () -> getCenter.apply(this.getWidth());
    public final Supplier<Float> getCenterY = () -> getCenter.apply(this.getHeight());
    public final Supplier<float []> getMiddle = () -> new float[]{getCenterX.get(), getCenterY.get()};



    public LogoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintLogo.setStyle(Paint.Style.FILL);
        paintLogo.setColor(Color.RED);
        int spSize = 17;
        float scaledSizeInPixels = spSize * getResources().getDisplayMetrics().scaledDensity;
        paintLogo.setTextSize(scaledSizeInPixels);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawText("Testi", 50, 50, paintLogo);
    }
}
