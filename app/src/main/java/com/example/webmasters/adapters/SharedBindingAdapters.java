package com.example.webmasters.adapters;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.*;

public class SharedBindingAdapters {

    @BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner, Object newSelectedValue, final InverseBindingListener newTextAttrChanged) {
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        SpinnerAdapter adapter = pAppCompatSpinner.getAdapter();
        if (adapter == null) return;
        for (int itemIndex = 0; itemIndex < adapter.getCount(); itemIndex++) {
            if (!adapter.getItem(itemIndex).equals(newSelectedValue)) continue;
            pAppCompatSpinner.setSelection(itemIndex, true);
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static Object captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return pAppCompatSpinner.getSelectedItem();
    }
}
