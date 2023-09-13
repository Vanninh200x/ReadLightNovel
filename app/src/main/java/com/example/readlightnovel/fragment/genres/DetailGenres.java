package com.example.readlightnovel.fragment.genres;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.DialogGenresDetailBinding;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.model.uim.ItemGenres;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailGenres extends DialogFragment {
    private Dialog dialog;
    private Activity activity;
    private DialogGenresDetailBinding binding;
    private ItemGenres item;
    private LNAdapter lightNovelAdapter;
    private String title;
    private boolean isItem;
    private List<Data> mListData;
//


    public DetailGenres(String title, LNAdapter lightNovelAdapter) {
        this.title = title;
        this.lightNovelAdapter = lightNovelAdapter;
        isItem = false;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        dialog = DialogUtils.getNewDialog(activity);
        binding = DialogGenresDetailBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        init();
        initView();
        initListener();
        return dialog;
    }

    private void init() {
        mListData = new ArrayList<>();
    }

    private List<Data> addListLightNovel(String category) {
        mListData.clear();
        new Thread(() -> getActivity().runOnUiThread(() -> {
            RequestAPI.getComicByCategory(lightNovelAdapter, mListData, category);
        })).start();
        return mListData;
    }

    private void initListener() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

    }

    private void initView() {
        binding.ivBack.setColorFilter(Color.BLACK);
        binding.tvTitle.setText(isItem ? item.getTitle() : title);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);
        lightNovelAdapter.setData(addListLightNovel(title));
        binding.rcv.setAdapter(lightNovelAdapter);

        lightNovelAdapter.setOnClickListener(new OnLightNovelClickListener() {
            @Override
            public void onClick(int position, Data item) {
                if (DialogUtils.enableShowDialogFragment(getActivity().getSupportFragmentManager(), DialogFragmentDetail.class.getSimpleName())) {
                    new DialogFragmentDetail(item).show(getActivity().getSupportFragmentManager(), DialogFragmentDetail.class.getSimpleName());
                }
            }
        });
    }


}
