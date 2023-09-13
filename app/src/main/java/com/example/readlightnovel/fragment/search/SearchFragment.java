package com.example.readlightnovel.fragment.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.FragmentSearchBinding;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private LNAdapter lnAdapter = new LNAdapter();
    private List<Data> mListData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init();
        showRecyclerView();
        return view;
    }

    private void showRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);
        binding.rcv.setAdapter(lnAdapter);

        if (mListData != null) {
            lnAdapter.setOnClickListener(new OnLightNovelClickListener() {
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
        binding.edtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Handle Enter key press
                    String title = binding.edtSearch.getText().toString();
                    lnAdapter.setData(addListLightNovel(title));

                   return true;
                }
                return false;
            }
        });
    }

    private List<Data> addListLightNovel(String title) {
        mListData.clear();
        new Thread(() -> getActivity().runOnUiThread(() -> {
            RequestAPI.getComicSearch(lnAdapter, mListData, title);
        })).start();
        return mListData;
    }
}