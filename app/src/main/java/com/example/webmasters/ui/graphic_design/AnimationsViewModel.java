package com.example.webmasters.ui.graphic_design;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnimationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnimationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}