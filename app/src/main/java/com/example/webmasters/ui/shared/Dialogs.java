package com.example.webmasters.ui.shared;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class Dialogs {

    public static void input(Context context, String title, BiConsumer<DialogInterface, String> successCallback, Consumer<Void> cancelCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

        final EditText input = new EditText(context);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> successCallback.accept(dialog, input.getText().toString()));
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            cancelCallback.accept(null);
            dialog.cancel();
        });

        builder.show();
    }

    public static class RecyclerDialog extends DialogFragment {
        private RecyclerView mRecyclerView;





        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            mRecyclerView = new RecyclerView(requireContext());

            // you can use LayoutInflater.from(getContext()).inflate(...) if you have xml layout
            mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            //mRecyclerView.setAdapter(/* your adapter */);

            return new AlertDialog.Builder(requireActivity())
                    .setTitle("ASD")
                    .setView(mRecyclerView)
                    .setPositiveButton(android.R.string.ok,
                            (dialog, whichButton) -> {

                            }
                    ).create();
        }
    }
}
