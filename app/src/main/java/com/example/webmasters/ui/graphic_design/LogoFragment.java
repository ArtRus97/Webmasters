package com.example.webmasters.ui.graphic_design;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.R;
import com.example.webmasters.Utils;
import com.example.webmasters.adapters.LogoAdapter;
import com.example.webmasters.databinding.FragmentLogosBinding;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.services.FirebaseService;
import com.example.webmasters.ui.shared.Dialogs;

public class LogoFragment extends Fragment {
    private FragmentLogosBinding mBinding;
    private GraphicDesignViewModel mModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        requireActivity().getMenuInflater().inflate(R.menu.graphic_design_menu, menu);
        menu.getItem(0).setOnMenuItemClickListener(this::onBrowse);
        menu.getItem(1).setOnMenuItemClickListener(this::onShare);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onBrowse(MenuItem item) {
        // Create default dialog.
        final Dialog dialog = new Dialog(requireActivity());
        // Setup custom layout for the dialog.
        dialog.setContentView(R.layout.dialog_logos);
        final RecyclerView recyclerLogos = dialog.findViewById(R.id.recyclerLogos);

        (new FirebaseService()).getSharedLogos((logos, names) -> {
            LogoAdapter logoAdapter = new LogoAdapter(logos, names);
            logoAdapter.setImportCallback(logo -> {
                mModel.setLogo(logo);
            });

            recyclerLogos.setAdapter(logoAdapter);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext());
            recyclerLogos.setLayoutManager(mLayoutManager);
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        return true;
    }

    public boolean onShare(MenuItem item) {
        // Display input dialog to prompt logo name from the user.
        Dialogs.input(requireContext(), "Logo name", (dialog, name) -> {
            // Share logo (add it to shared logos in the database).
            mModel.shareLogo(name);
            dialog.dismiss();
        }, unused -> {
            // Placeholder for any cancel events.
        });

        return true;
    }

}