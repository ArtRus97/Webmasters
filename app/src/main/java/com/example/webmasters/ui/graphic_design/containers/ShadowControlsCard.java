package com.example.webmasters.ui.graphic_design.containers;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.adapters.ShadowAdapter;
import com.example.webmasters.databinding.CardShadowControlsBinding;
import com.example.webmasters.models.graphic_design.Shadow;
import com.example.webmasters.ui.shared.ExtendedCardView;

import java.util.List;

public class ShadowControlsCard extends ExtendedCardView {
    private final String TITLE = "Shadows";
    private final CardShadowControlsBinding mBinding;

    public ShadowControlsCard(Context context) {
        this(context, null);
    }

    public ShadowControlsCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowControlsCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Inflate and attach child XML.
        mBinding = CardShadowControlsBinding.inflate(LayoutInflater.from(context), this);
        recycler().setHasFixedSize(true);
        recycler().setLayoutManager(new LinearLayoutManager(recycler().getContext()));

        // Set default attributes.
        setTitle(TITLE);
    }


    private RecyclerView recycler() {
        return mBinding.recyclerShadows;
    }

    public void setShadows(List<Shadow> shadows) {
        if (shadows == null) return;
        recycler().setAdapter(new ShadowAdapter(shadows));
    }
}
