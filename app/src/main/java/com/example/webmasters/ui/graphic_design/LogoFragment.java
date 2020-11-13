package com.example.webmasters.ui.graphic_design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.webmasters.databinding.FragmentLogosBinding;
import com.example.webmasters.models.graphic_design.Logo;

public class LogoFragment extends Fragment {
    private FragmentLogosBinding mBinding;
    private GraphicDesignViewModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mModel = new ViewModelProvider(requireActivity()).get(GraphicDesignViewModel.class);
        mBinding = FragmentLogosBinding.inflate(getLayoutInflater());

        boolean isInitialized = mModel.getInitialized();

        mBinding.getRoot().post(() -> {
            mBinding.setCanvasView(mBinding.logoView);
            mBinding.executePendingBindings();

            // Update controls based on the size of the logo boundaries.
            Logo logo = mModel.getLogo();

            int xBoundary = mBinding.logoView.getWidth();
            int yBoundary = mBinding.logoView.getHeight();

            if (!isInitialized) {
                logo.setTextX(xBoundary / 2);
                logo.setTextY(yBoundary / 2);
                logo.setShapeX(xBoundary / 2);
                logo.setShapeY(yBoundary / 2);
            }

            mBinding.setModel(mModel);
            mBinding.executePendingBindings();

        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mBinding.logoView.setSwipeListener(new LogoView.SwipeListener() {
            @Override
            public void onSwipeDown() {
                mBinding.scrollControls.setVisibility(View.GONE);
            }

            @Override
            public void onSwipeUp() {
                mBinding.scrollControls.setVisibility(View.VISIBLE);
            }
        });

    }
}