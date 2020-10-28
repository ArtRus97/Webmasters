package com.example.webmasters.ui.graphic_design.logos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.webmasters.R;
import com.example.webmasters.controllers.graphic_design.LogoFragmentController;
import com.example.webmasters.types.TextChangedListener;

public class LogosFragment extends Fragment {
    private LogoFragmentController mController = null;


    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_logos, container, false);
        mController = new LogoFragmentController(this, root);
        /*
        mLogoView = root.findViewById(R.id.logoView);

        mEditLogoText = root.findViewById(R.id.editLogoText);
        mLogosViewModel.getLogoText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String logoText) {
                mEditLogoText.setText(logoText);
            }
        });
        mEditLogoText.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(String logoText) {
                mLogosViewModel.setLogoText(logoText);
            }
        });

        /*
        //final TextView textView = root.findViewById(R.id.text_home);
        mLogosViewModel.getLogoText.get().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String logoText) {
                mLogoView.setText(logoText);
            }
        });

    */

        return root;
    }
}