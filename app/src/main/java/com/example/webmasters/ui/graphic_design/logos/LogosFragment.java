package com.example.webmasters.ui.graphic_design.logos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.webmasters.R;
import com.example.webmasters.controllers.graphic_design.LogoFragmentController;
import com.google.android.material.tabs.TabLayout;

public class LogosFragment extends Fragment {
    private LogoFragmentController mController = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_logos, container, false);
        mController = new LogoFragmentController(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}