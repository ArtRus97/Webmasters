package com.example.webmasters.ui.graphic_design.animations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.webmasters.R;

public class AnimationsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnimationsViewModel animationsViewModel = ViewModelProviders.of(this).get(AnimationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_animations, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        animationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}