package com.example.webmasters.ui.shared;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.webmasters.R;
import com.google.android.material.card.MaterialCardView;

public class ExtendedCardView extends MaterialCardView {
    private ConstraintLayout mLayout;

    public ExtendedCardView(Context context) {
        this(context, null);
    }

    public ExtendedCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendedCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // Inflate and attach child XML.
        LayoutInflater.from(context).inflate(R.layout.view_extended_card, this);
        // Get child view references.
        mLayout = findViewById(R.id.layout_content);
        Log.d("LAYOUT", mLayout.toString());
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (mLayout == null)
            super.addView(child, index, params);
        else
            mLayout.addView(child, index, params);
    }
}
