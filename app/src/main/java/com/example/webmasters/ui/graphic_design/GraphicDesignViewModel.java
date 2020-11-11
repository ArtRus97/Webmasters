package com.example.webmasters.ui.graphic_design;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.webmasters.models.graphic_design.*;

import java.util.ArrayList;
import java.util.List;

public class GraphicDesignViewModel extends ViewModel {
    private final MutableLiveData<Boolean> mIsInitialized;
    private final MutableLiveData<Logo> mLogo;
    private final MutableLiveData<List<Animation>> mAnimations;
    private final MutableLiveData<List<Shape>> mShapes;
    private final MutableLiveData<Integer> mInterval;

    public GraphicDesignViewModel() {
        mInterval = new MutableLiveData<>(10);
        mIsInitialized = new MutableLiveData<>(false);
        mLogo = new MutableLiveData<>(new Logo());
        mShapes = new MutableLiveData<>(new ShapeFactory().createShapes());
        AnimationFactory mAnimationFactory = new AnimationFactory();

        mAnimations = new MutableLiveData<>(new ArrayList<Animation>() {
            {
                add(mAnimationFactory.getAnimation(Animation.Type.Blink, new AnimationSettings() {
                    {
                        interval = getInterval();
                        changePerSecond = 100;
                    }
                }));
                add(mAnimationFactory.getAnimation(Animation.Type.Rotation, new AnimationSettings() {
                    {
                        interval = getInterval();
                        changePerSecond = 90;
                    }
                }));
            }
        });
    }

    public void setLogo(Logo logo) {
        mLogo.setValue(logo);
    }

    public Logo getLogo() {
        if (!getInitialized()) {
            mLogo.getValue().setTextValue("Webmasters");
            mIsInitialized.setValue(true);
        }
        return mLogo.getValue();
    }

    public List<Shape> getShapes() {
        return mShapes.getValue();
    }

    public Integer getInterval() {
        return mInterval.getValue();
    }

    public List<Animation> getAnimations() {
        return mAnimations.getValue();
    }

    public Boolean getInitialized() {
        return mIsInitialized.getValue();
    }

}
