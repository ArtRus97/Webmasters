package com.example.webmasters.adapters;

import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SharedBindingAdapters {

    /**
     * setAdapter allows easier binding of recycler view adapter's using model binding.
     *
     * @param recyclerView (RecyclerView) that uses the adapter.
     * @param adapter      (RecyclerView.Adapter<?>) used with the recycler view.
     */
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
    public static void setSelectedValue(AppCompatSpinner spinner, Object newSelectedValue, final InverseBindingListener changeListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Trigger the view -> model update when user selects an item.
                if (changeListener != null)
                    changeListener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SpinnerAdapter adapter = spinner.getAdapter();
        if (adapter == null) return;
        for (int itemIndex = 0; itemIndex < adapter.getCount(); itemIndex++) {
            if (!adapter.getItem(itemIndex).equals(newSelectedValue)) continue;
            spinner.setSelection(itemIndex, true);
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static Object getSelectedValue(AppCompatSpinner spinner) {
        return spinner.getSelectedItem();
    }

    @BindingAdapter(value = {"android:max"}, requireAll = false)
    public static void setMax(AppCompatSeekBar view, int max) {
        int value = view.getProgress();
        view.setMax(max);
        view.setProgress(value);
    }

    @BindingAdapter(value = {"android:progress"}, requireAll = false)
    public static void setProgress(AppCompatSeekBar view, int progress) {
        view.setProgress(progress);
    }

    @BindingAdapter(value = {"app:onProgress"})
    public static void setOnProgress(AppCompatSeekBar view, java.util.function.IntConsumer onProgress) {
        view.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onProgress.accept(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    @BindingAdapter(value = {"android:formatArgs"})
    public static void setText(TextView view, Object[] formatArgs) {
        view.setText(String.format(view.getText().toString(), formatArgs));
    }

    @BindingAdapter(value = {"android:formatArgs"})
    public static void setText(TextView view, Object formatArg) {
        view.setText(String.format(view.getText().toString(), formatArg));
    }
}
