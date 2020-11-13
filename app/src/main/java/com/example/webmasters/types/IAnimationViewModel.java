package com.example.webmasters.types;

import com.example.webmasters.models.graphic_design.Animation;

import java.util.List;

public interface IAnimationViewModel {
    List<Animation> getAnimations();
    List<Boolean> getAnimationStates();
}
