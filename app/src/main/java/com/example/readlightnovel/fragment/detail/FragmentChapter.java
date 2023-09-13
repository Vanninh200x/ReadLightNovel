package com.example.readlightnovel.fragment.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readlightnovel.adapter.detail.ChapterAdapter;
import com.example.readlightnovel.databinding.FragmentChapterBinding;
import com.example.readlightnovel.interfaces.OnChapterClickListener;
import com.example.readlightnovel.model.chapter.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.List;

public class FragmentChapter extends Fragment {
    private FragmentChapterBinding binding;
    private ChapterAdapter chapterAdapter;
    private List<Data> mList;

    public FragmentChapter(List<Data> mList) {
        this.mList = mList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChapterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();
        showRecyclerView();

        return view;
    }


    private void showRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);
        chapterAdapter = new ChapterAdapter(mList);
        binding.rcv.setAdapter(chapterAdapter);

        chapterAdapter.setOnClickListener(new OnChapterClickListener() {
            @Override
            public void onClick(int position, Data item) {
                if (DialogUtils.enableShowDialogFragment(getActivity().getSupportFragmentManager(), DetailRead.class.getSimpleName())) {
                    new DetailRead(item, mList).show(getActivity().getSupportFragmentManager(), DetailRead.class.getSimpleName());
                }
            }
        });
    }

    private void initView() {

    }

}