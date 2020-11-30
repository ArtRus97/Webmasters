package com.example.webmasters.models.graphic_design;

import android.graphics.Color;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.webmasters.BR;
import com.example.webmasters.types.ITheme;

public class Theme extends BaseObservable implements ITheme {
    private int mBackgroundColor = Color.BLACK;

    final public void setBackgroundColor(final int bgColor) {
        if (mBackgroundColor == bgColor) return;
        mBackgroundColor = bgColor;
        notifyPropertyChanged(BR.backgroundColor);
    }

    @Bindable
    final public int getBackgroundColor() {
        return mBackgroundColor;
    }
}
