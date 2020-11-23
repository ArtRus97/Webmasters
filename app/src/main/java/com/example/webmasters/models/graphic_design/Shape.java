package com.example.webmasters.models.graphic_design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import com.example.webmasters.BR;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
import com.example.webmasters.types.IShape;
import com.example.webmasters.types.ShapeType;

import java.util.Objects;

/**
 * Shape is a basic implementation of IShape interface.
 *
 * @author JIkaheimo (Jaakko Ikäheimo)
 * <p>
 * v 1.0.0 Base class created.
 * v 1.0.1 Observer notifiers added.
 * v 1.1.0 Shape shadow added.
 */
public class Shape extends BaseObservable implements IShape {
    // The shadow of the shaper
    private Shadow mShadow;
    // The name of the shape.
    protected String mName = "Unnamed";
    // The type of the shape.
    protected ShapeType mType;
    // The parameter of the shape.
    protected int mParameter;
    // The position of the shape.
    private int[] mPosition = {0, 0};
    // The color of the shape.
    private int mColor = Color.GREEN;
    // The scale of the shape.
    private float mScale = 1.0f;

    /**
     * Default constructor.
     */
    public Shape() {
        setShadow(new Shadow("Shape"));
    }

    public Shape(ShapeType type, String name) {
        this(name);
        mType = type;
    }

    public Shape(String name) {
        this();
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    /**
     * setParameter sets the parameter of the shape.
     *
     * @param parameter (int) of the shape.
     */
    final public void setParameter(final int parameter) {
        mParameter = parameter;
    }

    @Override
    @Bindable
    final public int getParameter() {
        return mParameter;
    }


    final public String getName() {
        return mName;
    }

    final public void setShadow(final Shadow shadow) {
        // Update property listeners.
        if (mShadow != null)
            mShadow.removeOnPropertyChangedCallback(this.onShadowPropertyChanged);
        shadow.addOnPropertyChangedCallback(this.onShadowPropertyChanged);
        // Set shadow.
        mShadow = shadow;
        // Notify observers.
        notifyPropertyChanged(BR.shadow);
    }


    @Override
    @Bindable
    public Shadow getShadow() {
        return mShadow;
    }


    public void setType(ShapeType type) {
        if (mType == type) return;
        mType = type;
        notifyPropertyChanged(BR.type);
    }

    @Override
    @Bindable
    final public ShapeType getType() {
        return mType;
    }

    final public void setX(final int x) {
        if (getX() == x) return;
        mPosition[0] = x;
        notifyPropertyChanged(BR.x);
    }

    @Override
    @Bindable
    final public int getX() {
        return mPosition[0];
    }

    final public void setY(final int y) {
        if (getY() == y) return;
        mPosition[1] = y;
        notifyPropertyChanged(BR.y);
    }

    @Override
    @Bindable
    final public int getY() {
        return mPosition[1];
    }

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
     * setScale sets the scale of the shape.
     *
     * @param scale (float) of the shape as float.
     */
    final public void setScale(final float scale) {
        if (mScale == scale) return;
        mScale = scale;
        notifyPropertyChanged(BR.scale);
    }

    @Override
    @Bindable
    final public float getScale() {
        return mScale;
    }


    @Override
    public Paint getPaint(final Context context) {
        // Create new paint.
        final Paint paint = new Paint();
        // Configure paint based on the state of the shape.
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setAntiAlias(true);
        paint.setColor(mColor);
        // Add shadow configurations.
        paint.setShadowLayer(5f, mShadow.getX(), mShadow.getY(), mShadow.getColor());
        // Return configured paint.
        return paint;
    }


    /**
     * drawOnCanvas handles the pipeline of drawing the shape on canvas.
     * <p>
     * Notes: Use onDraw to customize how the shape is being drawn.
     *
     * @param canvas  (Canvas) being drawn on.
     * @param context (Context)
     */
    final public void drawOnCanvas(final Canvas canvas, final Context context) {
        Paint paint = getPaint(context);
        drawOnCanvas(canvas, paint);
    }

    /**
     * drawOnCanvas handles the pipeline of drawing the shape on canvas
     * with custom paint provided.
     *
     * @param canvas (Canvas) being drawn on.
     * @param paint  (Paint) custom paint.
     */
    final public void drawOnCanvas(final Canvas canvas, final Paint paint) {
        onPreDraw(canvas);
        onDraw(canvas, paint);
        onPostDraw(canvas);
    }

    /**
     * onDraw handles the actual drawing of the shape on canvas.
     * <p>
     * Notes: Override this method to create custom-rendered shapes.
     *
     * @param canvas (Canvas) being drawn on.
     * @param paint  (Paint) used to draw the shape.
     */
    protected void onDraw(final Canvas canvas, final Paint paint) {
        canvas.drawRect(getX() - 30, getY() - 30, getX() + 30, getY() + 30, paint);
    }

    /**
     * onPreDraw gets executed just before the shape is drawn on canvas.
     *
     * @param canvas (Canvas) being drawn on.
     *               <p>
     *               Notes: Add any draw setups or canvas transforms here.
     */
    private void onPreDraw(final Canvas canvas) {
        canvas.save();
        // Scale the canvas based on the state of the shape.
        canvas.scale(mScale, mScale, getX(), getY());
    }

    /**
     * onPostDraw gets executed just after the shape has been drawn on canvas.
     *
     * @param canvas (Canvas) being drawn on.
     *               <p>
     *               Notes: Add any draw cleanups here.
     */
    private void onPostDraw(final Canvas canvas) {
        canvas.restore();
    }

    @NonNull
    final public String toString() {
        return mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return mType == shape.mType && mParameter == shape.mParameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mParameter, mType);
    }

    final protected OnPropertyChangedCallback onShadowPropertyChanged = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            notifyChange();
        }
    };
}
