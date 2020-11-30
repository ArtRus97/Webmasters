package com.example.webmasters.ui.graphic_design;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.webmasters.R;
import com.example.webmasters.databinding.FragmentThemesBinding;

public class ThemesFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentThemesBinding binding = FragmentThemesBinding.inflate(getLayoutInflater());

        GraphicDesignViewModel model = new ViewModelProvider(requireActivity()).get(GraphicDesignViewModel.class);
        binding.getRoot().post(() -> {
            binding.setModel(model);
        });

        return binding.getRoot();
    }
}