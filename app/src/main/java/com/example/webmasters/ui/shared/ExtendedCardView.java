package com.example.webmasters.ui.shared;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.webmasters.R;
import com.example.webmasters.databinding.ViewExtendedCardBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

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

    private ViewExtendedCardBinding mBinding;

    public ExtendedCardView(Context context) {
        this(context, null);
    }

    public boolean isContentVisible() {
        return getContent().getVisibility() == VISIBLE;
    }

    public ExtendedCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendedCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Inflate and attach child XML.
        mBinding = ViewExtendedCardBinding.inflate(LayoutInflater.from(context), this);

        // Set callback listeners.
        getHeader().setOnClickListener(header -> this.setContentVisible(!isContentVisible()));

        // Set custom attributes.
        TypedArray customAttributes = context.obtainStyledAttributes(attrs, R.styleable.ExtendedCardView);
        String titleText = customAttributes.getString(R.styleable.ExtendedCardView_title);
        if (titleText == null) {
            titleText = DEFAULT_TITLE;
        }

        setTitle(titleText);
        customAttributes.recycle();
    }

    public ViewGroup getHeader() {
        return mBinding.layoutHeader;
    }

    public TextView getTitle() {
        return mBinding.textHeaderTitle;
    }

    public TextView getIcon() {
        return mBinding.textHeaderIcon;
    }

    public ViewGroup getContent() {
        return mBinding.layoutContent;
    }

    public void setTitle(String title) {
        getTitle().setText(title);
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
        if (mBinding == null)
            super.addView(child, index, params);
        else
            getContent().addView(child, index, params);
    }


    public void setContentVisible(boolean isVisible) {
        if (!isVisible) {
            getContent().animate()
                    .translationY(0)
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            getContent().setVisibility(GONE);
                        }
                    });
        } else {
            getContent().setVisibility(VISIBLE);
            getContent().setAlpha(0.0f);
            getContent().animate()
                    .alpha(1.0f)
                    .setListener(null);

        }
        getIcon().setCompoundDrawablesWithIntrinsicBounds(0, 0, isVisible ? R.drawable.ic_baseline_expand_less_24 : R.drawable.ic_baseline_expand_more_24, 0);
    }
}
