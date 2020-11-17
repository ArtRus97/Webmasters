package com.example.webmasters.ui.graphic_design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.webmasters.R;
import com.example.webmasters.adapters.AnimationAdapter;
import com.example.webmasters.databinding.FragmentAnimationsBinding;

public class AnimationsFragment extends Fragment {

    private FragmentAnimationsBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = FragmentAnimationsBinding.inflate(getLayoutInflater());
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GraphicDesignViewModel model = new ViewModelProvider(requireActivity()).get(GraphicDesignViewModel.class);
        mBinding.setModel(model);
        mBinding.cardAnimationControls.setAdapter(new AnimationAdapter(model.getAnimations()));
    }
}