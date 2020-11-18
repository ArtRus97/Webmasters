package com.example.webmasters.ui.graphic_design;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.webmasters.Utils;
import com.example.webmasters.databinding.FragmentLogosBinding;
import com.example.webmasters.models.graphic_design.Logo;

public class LogoFragment extends Fragment {
    private FragmentLogosBinding mBinding;
    private GraphicDesignViewModel mModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get view model instance.
        mModel = new ViewModelProvider(requireActivity()).get(GraphicDesignViewModel.class);

        // Enable view binding.
        mBinding = FragmentLogosBinding.inflate(getLayoutInflater());
        mBinding.setLifecycleOwner(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStatenceState) {
        // Setup loading overlay.
        LiveData<Logo> mLogo = mModel.getLogo();
        FrameLayout layoutProgress = mBinding.overlayProgress.layoutProgress;
        mLogo.observe(getViewLifecycleOwner(), logo -> {
            Utils.animateView(layoutProgress, View.GONE, 0, 200);
        });
        Utils.animateView(layoutProgress, View.VISIBLE, 0.4f, 200);

        if (mBinding.getCanvasView() == null)
            mBinding.getRoot().post(() -> {
                mBinding.setCanvasView(mBinding.logoView);
                mBinding.executePendingBindings();
                mBinding.setModel(mModel);
            });

        super.onCreateView(inflater, container, savedInstanceStatenceState);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Setup swipe listener to toggle the visibility of logo controls.
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