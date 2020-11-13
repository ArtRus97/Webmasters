package com.example.webmasters.models.graphic_design;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.webmasters.Utils;

/**
 * Logo is a basic implementation of AbstractLogo class.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 *
 * v 1.0.0 Base class created.
 * v 1.0.1 Observer notifiers added.
 * v 1.1.0 Text typefaces added.
 */
public class Logo extends AbstractLogo  {

    /**
     * Constants
     **/

    // Shape scale constants.
    static final public float MIN_SHAPE_SCALE = 0.5f;
    static final public float MAX_SHAPE_SCALE = 2.0f;
    static final public float DEFAULT_SHAPE_SCALE = (MAX_SHAPE_SCALE + MIN_SHAPE_SCALE) / 2;

    // Text size constants.
    static final public int MIN_TEXT_SIZE = 18;
    static final public int MAX_TEXT_SIZE = 54;
    static final public int DEFAULT_TEXT_SIZE = (MAX_TEXT_SIZE + MIN_TEXT_SIZE) / 2;

    /**
     * Mutable properties.
     */

    // The text of the logo.
    private Text mText = new Text(){{
        setSize(DEFAULT_TEXT_SIZE);
    }};

    // The shape of the logo.
    private Shape mShape = new Shape(){{
        setScale(DEFAULT_SHAPE_SCALE);
    }};


    final public void setText(final Text text) {
        mText = text;
    }

    @Override
    @Bindable
    final public Text getText() {
        return mText;
    }


    final public void setShape(@NonNull final Shape shape) {
        // Update the new shape with the previous one.
        shape.setX(mShape.getX());
        shape.setY(mShape.getY());
        shape.setScale(mShape.getScale());
        shape.setColor(mShape.getColor());
        // Set shape.
        mShape = shape;
        // Notify observers.
        notifyPropertyChanged(BR.shape);
    }

    @Bindable
    @Override
    final public Shape getShape() {
        return mShape;
    }
}
