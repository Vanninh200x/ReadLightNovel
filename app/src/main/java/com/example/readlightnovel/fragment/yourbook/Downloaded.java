package com.example.readlightnovel.fragment.yourbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.readlightnovel.ReadLightNovelApplication;
import com.example.readlightnovel.adapter.lightnovel.SaveDataAdapter;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.databinding.FragmentDownloaedBinding;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnSaveDataClickListener;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.List;

public class Downloaded extends Fragment {
    private FragmentDownloaedBinding binding;
    private SaveDataAdapter adapter;


    public Downloaded(SaveDataAdapter saveDataAdapter) {
        this.adapter = saveDataAdapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDownloaedBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();

        return view;

    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);

        adapter.setData(ReadLightNovelApplication.mSaveDataList);
        binding.rcv.setAdapter(adapter);


        if (ReadLightNovelApplication.mSaveDataList != null) {
            adapter.setOnClickListener((position, item) -> {
                if (DialogUtils.enableShowDialogFragment(getActivity().getSupportFragmentManager(), DialogFragmentDetail.class.getSimpleName())) {
                    new DetailDownload(item).show(getActivity().getSupportFragmentManager(), DialogFragmentDetail.class.getSimpleName());
                }
            });
        }
    }
}