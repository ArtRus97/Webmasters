package com.example.webmasters.models.graphic_design.view_models;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.types.ILogo;

public class LogoViewModel extends ViewModel implements ILogo {
    private final MutableLiveData<String> mText = new MutableLiveData<>("Webmasters");
    private final MutableLiveData<Integer> mTextSize = new MutableLiveData<>(12);
    private final MutableLiveData<Integer> mTextColor = new MutableLiveData<>(Color.WHITE);

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
    public void setTextSize(int size) {
        if (getTextSize() == size) return;
        mTextSize.setValue((int) size);
    }

    @Override
    public int getTextSize() {
        if (mTextSize.getValue() != null)
            return mTextSize.getValue();
        else
            return 0;
    }

    public LiveData<Integer> getTextSizeObservable() {
        return mTextSize;
    }

    @Override
    public void setTextColor(int color) {
        if (getTextColor() == color) return;
        mTextColor.setValue(color);
    }

    @Override
    public int getTextColor() {
        if (mTextColor.getValue() != null)
            return mTextColor.getValue();
        else
            return Color.WHITE;
    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void setSize(int size) {

    }

    @Override
    public int getSize() {
        return 0;
    }

    public LiveData<Integer> getTextColorObservable() { return mTextColor; }
}