package com.example.webmasters.models.graphic_design;

import androidx.annotation.Nullable;

public class AnimationFactory {

    public Animation getAnimation(Animation.Type animationType, @Nullable AnimationSettings settings) {
        switch (animationType) {
            case Blink:
                return Animation.blink(settings);
            case Rotation:
                return Animation.rotation(settings);
        }
        return null;
    }
}
