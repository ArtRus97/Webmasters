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
        Movement,
        SkewX,
        SkewY
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
            case SkewX:
                return skewX(settings);
            case SkewY:
                return skewY(settings);
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
     * skewX creates a new skewX animation configured with the given settings.
     *
     * @param settings (AnimationSettings)
     * @return skewX animation.
     */
    private static Animation skewX(final AnimationSettings settings) {
        // Add skew animation specific settings.
        settings.reverse = true;
        settings.initialValue = 0;
        settings.maximum = 0.5f;

        // Create new skew animation.
        return new Animation("Skew X", settings) {
            @Override
            protected void transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
                canvas.skew(getValue(), 0);
            }
        };
    }

    /**
     * skewY creates a new skewY animation configured with the given settings.
     *
     * @param settings (AnimationSettings)
     * @return skewY animation.
     */
    private static Animation skewY(final AnimationSettings settings) {
        // Add skew animation specific settings.
        settings.reverse = true;
        settings.initialValue = 0;
        settings.maximum = 0.5f;

        // Create new skew animation.
        return new Animation("Skew Y", settings) {
            @Override
            protected void transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
                canvas.skew(0, getValue());
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
        settings.reverse = true;
        settings.initialValue = 0;
        settings.maximum = 50;

        // Create new movement animation.
        return new Animation("Movement", settings) {
            @Override
            protected void transformCanvas(Canvas canvas) {
                canvas.translate(getValue(), 0);
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
        settings.reverse = false;

        // Create new rotation animation.
        return new Animation("Rotation", settings) {
            @Override
            final protected void transformCanvas(Canvas canvas, ICanvasDrawable drawable) {
                canvas.rotate(getValue(), drawable.getX(), drawable.getY());
            }
        };
    }


}
