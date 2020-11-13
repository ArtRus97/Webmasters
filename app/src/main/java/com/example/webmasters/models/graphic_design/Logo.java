package com.example.webmasters.models.graphic_design;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.webmasters.Utils;
import com.example.webmasters.types.IText;

/**
 * Logo is a basic implementation of AbstractLogo class.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 *
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
     * Immutable properties.
     */

    // The text of the logo.
    final private Text mText = new Text(){{
        setSize(DEFAULT_TEXT_SIZE);
    }};

    /**
     * Mutable properties.
     */

    // The shape of the logo.
    private Shape mShape = new Shape(){{
        setScale(DEFAULT_SHAPE_SCALE);
    }};


    @Bindable
    public IText getText() {
        return mText;
    }

    /**
     * setShape sets the shape of the logo.
     * @param shape (Shape)
     */
    public void setShape(@NonNull Shape shape) {
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
    public Shape getShape() {
        return mShape;
    }

    @Override
    public void setTextValue(String textValue) {
        // Check for redundant operation.
        if (getTextValue().equals(textValue)) return;
        // Update text value.
        mText.setValue(textValue);
        // Notify observers.
        notifyPropertyChanged(BR.textValue);
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void setTextSize(int textSize) {
        // Make sure text size does not go over boundaries.
        textSize = Utils.minmax(textSize, MIN_TEXT_SIZE, MAX_TEXT_SIZE).intValue();
        // Check for redundant operation.
        if (getTextSize() == textSize) return;
        // Update text size.
        mText.setSize(textSize);
        // Notify observers.
        notifyPropertyChanged(BR.textSize);
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void setTextColor(int textColor) {
        // Check for redundant operation.
        if (getTextColor() == textColor) return;
        // Update text color.
        mText.setColor(textColor);
        // Notify observers.
        notifyPropertyChanged(BR.textColor);
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void setShapeColor(int shapeColor) {
        // Check for redundant operation.
        if (getShapeColor() == shapeColor) return;
        // Update shape color.
        mShape.setColor(shapeColor);
        // Notify observers.
        notifyPropertyChanged(BR.shapeColor);
        notifyPropertyChanged(BR.shape);
    }

    @Override
    public void setShapeScale(float scale) {
        // Make sure scaling does not go over boundaries.
        scale = Utils.minmax(scale, MIN_SHAPE_SCALE, MAX_SHAPE_SCALE).floatValue();
        // Check for redundant operation.
        if (getShapeScale() == scale) return;
        // Update scale.
        mShape.setScale(scale);
        // Notify observers.
        notifyPropertyChanged(BR.shapeScale);
        notifyPropertyChanged(BR.shape);
    }

    @Override
    public void setTextX(int x) {
        // Check for redundant operation.
        if (getTextX() == x) return;
        // Update text x coordinate.
        mText.setX(x);
        // Notify observers.
        notifyPropertyChanged(BR.textX);
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void setTextY(int y) {
        // Check for redundant operation.
        if (getTextY() == y) return;
        // Update text y coordinate.
        mText.setY(y);
        // Notify observers.
        notifyPropertyChanged(BR.textY);
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void setTextBold(boolean isBold) {
        // Check for redundant operation.
        if (getTextBold() == isBold) return;
        // Update text boldness.
        mText.setBold(isBold);
        // Notify observers.
        notifyPropertyChanged(BR.textBold);
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void setTextItalic(boolean isItalic) {
        // Check for redundant operation.
        if (getTextItalic() == isItalic) return;
        // Update text italic typeface.
        mText.setItalic(isItalic);
        // Notify observers.
        notifyPropertyChanged(BR.textItalic);
        notifyPropertyChanged(BR.text);
    }

    @Override
    public void setShapeX(int x) {
        // Check for redundant operation.
        if (getShapeX() == x) return;
        // Update shape x coordinate.
        mShape.setX(x);
        // Notify observers.
        notifyPropertyChanged(BR.shapeX);
        notifyPropertyChanged(BR.shape);
    }

    @Override
    public void setShapeY(int y) {
        // Check for redundant operation.
        if (getShapeY() == y) return;
        // Update shape y coordinate.
        mShape.setY(y);
        // Notify observers.
        notifyPropertyChanged(BR.shapeY);
        notifyPropertyChanged(BR.shape);
    }


}
