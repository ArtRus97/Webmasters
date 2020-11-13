package com.example.webmasters.models.graphic_design.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.example.webmasters.models.graphic_design.Animation;
import com.example.webmasters.models.graphic_design.AnimationSettings;
import com.example.webmasters.types.ICanvasDrawable;

/**
 * AnimationFactory defines methods to create some pre-defined animations.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class AnimationFactory {

    // Pre-defined animation types.
    public enum AnimationType {
        Rotation,
        Blink,
        Movement
    }

    /**
     * getAnimation returns a default animation of the specified animation type.
     *
     * @param animationType (AnimationType) of the animation.
     * @return default animation of the given type.
     */
    public Animation getAnimation(AnimationType animationType) {
        return getAnimation(animationType, new AnimationSettings() {
        });
    }

    public Animation getAnimation(AnimationType animationType, AnimationSettings settings) {
        switch (animationType) {
            case Blink:
                return blink(settings);
            case Rotation:
                return rotation(settings);
            case Movement:
                return movement(settings);
        }
        return null;
    }

    /**
     * blink creates a new blink animation configured with the given settings.
     *
     * @param settings (AnimationSettings)
     * @return blink animation.
     */
    private static Animation blink(final AnimationSettings settings) {
        // Add blink animation specific settings.
        settings.reverse = true;
        settings.maximum = 255;
        settings.initialValue = 255;

        // Create new blink animation.
        return new Animation("Blink", settings) {
            @Override
            protected void transformPaint(final Paint paint) {
                paint.setAlpha((int) getValue());
            }
        };
    }

    /**
     * movement creates a new movement animation configured with the given settings.
     *
     * @param settings (AnimationSettings)
     * @return movement animation.
     */
    private static Animation movement(final AnimationSettings settings) {
        // Add movement animation specific settings.
        settings.reverse = false;
        settings.initialValue = 0;
        settings.maximum = 50;

        // Create new movement animation.
        return new Animation("Movement", settings) {
            @Override
            protected boolean transformCanvas(Canvas canvas) {
                canvas.translate(getValue(), 0);
                return true;
            }
        };
    }

    /**
     * rotation creates a new rotation animation configured with the given settings.
     *
     * @param settings (AnimationSettings)
     * @return rotation animation.
     */
    private static Animation rotation(final AnimationSettings settings) {
        // Add rotation animation specific settings.
        settings.maximum = 360f;
        settings.reverse = true;

        // Create new rotation animation.
        return new Animation("Rotation", settings) {
            @Override
            final protected boolean transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
                canvas.rotate(getValue(), drawable.getX(), drawable.getY());
                return true;
            }
        };
    }


}
