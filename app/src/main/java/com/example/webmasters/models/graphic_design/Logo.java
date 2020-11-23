package com.example.webmasters.models.graphic_design;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.webmasters.Utils;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
import com.google.firebase.firestore.Exclude;

/**
 * Logo is a basic implementation of AbstractLogo class.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 * <p>
 * v 1.0.0 Base class created.
 * v 1.0.1 Observer notifiers added.
 * v 1.1.0 Text typefaces added.
 */
public class Logo extends AbstractLogo {

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
    private Text mText;

    // The shape of the logo.
    private Shape mShape;


    /**
     * Default constructor.
     */
    public Logo() {
        setText(new Text() {{
            setSize(DEFAULT_TEXT_SIZE);
        }});
        setShape(ShapeFactory.defaultShape());
    }

    @Override
    final public void setText(final Text text) {
        // Update property listeners.
        if (mText != null)
            mText.removeOnPropertyChangedCallback(this.onTextPropertyChanged);
        text.addOnPropertyChangedCallback(this.onTextPropertyChanged);
        // Set text.
        mText = text;
        // Notify observers.
        notifyPropertyChanged(BR.text);
    }

    @Override
    @Bindable
    final public Text getText() {
        return mText;
    }


    @Override
    final public void setShape(final Shape shape) {
        // Update property listeners.
        if (mShape != null)
            mShape.removeOnPropertyChangedCallback(this.onShapePropertyChanged);
        shape.addOnPropertyChangedCallback(this.onShapePropertyChanged);
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
