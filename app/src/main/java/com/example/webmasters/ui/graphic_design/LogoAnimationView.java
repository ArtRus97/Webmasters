package com.example.webmasters.ui.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.webmasters.models.graphic_design.Animation;
import com.example.webmasters.models.graphic_design.utils.DrawableAnimator;
import com.example.webmasters.types.IShape;
import com.example.webmasters.types.IText;

import java.util.List;

/**
 * LogoAnimationView extends the LogoView to
 * allow animating of different logo components.
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class LogoAnimationView extends LogoView {
    // Animator used to animate shapes.
    final DrawableAnimator mShapeAnimator;
    // Animator used to animate texts.
    final DrawableAnimator mTextAnimator;

    public LogoAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mShapeAnimator = new DrawableAnimator(this);
        mTextAnimator = new DrawableAnimator(this);
    }

    public void setAnimations(List<Animation> animations) {
        mShapeAnimator.setAnimations(animations);
    }

    @Override
    protected void drawShape(Canvas canvas, IShape shape) {
        mShapeAnimator.draw(canvas, shape);
    }

    @Override
    protected void drawText(Canvas canvas, IText text) {
        mTextAnimator.draw(canvas, text);
    }
}
