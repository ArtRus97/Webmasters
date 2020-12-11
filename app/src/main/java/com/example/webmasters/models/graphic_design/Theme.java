package com.example.webmasters.models.graphic_design;

import android.graphics.Color;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.webmasters.BR;
import com.example.webmasters.types.ITheme;

public class Theme extends BaseObservable implements ITheme {
    private int mTextColor = Color.WHITE;
    private int mBackgroundColor = Color.parseColor("#212121");


    final public void setTextColor(final int textColor) {
        if (mTextColor == textColor) return;
        mTextColor = textColor;
        notifyPropertyChanged(BR.textColor);
    }

    @Bindable
    final public int getTextColor() {
        return mTextColor;
    }

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
