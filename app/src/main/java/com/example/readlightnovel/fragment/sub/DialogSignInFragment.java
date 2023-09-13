package com.example.readlightnovel.fragment.sub;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.readlightnovel.R;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.DialogFragmentSignInBinding;
import com.example.readlightnovel.dialog.DialogLoading;
import com.example.readlightnovel.callback.CallBackUser;
import com.example.readlightnovel.model.signin.User;
import com.example.readlightnovel.utils.desa.DialogUtils;

public class DialogSignInFragment extends DialogFragment {
    private Dialog dialog;
    private Activity activity;
    private DialogFragmentSignInBinding binding;
    private DialogLoading dialogLoading;
    private CallBackUser myCallBack;

    public DialogSignInFragment(CallBackUser myCallBack) {
        this.myCallBack = myCallBack;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        dialog = DialogUtils.getNewDialog(activity);
        binding = DialogFragmentSignInBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());


        initThemes();
        initListener();
        return dialog;
    }

    private void initThemes() {
        binding.tvContent.setTextColor(Color.WHITE);
        binding.tvTitle.setTextColor(Color.WHITE);
        dialogLoading = new DialogLoading();
    }

    private void initListener() {
        binding.ivBack.setOnClickListener(v -> dialog.dismiss());

        binding.tvSignIn.setOnClickListener(v -> onClickSignIn());

        binding.tvSignUp.setOnClickListener(v -> {
            if (DialogUtils.enableShowDialogFragment(getActivity().getSupportFragmentManager(), DialogSignUpFragment.class.getSimpleName())) {
                new DialogSignUpFragment().show(getActivity().getSupportFragmentManager(), DialogSignUpFragment.class.getSimpleName());
            }
        });

        binding.tvResetPasswd.setOnClickListener(v -> {
            dialogLoading.show(getActivity().getSupportFragmentManager(), DialogSignUpFragment.class.getSimpleName());
            RequestAPI.resetPassword(binding.account.getText().toString(), activity, dialogLoading);

        });


    }

    private void onClickSignIn() {
        String account = binding.account.getText().toString().trim();
        String password = binding.password.getText().toString().trim();
        User user = new User(account, password);
        binding.tvNotify.setVisibility(View.VISIBLE);

        if (user.isValidEmail() && user.isValidPassword()) {
            RequestAPI.postRequestLogin(binding.account.getText().toString(), binding.password.getText().toString(), binding.tvNotify, dialog, myCallBack);
        } else {
            binding.tvNotify.setText(R.string.incorrect_account);
            binding.tvNotify.setTextColor(Color.RED);
        }
    }
}
