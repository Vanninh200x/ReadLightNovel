package com.example.readlightnovel.fragment.sub;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.readlightnovel.R;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.DialogFragmentSignUpBinding;
import com.example.readlightnovel.dialog.DialogLoading;
import com.example.readlightnovel.utils.desa.DialogUtils;

public class DialogSignUpFragment extends DialogFragment {
    private Dialog dialog;
    private Activity activity;
    private DialogFragmentSignUpBinding binding;
    private DialogLoading dialogLoading;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        dialog = DialogUtils.getNewDialog(activity);
        binding = DialogFragmentSignUpBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());

        dialogLoading = new DialogLoading();

        initListener();
        return dialog;
    }

    private void initListener() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(binding.account.getText().toString()) && isValidPassword(binding.password.getText().toString()) && binding.nickName.getText().toString().length() > 3) {
                    dialogLoading.setCancelable(false);
                    dialogLoading.show(getActivity().getSupportFragmentManager(), DialogSignUpFragment.class.getSimpleName());
                    RequestAPI.postRequestSignUp(binding.account.getText().toString(), binding.nickName.getText().toString(), binding.password.getText().toString(), dialogLoading, dialog);
                } else {
                    Toast.makeText(activity, R.string.check_your_info, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() > 6;
    }
}
