package com.example.webmasters.ui.graphic_design;

import android.util.Log;
import androidx.databinding.InverseMethod;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.example.webmasters.models.graphic_design.*;
import com.example.webmasters.models.graphic_design.utils.AnimationFactory;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
import com.example.webmasters.services.FirebaseService;
import com.example.webmasters.types.IAnimationViewModel;
import com.example.webmasters.types.ShapeType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class GraphicDesignViewModel extends ViewModel implements IAnimationViewModel {
    private final String TAG = "GraphicDesignViewModel";
    private final MutableLiveData<Boolean> mIsInitialized;
    private final MutableLiveData<List<Boolean>> mAnimationStates;
    private final MutableLiveData<Logo> mLogo;
    private final MutableLiveData<List<Animation>> mAnimations;
    private final MutableLiveData<List<ShapeType>> mShapeTypes = new MutableLiveData<>();


    /**
     * Default constructor.
     */
    public GraphicDesignViewModel() {
        mIsInitialized = new MutableLiveData<>(false);
        mLogo = new MutableLiveData<>(new Logo());

        mShapeTypes.setValue(new ArrayList<ShapeType>() {{
            add(ShapeType.FLOWER);
            add(ShapeType.STAR);
        }});

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
            add(animationFactory.getAnimation(AnimationFactory.AnimationType.Movement));
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

    public void setLogo(Logo logo) {
        mLogo.setValue(logo);
    }



    public Shape getShape() {
        return mLogo.getValue().getShape();
    }

    public LiveData<List<ShapeType>> getShapeTypes() {
        return mShapeTypes;
    }

    public LiveData<Logo> getLogoObservable() {
        return mLogo;
    }

    public Logo getLogo() {
        if (!getInitialized().getValue()) {
            mLogo.getValue().setTextValue("Webmasters");
            mIsInitialized.setValue(true);
        }
        return mLogo.getValue();
    }


    public List<Animation> getAnimations() {
        return mAnimations.getValue();
    }

    public List<Boolean> getAnimationStates() {
        return mAnimationStates.getValue();
    }

    public LiveData<Boolean> getInitialized() {
        return mIsInitialized;
    }

    @Override
    protected void onCleared() {
        // Store logo.
        (new FirebaseService()).addLogo(mLogo.getValue());

        super.onCleared();
    }
}
