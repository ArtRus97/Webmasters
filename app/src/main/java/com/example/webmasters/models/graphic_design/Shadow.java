package com.example.webmasters.models.graphic_design;

import android.graphics.Color;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.example.webmasters.types.IShadow;

/**
 * Shadow is a basic implementation of IShadow interface.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 * <p>
 * v 1.0.0 Base class created.
 */
public class Shadow extends BaseObservable implements IShadow {
    // Name of the shadow.
    private String mName;
    // "Offset" of the shadow.
    private int mPosition[] = {0, 0};
    // Color of the shadow.
    private int mColor = Color.DKGRAY;

    /**
     * Default constructor.
     */
    public Shadow() {
        this("Unnamed");
    }

    public Shadow(final String name) {
        mName = name;
    }

    /**
     * setColor sets the color of shadow.
     *
     * @param color (int) of shadow.
     */
    final public void setColor(final int color) {
        if (mColor == color) return;
        mColor = color;
        notifyPropertyChanged(BR.color);
    }

    @Override
    @Bindable
    final public int getColor() {
        return mColor;
    }

    /**
     * setName sets the name of shadow.
     *
     * @param name (String) of shadow.
     */
    final public void setName(final String name) {
        if (mName.equals(name)) return;
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Override
    @Bindable
    final public String getName() {
        return mName;
    }

    /**
     * set
     *
     * @param x
     */
    final public void setX(final int x) {
        if (getX() == x) return;
        mPosition[0] = x;
        notifyPropertyChanged(BR.x);
    }

    @Override
    @Bindable
    public int getX() {
        return mPosition[0];
    }

    final public void setY(final int y) {
        if (getY() == y) return;
        mPosition[1] = y;
        notifyPropertyChanged(BR.y);
    }

    @Override
    @Bindable
    public int getY() {
        return mPosition[1];
    }
}
