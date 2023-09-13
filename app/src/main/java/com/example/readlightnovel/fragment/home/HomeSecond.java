package com.example.readlightnovel.fragment.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.databinding.FragmentHomeSecondBinding;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.List;

public class HomeSecond extends Fragment {
    private FragmentHomeSecondBinding binding;
    private LNAdapter lightNovelAdapter;
    private List<Data> mListLightNovel;

    public HomeSecond() {

    }

    public HomeSecond(List<Data> mListLightNovel, LNAdapter lightNovelAdapter) {
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeSecondBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init();
        showRecyclerView();
        return view;
    }

    private void init() {

    }

    private void showRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);
        binding.rcv.setAdapter(lightNovelAdapter);

        if (mListLightNovel != null) {
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

}