package com.example.webmasters.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.databinding.ListItemLogoBinding;
import com.example.webmasters.models.graphic_design.Logo;

import java.util.List;
import java.util.function.Consumer;

public class LogoAdapter extends RecyclerView.Adapter<LogoAdapter.LogoViewHolder> {
    private final List<Logo> mLogos;
    private final List<String> mNames;

    private Consumer<Logo> mImportCallback = null;

    public LogoAdapter(List<Logo> logos, List<String> names) {
        mLogos = logos;
        mNames = names;
    }

    public void setImportCallback(Consumer<Logo> callback) {
        mImportCallback = callback;
    }

    @NonNull
    @Override
    public LogoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemLogoBinding binding = ListItemLogoBinding.inflate(layoutInflater, parent, false);
        return new LogoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LogoViewHolder holder, int position) {
        Logo logo = mLogos.get(position);
        String name = mNames.get(position);
        holder.setLogo(logo);
        holder.setName(name);
    }

    @Override
    public int getItemCount() {
        return mLogos.size();
    }


    class LogoViewHolder extends RecyclerView.ViewHolder {
        private final ListItemLogoBinding mBinding;
        private Logo mLogo;

        LogoViewHolder(ListItemLogoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.buttonImport.setOnClickListener(this::onImport);
        }

        void setLogo(Logo logo) {
            mLogo = logo;
            mBinding.setLogo(logo);
            mBinding.executePendingBindings();
        }

        void setName(String name) {
            mBinding.setName(name);
            mBinding.executePendingBindings();
        }

        private void onImport(View view) {
            if (mImportCallback != null) mImportCallback.accept(mLogo);
        }
    }
}

