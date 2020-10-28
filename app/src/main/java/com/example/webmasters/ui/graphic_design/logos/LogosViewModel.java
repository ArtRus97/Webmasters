package com.example.webmasters.ui.graphic_design.logos;

import android.database.Observable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.function.Supplier;

public class LogosViewModel extends ViewModel {
    private MutableLiveData<String> mLogoText = new MutableLiveData<>("Logo Text");

    public LiveData<String> getLogoText() { return mLogoText; }
    public String getLogoString() { return mLogoText.getValue(); }

    public void setLogoText(String logoText) {
        // Redundancy check.
        if (getLogoString().equals(logoText)) return;
        // Update logo text.
        mLogoText.setValue(logoText);
    }
}