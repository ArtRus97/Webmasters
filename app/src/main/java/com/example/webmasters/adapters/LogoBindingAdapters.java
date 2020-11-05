package com.example.webmasters.adapters;

import android.view.MotionEvent;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;


import com.example.webmasters.ui.graphic_design.logos.LogoView;
import com.example.webmasters.ui.shared.ColorButton;

public class LogoBindingAdapters {

    @BindingAdapter("app:textSize")
    public static void setTextSize(LogoView view, int textSize) {
        view.setTextSize(textSize);
    }

    @BindingAdapter("app:text")
    public static void setText(LogoView view, String text) {
        view.setText(text);
    }

    @BindingAdapter({"app:textColor"})
    public static void setTextColor(LogoView view, int textColor) {
        view.setTextColor(textColor);
    }

    @BindingAdapter({"app:customColor"})
    public static void setCustomColor(ColorButton view, int color) {
        view.setColor(color);
    }

    @InverseBindingAdapter(attribute = "app:customColor")
    public static int getCustomColor(ColorButton view) {
        return view.getColor();
    }

    @BindingAdapter("app:customColorAttrChanged")
    public static void onCustomColorAttrChanged(ColorButton view, InverseBindingListener customColorAttrChanged) {
        view.onColorChanged(color -> customColorAttrChanged.onChange());
    }

}
