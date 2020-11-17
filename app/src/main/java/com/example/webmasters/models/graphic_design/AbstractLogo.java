package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
import com.example.webmasters.types.ILogo;
import com.example.webmasters.types.ShapeType;
import com.google.firebase.firestore.Exclude;

/**
 * AbstractLogo implements some basic functionality of ILogo interface.
 * <p>
 * v 1.0.0 Base class created.
 * v 1.1.0 Move ICanvasDrawable implementations/overrides from Logo to AbstractLogo
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 */
abstract public class AbstractLogo extends BaseObservable implements ILogo {
    /**
     * setShape sets the shape of logo
     *
     * @param shape (Shape) of logo.
     */
    abstract void setShape(final Shape shape);

    @Override
    @Bindable
    abstract public Shape getShape();

    /**
     * setText sets the text of logo.
     *
     * @param text (Text) of logo.
     */
    abstract void setText(final Text text);

    @Override
    @Bindable
    abstract public Text getText();


    /**
     * setTextValue sets the logo text value.
     *
     * @param value (String) of the logo text.
     */
    final public void setTextValue(final String value) {
        getText().setValue(value);
        notifyPropertyChanged(BR.text);
    }

    @Override
    @Exclude
    @Bindable
    final public String getTextValue() {
        return getText().getValue();
    }

    final public void setTextSize(final int textSize) {
        getText().setSize(textSize);
        notifyPropertyChanged(BR.text);
    }


    @Override
    @Exclude
    @Bindable
    final public int getTextSize() {
        return getText().getSize();
    }

    final public void setTextColor(final int textColor) {
        getText().setColor(textColor);
        notifyPropertyChanged(BR.text);
    }

    @Override
    @Exclude
    @Bindable
    final public int getTextColor() {
        return getText().getColor();
    }


    final public void setTextX(final int textX) {
        getText().setX(textX);
        notifyPropertyChanged(BR.text);
    }


    @Override
    @Exclude
    @Bindable
    final public int getTextX() {
        return getText().getX();
    }


    final public void setTextY(final int textY) {
        getText().setY(textY);
        notifyPropertyChanged(BR.text);
    }

    @Override
    @Exclude
    @Bindable
    final public int getTextY() {
        return getText().getY();
    }


    final public void setTextBold(final boolean isBold) {
        getText().setBold(isBold);
        notifyPropertyChanged(BR.text);
    }

    @Override
    @Exclude
    @Bindable
    final public boolean getTextBold() {
        return getText().isBold();
    }


    final public void setTextItalic(final boolean isItalic) {
        getText().setItalic(isItalic);
        notifyPropertyChanged(BR.text);
    }

    @Override
    @Exclude
    @Bindable
    final public boolean getTextItalic() {
        return getText().isItalic();
    }


    final public void setShapeScale(final float shapeScale) {
        getShape().setScale(shapeScale);
        notifyPropertyChanged(BR.shape);
    }

    @Override
    @Exclude
    @Bindable
    final public float getShapeScale() {
        return getShape().getScale();
    }

    final public void setShapeColor(final int shapeColor) {
        getShape().setColor(shapeColor);
        notifyPropertyChanged(BR.shape);
    }

    @Override
    @Exclude
    @Bindable
    final public int getShapeColor() {
        return getShape().getColor();
    }


    /**
     * setShapeX sets the horizontal coordinate of logo shape.
     *
     * @param x (int) horizontal coordinate of logo shape.
     */
    final public void setShapeX(final int x) {
        getShape().setX(x);
        notifyPropertyChanged(BR.shape);
    }

    @Override
    @Exclude
    @Bindable
    final public int getShapeX() {
        return getShape().getX();
    }

    /**
     * setShapeY sets the vertical coordinate of logo shape..
     *
     * @param y (int) vertical coordinate of logo shape.
     */
    final public void setShapeY(final int y) {
        getShape().setY(y);
        notifyPropertyChanged(BR.shape);
    }


    @Override
    @Exclude
    @Bindable
    final public int getShapeY() {
        return getShape().getY();
    }

    /**
     * setShapeType sets the logo shape type
     *
     * @param shapeType (ShapeType) of the shape.
     */
    final public void setShapeType(final ShapeType shapeType) {
        getShape().setType(shapeType);
        notifyPropertyChanged(BR.shape);
    }

    @Override
    @Exclude
    @Bindable
    public ShapeType getShapeType() {
        return getShape().getType();
    }

    @Override
    final public int getX() {
        // Logo x-coordinate should always be at 0.
        return 0;
    }

    @Override
    final public int getY() {
        // Logo y-coordinate should always be at 0.
        return 0;
    }

    @Override
    final public Paint getPaint(final Context context) {
        // Logo does not have a Paint itself.
        return null;
    }

    @Override
    final public void drawOnCanvas(final Canvas canvas, final Context context) {
        // Draw the sub-components.
        getShape().drawOnCanvas(canvas, context);
        getText().drawOnCanvas(canvas, context);
    }

    @Override
    final public void drawOnCanvas(final Canvas canvas, final Paint paint) {
        // Draw the sub-components with the given paint.
        getShape().drawOnCanvas(canvas, paint);
        getText().drawOnCanvas(canvas, paint);
    }


    // onShapePropertyChanged propagates the change from logo's shape to the corresponding logo property.
    final protected OnPropertyChangedCallback onShapePropertyChanged = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            switch (propertyId) {
                case BR.x:
                    notifyPropertyChanged(BR.shapeX);
                    break;
                case BR.y:
                    notifyPropertyChanged(BR.shapeY);
                    break;
                case BR.color:
                    notifyPropertyChanged(BR.shapeColor);
                    break;
                case BR.scale:
                    notifyPropertyChanged(BR.shapeScale);
                    break;
                case BR.type:
                    // Update shape draw function.
                    setShape(ShapeFactory.applyShapeType(getShape(), getShapeType()));
                    notifyPropertyChanged(BR.shapeType);
                    break;
            }
        }
    };

    // onTextPropertyChanged propagates the change from logo's text to the corresponding logo property.
    final protected OnPropertyChangedCallback onTextPropertyChanged = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            switch (propertyId) {
                case BR.x:
                    notifyPropertyChanged(BR.textX);
                    break;
                case BR.y:
                    notifyPropertyChanged(BR.textY);
                    break;
                case BR.bold:
                    notifyPropertyChanged(BR.textBold);
                    break;
                case BR.italic:
                    notifyPropertyChanged(BR.textItalic);
                    break;
                case BR.color:
                    notifyPropertyChanged(BR.textColor);
                    break;
                case BR.value:
                    notifyPropertyChanged(BR.textValue);
                    break;
                case BR.size:
                    notifyPropertyChanged(BR.textSize);
                    break;
            }
        }
    };
}
