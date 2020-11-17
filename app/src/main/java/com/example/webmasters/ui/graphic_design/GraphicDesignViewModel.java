package com.example.webmasters.ui.graphic_design;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.webmasters.BR;
import com.example.webmasters.models.graphic_design.Animation;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.models.graphic_design.utils.AnimationFactory;
import com.example.webmasters.services.FirebaseService;
import com.example.webmasters.types.IAnimationViewModel;
import com.example.webmasters.types.ShapeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
public class GraphicDesignViewModel extends ViewModel implements IAnimationViewModel {
    private final MutableLiveData<List<Boolean>> mAnimationStates;
    private final MutableLiveData<Logo> mLogo = new MutableLiveData<>();
    private final MutableLiveData<List<Animation>> mAnimations;
    private final MutableLiveData<List<ShapeType>> mShapeTypes = new MutableLiveData<>();

    /**
     * Default constructor.
     */
    public GraphicDesignViewModel() {
        // Try to fetch the user logo from firestore.
        (new FirebaseService()).getLogo(this::onLogoFetched);

        // Add different shape types.
        mShapeTypes.setValue(Arrays.stream(ShapeType.values()).collect(Collectors.toList()));

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

        ArrayList<Boolean> animationStates = new ArrayList<>();

        // Add some model related configurations.
        animations.forEach(animation -> {
            animation.setChangePerSecond(animation.MAXIMUM / 2);
            animationStates.add(false);
        });

        mAnimationStates.setValue(animationStates);
        mAnimations.setValue(animations);
    }

    public Shape getShape() {
        return mLogo.getValue().getShape();
    }

    public LiveData<List<ShapeType>> getShapeTypes() {
        return mShapeTypes;
    }

    public LiveData<Logo> getLogo() {
        return mLogo;
    }


    @Override
    public List<Animation> getAnimations() {
        return mAnimations.getValue();
    }

    @Override
    public List<Boolean> getAnimationStates() {
        return mAnimationStates.getValue();
    }


    @Override
    protected void onCleared() {
        // Store logo to firestore.
        (new FirebaseService()).addLogo(mLogo.getValue());
        // Handle the actual view model clear.
        super.onCleared();
    }

    private void onLogoFetched(Logo logo) {
        // Create default logo if user does not have one.
        if (logo == null) {
            logo = new Logo(){{
                setTextValue("Webmasters");
                setTextX(50);
                setTextY(50);
                setShapeX(50);
                setShapeY(50);
            }};
        }
        mLogo.postValue(logo);
    }
}
