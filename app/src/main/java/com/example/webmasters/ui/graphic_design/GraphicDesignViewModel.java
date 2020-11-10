package com.example.webmasters.ui.graphic_design;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webmasters.models.graphic_design.Logo;

public class GraphicDesignViewModel extends ViewModel {
    private boolean mIsInitialized = false;
    private final MutableLiveData<Logo> mLogo = new MutableLiveData<>(new Logo());

    public void setLogo(Logo logo) {
        mLogo.setValue(logo);
    }

    public LiveData<Logo> getLogo() {
        if (!mIsInitialized) {
            mLogo.getValue().setTextValue("Webmasters");
            mIsInitialized = true;
        }
        return mLogo;
    }
}
