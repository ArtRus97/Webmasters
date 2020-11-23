package com.example.webmasters.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.databinding.ListItemShadowBinding;
import com.example.webmasters.models.graphic_design.Shadow;

import java.util.List;

public class ShadowAdapter extends RecyclerView.Adapter<ShadowViewHolder> {
    private final List<Shadow> mShadows;

    public ShadowAdapter(List<Shadow> shadows) {
        mShadows = shadows;
    }

    @NonNull
    @Override
    public ShadowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemShadowBinding binding = ListItemShadowBinding.inflate(layoutInflater, parent, false);
        return new ShadowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShadowViewHolder holder, int position) {
        holder.setShadow(mShadows.get(position));
    }

    @Override
    public int getItemCount() {
        return mShadows.size();
    }
}

class ShadowViewHolder extends RecyclerView.ViewHolder {
    private final ListItemShadowBinding mBinding;

    public ShadowViewHolder(ListItemShadowBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    void setShadow(Shadow shadow) {
        mBinding.setShadow(shadow);
        mBinding.executePendingBindings();
    }
}