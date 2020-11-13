package com.example.webmasters.ui.graphic_design;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.webmasters.models.graphic_design.*;
import com.example.webmasters.models.graphic_design.utils.AnimationFactory;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
import com.example.webmasters.types.IAnimationViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class GraphicDesignViewModel extends ViewModel implements IAnimationViewModel {
    private final MutableLiveData<Boolean> mIsInitialized;
    private final MutableLiveData<List<Boolean>> mAnimationStates;
    private final MutableLiveData<Logo> mLogo;
    private final MutableLiveData<List<Animation>> mAnimations;
    private final MutableLiveData<List<Shape>> mShapes;

    /**
     * Default constructor.
     */
    public GraphicDesignViewModel() {
        mIsInitialized = new MutableLiveData<>(false);
        mLogo = new MutableLiveData<>(new Logo());

        mShapes = new MutableLiveData<>();
        createShapes();

        mAnimations = new MutableLiveData<>();
        mAnimationStates = new MutableLiveData<>();
        createAnimations();
    }

    /**
     * createAnimations adds some preset animations to the view model.
     *
     * Notes: Add any preset animations here!
     */
    private void createAnimations() {
        AnimationFactory animationFactory = new AnimationFactory();

        // Create pre-defined animations.
        ArrayList<Animation> animations = new ArrayList<Animation>() {{
            add(animationFactory.getAnimation(AnimationFactory.AnimationType.Blink));
            add(animationFactory.getAnimation(AnimationFactory.AnimationType.Rotation));
        }};

        ArrayList<Boolean> animationStates = new ArrayList<Boolean>();

        // Add some model related configurations.
        animations.forEach(animation -> {
            animation.setChangePerSecond(animation.MAXIMUM / 2);
            animationStates.add(false);
        });

        mAnimationStates.setValue(animationStates);
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

    public LiveData<Logo> getLogoObservable() {
        return mLogo;
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



    public List<Animation> getAnimations() {
        return mAnimations.getValue();
    }

    public List<Boolean> getAnimationStates() {
        return mAnimationStates.getValue();
    }

    public Boolean getInitialized() {
        return mIsInitialized.getValue();
    }

}
