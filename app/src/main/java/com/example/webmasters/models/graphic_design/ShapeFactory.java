package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ShapeFactory {
    public Shape[] createShapes() {
        Shape defaultShape = new Shape();


        Shape starShape = new Shape("Star") {

            @Override
            public void onDraw(Canvas canvas, Paint paint) {
                float NUM_OVALS = 7f;
                for (int ovalIndex = 0; ovalIndex < NUM_OVALS; ovalIndex++) {
                    double fraction = 2 * Math.PI * (ovalIndex / NUM_OVALS);
                    float y = (float) (getY() + Math.sin(fraction) * 50);
                    float x = (float) (getX() + Math.cos(fraction) * 50);
                    canvas.drawLine(getX(), getY(), x, y, paint);
                }
            }
        };

        return new Shape[] {defaultShape, starShape};
    }
}
