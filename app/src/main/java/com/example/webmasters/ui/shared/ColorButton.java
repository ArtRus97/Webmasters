package com.example.webmasters.ui.shared;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.Observable;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.webmasters.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.button.MaterialButton;


public class ColorButton extends MaterialButton implements LifecycleOwner {
    private final LifecycleRegistry registry = new LifecycleRegistry(this);
    final ObservableInt mColor = new ObservableInt(Color.WHITE);
    private Handler mHandler;
    private AlertDialog mDialog = ColorPickerDialogBuilder
            .with(getContext())
            .setTitle("Select color")
            .initialColor(getColor())
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .density(12)
            .setPositiveButton("select", (_dialog, color, _allColors) -> setColor(color))
            .build();

    public void onColorChanged(ObservableInt.OnPropertyChangedCallback callback) {
        mColor.addOnPropertyChangedCallback(callback);
    }


    public ColorButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler(context.getMainLooper());

        // Setup event listeners.
        setOnClickListener(this::onClick);
        mColor.addOnPropertyChangedCallback(new ObservableInt.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                updateColor(mColor.get());
            }
        });

        // Use background tint as initial color if one is set in XML.
        Drawable background = getBackground();
        if (background instanceof ColorDrawable)
            setColor(((ColorDrawable) background).getColor());
        else
            setColor(context.getColor(R.color.colorPrimary));


    }

    public void updateColor(int color) {
        // Make sure button is updated on UI thread.
        Log.d("ASD", "Updating color");
        mHandler.post(() -> {
            setBackgroundColor(color);
            setText(Integer.toHexString(color));
        });
    }

    public void setColor(int color) { mColor.set(color);}


    public int getColor() {
        return mColor.get();
    }

    private void onClick(View self) {
        mDialog.show();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return registry;
    }

}
