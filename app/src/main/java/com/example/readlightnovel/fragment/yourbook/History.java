package com.example.readlightnovel.fragment.yourbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.detail.ChapterAdapter;
import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.databinding.FragmentChapterBinding;
import com.example.readlightnovel.databinding.FragmentHistoryBinding;
import com.example.readlightnovel.fragment.detail.DetailRead;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnChapterClickListener;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.model.uim.LightNovelParent;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.List;


public class History extends Fragment {
    private FragmentHistoryBinding binding;

    private List<Data> mListLightNovel;
    private LNAdapter lightNovelAdapter;
    

    public History(){

    }
    public History(List<Data> mListLightNovel, LNAdapter lightNovelAdapter) {
        // Required empty public constructor
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();
        showRecyclerView();

        return view;

    }

    private void showRecyclerView() {
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);


        if (mListLightNovel != null) {
            lightNovelAdapter.setData(mListLightNovel);
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
}