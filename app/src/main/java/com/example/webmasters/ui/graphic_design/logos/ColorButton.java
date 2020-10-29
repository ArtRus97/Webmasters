package com.example.webmasters.ui.graphic_design.logos;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.webmasters.R;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.button.MaterialButton;


public class ColorButton extends MaterialButton {
    private int mColor;
    private Handler mHandler;

    public ColorButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler(context.getMainLooper());

        // Use background tint as initial color if one is set in XML.
        Drawable background = getBackground();
        if (background instanceof ColorDrawable)
            setColor(((ColorDrawable) background).getColor());
        else
            setColor(context.getColor(R.color.colorPrimary));

        // Setup event listeners.
        setOnClickListener(this::onClick);
    }

    public void setColor(int color) {
        mHandler.post(() -> {
            setBackgroundColor(color);
            setText(Integer.toHexString(color));
        });
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    private void onClick(View self) {
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Select color")
                .initialColor(mColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setPositiveButton("select", (_dialog, color, _allColors) -> setColor(color))
                .build()
                .show();
    }

}
