package com.example.webmasters.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.databinding.ListItemAnimationBinding;
import com.example.webmasters.models.graphic_design.Animation;

import java.util.List;

public class AnimationAdapter extends RecyclerView.Adapter<AnimationViewHolder> {
    private final List<Animation> mAnimations;

    public AnimationAdapter(List<Animation> animations) {
        mAnimations = animations;
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
        holder.setAnimation(mAnimations.get(position));
    }

    @Override
    public int getItemCount() {
        return mAnimations.size();
    }
}

class AnimationViewHolder extends RecyclerView.ViewHolder {
    private final ListItemAnimationBinding mBinding;

    AnimationViewHolder(ListItemAnimationBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    void setAnimation(Animation animation) {
        mBinding.setAnimation(animation);
        mBinding.executePendingBindings();
    }
}