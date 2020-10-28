package com.example.webmasters.controllers.graphic_design;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.webmasters.R;
import com.example.webmasters.types.TextChangedListener;
import com.example.webmasters.ui.graphic_design.logos.LogosFragment;
import com.example.webmasters.ui.graphic_design.logos.LogosViewModel;

public class LogoFragmentController {
    private Fragment mFragment;

    public LogoFragmentController(Fragment fragment, View view) {
        mFragment = fragment;
        LogosViewModel model = new ViewModelProvider(mFragment).get(LogosViewModel.class);
        setupLogoTextUpdates(view.findViewById(R.id.editLogoText), model);
    }

    private void setupLogoTextUpdates(EditText editLogoText, LogosViewModel logosViewModel) {
        // Model -> View update.
        logosViewModel.getLogoText().observe(mFragment, logoText -> {
            // Check for redundant update.
            int position = editLogoText.getSelectionEnd();
            editLogoText.setText(logoText);
            editLogoText.setSelection(position);
        });
        // View -> Model update.
        editLogoText.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(String logoText) {
                logosViewModel.setLogoText(logoText);
            }
        });
    }
}
