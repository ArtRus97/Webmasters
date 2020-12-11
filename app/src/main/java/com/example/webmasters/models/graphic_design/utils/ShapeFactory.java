package com.example.webmasters.models.graphic_design.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.types.ShapeType;

import java.util.*;


/**
 * ShapeFactory defines methods to create some pre-defined shapes.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class ShapeFactory {

    /**
     * getShape returns a default shape of the specified shape type.
     *
     * @param shapeType    (ShapeType) of the shape.
     * @param specialParam (int) used to customize the shape.
     */
    public Shape getShape(ShapeType shapeType, int specialParam) {
        switch (shapeType) {
            case STAR:
                return star(specialParam);
            case FLOWER:
                return flower();
        }
        return null;
    }

    /**
     * applyShapeType returns a new shape with the same parameters
     * as the given one, but as of the given shape type.
     */
    public static Shape applyShapeType(Shape shape, ShapeType shapeType) {
        Shape newShape = (new ShapeFactory()).getShape(shapeType, shape.getParameter());
        newShape.setScale(shape.getScale());
        newShape.setColor(shape.getColor());
        newShape.setX(shape.getX());
        newShape.setY(shape.getY());
        newShape.setShadow(shape.getShadow());
        return newShape;
    }


    /**
     * star creates a new star shape with the number of given spike.
     *
     * @param numSpikes (int) number of star's spikes.
     * @return star shape.
     */
    private static Shape star(final int numSpikes) {

        return new Shape() {
            // Shape constants.
            private final int mOuterRadius;
            private final int mInnerRadius;

            // Initializer.
            {
                mName = numSpikes + "-spiked star";
                mType = ShapeType.STAR;
                mParameter = 8;
                mOuterRadius = 100;
                mInnerRadius = mOuterRadius / 2;
            }

            @Override
            protected void onDraw(Canvas canvas, Paint paint) {
                Path starPath = new Path();
                float rotation = 3 * (float) Math.PI / 2f;
                float x;
                float y;
                final float STEP = (float) Math.PI / mParameter;

                for (int spikeIndex = 0; spikeIndex < mParameter; spikeIndex++) {
                    x = getX() + (float) Math.cos(rotation) * mOuterRadius;
                    y = getY() + (float) Math.sin(rotation) * mOuterRadius;
                    if (spikeIndex == 0)
                        starPath.moveTo(x, y);
                    starPath.lineTo(x, y);
                    rotation += STEP;

                    x = getX() + (float) Math.cos(rotation) * mInnerRadius;
                    y = getY() + (float) Math.sin(rotation) * mInnerRadius;
                    starPath.lineTo(x, y);
                    rotation += STEP;
                }
                starPath.close();

                canvas.drawPath(starPath, paint);
            }

            @Override
            public Paint getPaint(Context context) {
                Paint paint = super.getPaint(context);
                paint.setStrokeWidth(8f);
                return paint;
            }
        };
    }

    /**
     * flower creates a new flower shape with the number ...
     *
     * @return flower shape.
     */
    private static Shape flower() {
        return new Shape() {
            // Initializer.
            {
                mType = ShapeType.FLOWER;
                mName = "Flower";
                mParameter = 0;
            }

            @Override
            protected void onDraw(Canvas canvas, Paint paint) {
                Path path = new Path();
                float NUM_OVALS = 7f;
                for (int ovalIndex = 0; ovalIndex < NUM_OVALS; ovalIndex++) {
                    double fraction = 2 * Math.PI * (ovalIndex / NUM_OVALS);
                    float y = (float) (getY() + Math.sin(fraction) * 50);
                    float x = (float) (getX() + Math.cos(fraction) * 50);
                    path.addCircle(x, y, 10, Path.Direction.CW);
                }
                canvas.drawPath(path, paint);
            }
        };
    }

    /**
     * defaultShape returns the default shape of the factory.
     *
     * @return default shape.
     */
    public static Shape defaultShape() {
        return flower();
    }

}
