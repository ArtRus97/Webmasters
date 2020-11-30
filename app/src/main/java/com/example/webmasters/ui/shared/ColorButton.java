package com.example.webmasters.ui.shared;

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
import com.example.webmasters.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.button.MaterialButton;


public class ColorButton extends MaterialButton implements LifecycleOwner {
    private final LifecycleRegistry registry = new LifecycleRegistry(this);
    final ObservableInt mColor = new ObservableInt(Color.WHITE);
    private final Handler mHandler;
    private final AlertDialog mDialog = ColorPickerDialogBuilder
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
        mHandler.post(() -> {
            setBackgroundColor(color);
            setText(Integer.toHexString(color));

            Color colorTemp = Color.valueOf(color);

            // Counting the perceptive luminance - human eye favors green color...
            float luminance =
                    (
                        (0.299f * Color.red(color)) +
                        (0.587f * Color.green(color)) +
                        (0.114f * Color.blue(color))
                    ) / 255f;
            Log.d("ASD", luminance+"");

            setTextColor(luminance > 0.5 ? Color.BLACK : Color.WHITE);
        });
    }

    public void setColor(int color) {
        mColor.set(color);
    }


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
