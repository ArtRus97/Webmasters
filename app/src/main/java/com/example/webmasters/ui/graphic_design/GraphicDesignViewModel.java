package com.example.webmasters.ui.graphic_design;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.webmasters.models.graphic_design.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class GraphicDesignViewModel extends ViewModel {
    private final MutableLiveData<Boolean> mIsInitialized;
    private final MutableLiveData<Logo> mLogo;
    private final MutableLiveData<List<Animation>> mAnimations;
    private final MutableLiveData<List<Shape>> mShapes;
    private final MutableLiveData<Integer> mInterval;

    /**
     * Default constructor.
     */
    public GraphicDesignViewModel() {
        mInterval = new MutableLiveData<>(10);
        mIsInitialized = new MutableLiveData<>(false);
        mLogo = new MutableLiveData<>(new Logo());

        mShapes = new MutableLiveData<>();
        createShapes();

        mAnimations = new MutableLiveData<>();
        createAnimations();
    }

    /**
     * createAnimations adds some preset animations to the view model.
     *
     * Notes: Add any preset animations here!
     */
    private void createAnimations() {
        AnimationFactory animationFactory = new AnimationFactory();

        ArrayList<Animation> animations = new ArrayList<Animation>() {{
            add(animationFactory.getAnimation(Animation.Type.Blink));
            add(animationFactory.getAnimation(Animation.Type.Rotation));
        }};

        animations.forEach(animation -> {
            Log.d("ASDddd", animation.MAXIMUM+"");
            animation.setInterval(getInterval());
            animation.setChangePerSecond(animation.MAXIMUM / 2);
        });

        mAnimations.setValue(animations);
    }

    /**
     * createShapes adds some preset shapes to the view model.
     *
     * Notes: Add any preset shapes here!
     */
    private void createShapes() {
        ShapeFactory shapeFactory = new ShapeFactory();
        mShapes.setValue(shapeFactory.createShapes());
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
