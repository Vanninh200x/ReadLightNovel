package com.example.readlightnovel.dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.readlightnovel.R;
import com.example.readlightnovel.databinding.CustomProgressBinding;
import com.example.readlightnovel.databinding.DialogFragmentSignInBinding;
import com.example.readlightnovel.utils.desa.DialogUtils;


public class DialogLoading extends DialogFragment {
    private Activity activity;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_progress, null));
        builder.setCancelable(true);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
