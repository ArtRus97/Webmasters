package com.example.webmasters.models.graphic_design;

import android.graphics.Color;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.shared.ObservableLiveData;
import com.example.webmasters.types.ILogo;
import com.example.webmasters.ui.graphic_design.logos.LogoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LogoViewModel extends BaseObservable implements ILogo {
    private Logo mLogo = new Logo();
    private final List<Integer> mPosition = new ArrayList<>();

    public LogoViewModel() {
        mPosition.add(0);
        mPosition.add(0);
    }


    public void setText(String text) {
        if (mLogo.text.value.equals(text)) return;
        mLogo.setText(text);
        notifyPropertyChanged(BR.text);
    }

    @Bindable
    public String getText() {
        return mLogo.getText();
    }


    public void setTextSize(int textSize) {
        if (mLogo.text.size == textSize) return;
        mLogo.setTextSize(textSize);
        notifyPropertyChanged(BR.textSize);
    }

    @Bindable
    public int getTextSize() {
        return mLogo.getTextSize();
    }


    public void setTextColor(int textColor) {
        if (mLogo.text.color == textColor) return;
        mLogo.setTextColor(textColor);
        notifyPropertyChanged(BR.textColor);
    }

    @Bindable
    public int getTextColor() {
        return mLogo.getTextColor();
    }


    public void setShapeColor(int shapeColor) {
        if (mLogo.shape.color == shapeColor) return;
        mLogo.setShapeColor(shapeColor);
        notifyPropertyChanged(BR.shapeColor);
    }

    @Bindable
    public int getShapeColor() {
        return mLogo.getShapeColor();
    }


    public void setShapeScale(float scale) {
        if (mLogo.shape.scale == scale) return;
        mLogo.setShapeScale(scale);
        notifyPropertyChanged(BR.shapeScale);
    }

    @Bindable
    public float getShapeScale() {
        return mLogo.getShapeScale();
    }


    public void setTextX(int x) {
        if (getTextX() == x) return;
        mPosition.set(0, x);
        notifyPropertyChanged(BR.textX);
    }

    @Bindable
    public int getTextX() {
        return mPosition.get(0);
    }


    public void setTextY(int y) {
        if  (getTextY() == y) return;
        mPosition.set(1, y);
        notifyPropertyChanged(BR.textY);
    }

    @Bindable
    public int getTextY() {
        return mPosition.get(1);
    }

}