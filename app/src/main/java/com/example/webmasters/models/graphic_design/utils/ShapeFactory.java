package com.example.webmasters.models.graphic_design.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.webmasters.models.graphic_design.Shape;

import java.util.*;

class StarShape extends Shape {
    private int mNumSpikes;
    private int mOuterRadius;
    private int mInnerRadius;

    public StarShape(final int numSpikes) {
        this(numSpikes, 100);
    }

    public StarShape(final int numSpikes, final int outerRadius) {
        super(numSpikes + "-spiked star");
        mNumSpikes = numSpikes;
        mOuterRadius = 100;
        mInnerRadius = mOuterRadius / 2;
    }

    @Override
    public Paint getPaint(Context context) {
        Paint paint = super.getPaint(context);
        paint.setStrokeWidth(8f);
        return paint;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        Path starPath = new Path();
        float rotation = 3 * (float) Math.PI / 2f;
        float x;
        float y;
        final float STEP = (float) Math.PI / mNumSpikes;

        for (int spikeIndex = 0; spikeIndex < mNumSpikes; spikeIndex++) {
            x = getX() + (float)Math.cos(rotation) * mOuterRadius;
            y = getY() + (float)Math.sin(rotation) * mOuterRadius;
            if (spikeIndex == 0)
                starPath.moveTo(x, y);
            starPath.lineTo(x, y);
            rotation += STEP;

            x = getX() + (float)Math.cos(rotation) * mInnerRadius;
            y = getY() + (float)Math.sin(rotation) * mInnerRadius;
            starPath.lineTo(x, y);
            rotation += STEP;
        }
        starPath.close();

        canvas.drawPath(starPath, paint);
    }

}

public class ShapeFactory {
    /**
     * createShapes returns some pre-defines shapes with custom canvas rendering.
     * @return custom shapes.
     */
    public List<Shape> createShapes() {
        Shape defaultShape = new Shape();

        StarShape star5 = new StarShape(5);

        StarShape star10 = new StarShape(10);

        Shape flowerShape = new Shape("Flower") {
            @Override
            public void onDraw(Canvas canvas, Paint paint) {
                super.onDraw(canvas, paint);
            }
        };

        Shape spikyShape = new Shape("Spiky") {
            @Override
            public void onDraw(Canvas canvas, Paint paint) {
                super.onDraw(canvas, paint);
            }
        };

        return new ArrayList<Shape>() {
            {
                add(defaultShape);
                add(star5);
                add(star10);
                add(flowerShape);
                add(spikyShape);
            }
        };
    }

}
