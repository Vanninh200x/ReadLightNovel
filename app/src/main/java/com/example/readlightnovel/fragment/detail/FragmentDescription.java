package com.example.readlightnovel.fragment.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.readlightnovel.R;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.callback.CallBackRate;
import com.example.readlightnovel.databinding.FragmentDescriptionBinding;
import com.example.readlightnovel.dialog.DialogLoading;
import com.example.readlightnovel.fragment.sub.DialogSignInFragment;
import com.example.readlightnovel.fragment.sub.DialogSignUpFragment;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.model.rate.PickRate;
import com.example.readlightnovel.utils.desa.DialogUtils;
import com.orhanobut.hawk.Hawk;

public class FragmentDescription extends Fragment {
    private FragmentDescriptionBinding binding;
    private Data data;
    private DialogLoading dialogLoading;
    private long x;
    private CallBackRate callBackRate;


    public FragmentDescription(Data data, CallBackRate callBackRate) {
        this.data = data;
        this.callBackRate = callBackRate;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        binding.rattingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    if (Hawk.get("user_name") != null) {
                        dialogLoading.show(getActivity().getSupportFragmentManager(), DialogSignUpFragment.class.getSimpleName());
                        RequestAPI.postRequestRating(Hawk.get("access_token"), data.getId(), (long) rating, dialogLoading);
                        callBackRate.onCallBack(new PickRate(data.getId(), (long) rating));
                        Toast.makeText(getActivity(), R.string.is_rating, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), R.string.require_login, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        dialogLoading = new DialogLoading();
        binding.tvDescription.setText(data.getDescription());

        if (Hawk.get("user_rate") != null && Hawk.get("user_id") != null) {
            x = Hawk.get("user_rate");
            binding.rattingBar.setRating((float) x);
        }
    }

}