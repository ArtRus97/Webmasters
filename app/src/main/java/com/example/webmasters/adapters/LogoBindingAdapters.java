package com.example.webmasters.adapters;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.Observable;


import com.example.webmasters.ui.shared.ColorButton;

public class LogoBindingAdapters {


    @BindingAdapter(value = {"app:color", "app:colorAttrChanged"}, requireAll = false)
    public static void setColor(ColorButton view, int color, final InverseBindingListener listener) {
        view.setColor(color);
        view.onColorChanged(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                listener.onChange();
            }
        });
    }

    @InverseBindingAdapter(attribute = "app:color", event = "app:colorAttrChanged")
    public static int getColor(ColorButton view) {
        return view.getColor();
    }


}
