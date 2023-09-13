package com.example.readlightnovel.fragment.main;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.databinding.FragmentYourBookBinding;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.List;

public class YourBookFragment extends Fragment {

    private FragmentYourBookBinding binding;

    private List<Data> mListLightNovel;
    private LNAdapter lightNovelAdapter;

    public YourBookFragment(List<Data> mListLightNovel, LNAdapter lightNovelAdapter) {
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;
        System.out.println("YourBook");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentYourBookBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init();
        showRecyclerView();
        initListener();
        return view;
    }

    private void initListener() {
        binding.header.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("SIZE-YOUR-BOOK: " + mListLightNovel.size() + "");
            }
        });
    }

    private void showRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);

        lightNovelAdapter.setData(mListLightNovel);
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

    private void init() {
        binding.header.tvTitle.setText(getString(R.string.your_book));
        binding.header.tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
    }


}