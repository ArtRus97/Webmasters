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

public class ExtendedCardView extends MaterialCardView {
    private ViewGroup mLayout;
    private ViewGroup mHeader;
    private ViewGroup mContent;

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
        TextView textTitle = mHeader.findViewById(R.id.text_header);

        // Set callback listeners.
        mHeader.setOnClickListener(header -> this.toggleContent());

        // Set custom attributes.
        TypedArray customAttributes = context.obtainStyledAttributes(attrs, R.styleable.ExtendedCardView, defStyle, 0);
        String titleText = customAttributes.getString(R.styleable.ExtendedCardView_titleText);
        textTitle.setText(titleText);
        customAttributes.recycle();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (mContent == null)
            super.addView(child, index, params);
        else
            mContent.addView(child, index, params);
    }


    public void toggleContent() {

        if (isContentVisible()) {
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

    }
}
