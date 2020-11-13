package com.example.webmasters.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.databinding.ListItemAnimationBinding;
import com.example.webmasters.models.graphic_design.Animation;

import java.util.List;

public class AnimationAdapter extends RecyclerView.Adapter<AnimationViewHolder> {
    private final List<Animation> mAnimations;
    private final ObservableList<Animation> mSelectedAnimations;
    private final ObservableField<Animation> mFirstAnimation;


    public AnimationAdapter(List<Animation> animations) {
        mAnimations = animations;
        mFirstAnimation = new ObservableField<>(mAnimations.get(0));
        mSelectedAnimations = new ObservableArrayList<>();
    }

    @NonNull
    @Override
    public AnimationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemAnimationBinding binding = ListItemAnimationBinding.inflate(layoutInflater, parent, false);
        return new AnimationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimationViewHolder holder, int position) {
        Animation animation = mAnimations.get(position);
        holder.setAnimation(animation);
        holder.onClick(viewHolder -> {
            // Remove selected animation.
            if (mSelectedAnimations.contains(animation)) {
                mSelectedAnimations.remove(animation);
                holder.setState(false);
            // Add animation as selected.
            } else {
                mSelectedAnimations.add(animation);
                holder.setState(true);
            }

            if (mSelectedAnimations.size() > 0) {
                Log.d("ASD", "Animation set!");
                mFirstAnimation.set(mSelectedAnimations.get(0));
            }
        });
    }

    private Animation firstAnimation() {
        return getFirstAnimation().get();
    }

    public ObservableList<Animation> getSelectedAnimations() {
        return mSelectedAnimations;
    }

    public ObservableField<Animation> getFirstAnimation() {
        return mFirstAnimation;
    }


    @Override
    public int getItemCount() {
        return mAnimations.size();
    }
}

class AnimationViewHolder extends RecyclerView.ViewHolder {
    private final ListItemAnimationBinding mBinding;
    private Animation mAnimation;
    private boolean mIsSelected = false;

    AnimationViewHolder(ListItemAnimationBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mBinding.layoutAnimationItem.setOnClickListener(this::onClick);
    }

    void setState(boolean isSelected) {
        mBinding.setIsSelected(isSelected);
        mBinding.executePendingBindings();
    }

    public void onClick(View.OnClickListener clickHandler) {
        mBinding.getRoot().setOnClickListener(clickHandler);
    }

    void setAnimation(Animation animation) {
        mAnimation = animation;
        mBinding.setAnimation(animation);
        mBinding.executePendingBindings();
    }

    private void onClick(View view) {

        /*
        if (mAnimation.isPlaying())
            mAnimation.pause();
        else
            mAnimation.play();

         */
    }
}