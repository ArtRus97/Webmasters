package com.example.webmasters.models.graphic_design.view_models;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.types.ILogo;

public class LogoViewModel extends ViewModel implements ILogo {
    private final MutableLiveData<String> mText = new MutableLiveData<>("");
    private final MutableLiveData<Float> mTextSize = new MutableLiveData<>(0f);
    private final MutableLiveData<Integer> mTextColor = new MutableLiveData<>(0);

    @Override
    public void setText(String text) {
        if (getText().equals(text)) return;
        mText.setValue(text);
    }

    @Override
    public String getText() {
        return mText.getValue();
    }

    public LiveData<String> getTextObservable() {
        return mText;
    }

    @Override
    public void setTextSize(float size) {
        mTextSize.setValue(size);
    }

    @Override
    public float getTextSize() {
        if (mTextSize.getValue() != null)
            return mTextSize.getValue();
        else
            return 0f;
    }

    public LiveData<Float> getTextSizeObservable() {
        return mTextSize;
    }

    @Override
    public void setTextColor(int color) {
        mTextColor.setValue(color);
    }

    @Override
    public int getTextColor() {
        if (mTextColor.getValue() != null)
            return mTextColor.getValue();
        else
            return Color.WHITE;
    }

    public Logo getLogo() {
        Logo logo = new Logo();
        logo.setTextColor(getTextColor());
        logo.setText(getText());
        logo.setTextSize(getTextSize());
        return logo;
    }

}