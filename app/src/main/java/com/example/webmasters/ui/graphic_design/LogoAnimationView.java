package com.example.webmasters.ui.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.webmasters.models.graphic_design.Animation;
import com.example.webmasters.models.graphic_design.utils.ShapeAnimator;
import com.example.webmasters.types.IShape;

import java.util.List;

public class LogoAnimationView extends LogoView {
    ShapeAnimator mShapeAnimator = new ShapeAnimator(this);

    public LogoAnimationView(Context context) {
        super(context);
    }


    public void setAnimations(List<Animation> animations) {
        mShapeAnimator.setAnimations(animations);
    }

    @Override
    protected void drawShape(Canvas canvas, IShape shape) {
        mShapeAnimator.drawShape(canvas, shape);
    }

    public LogoAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LogoAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
