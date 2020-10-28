package com.example.webmasters.types;

import android.text.Editable;
import android.text.TextWatcher;


public abstract class TextChangedListener implements TextWatcher {
    private boolean mIgnoreChange = false;

    @Override
    final public void beforeTextChanged(CharSequence text, int start, int count, int after) {};

    @Override
    final public void onTextChanged(CharSequence text, int start, int before, int count) {};

    @Override
    final public void afterTextChanged(Editable editable) {
        if (mIgnoreChange) return;
        mIgnoreChange = true;
        onTextChanged(editable.toString());
        mIgnoreChange = false;
    };

    public abstract void onTextChanged(String text);
}
