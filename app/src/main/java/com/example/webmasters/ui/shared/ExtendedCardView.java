package com.example.webmasters.ui.shared;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import com.example.webmasters.R;
import com.google.android.material.card.MaterialCardView;

/**
 * ExtendedCardView extends MaterialCardView with some additional features.
 *
 * @author JIkaheimo
 * <p>
 * Features:
 * - Showing/hiding the child views by clicking the card title.
 * <p>
 * Custom attributes:
 * - app:titleText sets the the card's title text.
 */
public class ExtendedCardView extends MaterialCardView {
    // Constants.
    final String DEFAULT_TITLE = "Card Title";

    // Layouts.
    private ViewGroup mLayout;
    private ViewGroup mHeader;
    private ViewGroup mContent;

    // Components.
    private TextView mTextTitle;
    private TextView mTextIcon;


    public ExtendedCardView(Context context) {
        this(context, null);
    }

    public boolean isContentVisible() {
        return mContent.getVisibility() == VISIBLE;
    }

    public ExtendedCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendedCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Inflate and attach child XML.
        View root = LayoutInflater.from(context).inflate(R.layout.view_extended_card, this);

        // Get child view references.
        mLayout = root.findViewById(R.id.layout_main);
        mHeader = mLayout.findViewById(R.id.layout_header);
        mContent = mLayout.findViewById(R.id.layout_content);
        mTextTitle = mHeader.findViewById(R.id.text_header_title);
        mTextIcon = mHeader.findViewById(R.id.text_header_icon);

        // Set callback listeners.
        mHeader.setOnClickListener(header -> this.toggleContent(!isContentVisible()));

        // Set custom attributes.
        TypedArray customAttributes = context.obtainStyledAttributes(attrs, R.styleable.ExtendedCardView);
        String titleText = customAttributes.getString(R.styleable.ExtendedCardView_titleText);
        if (titleText == null) {
            titleText = DEFAULT_TITLE;
        }

        mTextTitle.setText(titleText);
        customAttributes.recycle();
    }

    /**
     * addView has to be overridden to allow XML file to pass the defined child views
     * to the custom view's layout.
     *
     * @param child  (View)
     * @param index  (int)
     * @param params (ViewGroup.LayoutParams)
     */
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (mContent == null)
            super.addView(child, index, params);
        else
            mContent.addView(child, index, params);
    }


    public void toggleContent(boolean isVisible) {
        if (!isVisible) {
            mContent.animate()
                    .translationY(0)
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mContent.setVisibility(GONE);
                        }
                    });
        } else {
            mContent.setVisibility(VISIBLE);
            mContent.setAlpha(0.0f);
            mContent.animate()
                    .alpha(1.0f)
                    .setListener(null);

        }
        mTextIcon.setCompoundDrawablesWithIntrinsicBounds(0, 0, !isVisible ? R.drawable.ic_baseline_expand_less_24 : R.drawable.ic_baseline_expand_more_24, 0);
    }
}
