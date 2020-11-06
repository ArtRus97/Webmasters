package com.example.webmasters.adapters;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.Observable;


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

    @BindingAdapter(value = {"app:customColor", "app:customColorAttrChanged"}, requireAll = false)
    public static void setCustomColor(ColorButton view, int color, final InverseBindingListener listener) {
        view.setColor(color);
        view.onColorChanged(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                listener.onChange();
            }
        });
    }

    @InverseBindingAdapter(attribute = "app:customColor", event = "app:customColorAttrChanged")
    public static int getCustomColor(ColorButton view) {
        return view.getColor();
    }


}