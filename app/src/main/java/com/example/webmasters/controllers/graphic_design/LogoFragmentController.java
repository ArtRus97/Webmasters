package com.example.webmasters.controllers.graphic_design;

import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.webmasters.R;
import com.example.webmasters.models.graphic_design.view_models.LogoViewModel;
import com.example.webmasters.types.TextChangedListener;
import com.example.webmasters.ui.graphic_design.logos.LogoView;

public class LogoFragmentController {
    private Fragment mFragment;

    public LogoFragmentController(Fragment fragment, View view) {
        mFragment = fragment;
        LogoViewModel model = new ViewModelProvider(mFragment).get(LogoViewModel.class);
        bindLogoText(view.findViewById(R.id.editLogoText), model);
        bindLogoView(view.findViewById(R.id.logoView), model);
    }

    /**
     * bindLogoView binds logo view to view model.
     * @param logoView (LogoView)
     * @param logosViewModel (LogosViewModel)
     */
    private void bindLogoView(LogoView logoView, LogoViewModel logosViewModel) {
        // Model -> View updates.
        logosViewModel.getTextObservable().observe(mFragment, logoView::setText);
        logosViewModel.getTextSizeObservable().observe(mFragment, logoView::setTextSize);
    }

    /**
     * bindLogoText binds logo text edit view to view model and enables bidirectional updates.
     * @param editLogoText (EditText)
     * @param logosViewModel (LogosViewModel)
     */
    private void bindLogoText(EditText editLogoText, LogoViewModel logosViewModel) {
        // Model -> View update.
        logosViewModel.getTextObservable().observe(mFragment, logoText -> {
            int position = editLogoText.getSelectionEnd();
            editLogoText.setText(logoText);
            editLogoText.setSelection(position);
        });
        // View -> Model update.
        editLogoText.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(String logoText) {
                logosViewModel.setText(logoText);
            }
        });
    }
}
