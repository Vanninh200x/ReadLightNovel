package com.example.readlightnovel.fragment.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readlightnovel.adapter.lightnovel.ChildRecyclerViewAdapter;
import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.adapter.lightnovel.LNParentAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.FragmentHomeFirstBinding;
import com.example.readlightnovel.fragment.genres.DetailGenres;
import com.example.readlightnovel.interfaces.OnParentClickListener;
import com.example.readlightnovel.model.uim.LightNovelParent;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.NetworkUtils;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.ArrayList;
import java.util.List;


public class HomeFirst extends Fragment {
    private LNParentAdapter lnParentAdapter;
    private List<Data> mListData;
    private ChildRecyclerViewAdapter childRecyclerViewAdapter;
    private LNAdapter adapter = new LNAdapter();
    private List<LightNovelParent> mListParent;
    private FragmentHomeFirstBinding binding;
    private FragmentManager fragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init();
        showRecyclerView();
        return view;
    }

    public HomeFirst() {

    }

    private void init() {
        fragmentManager = getActivity().getSupportFragmentManager();
        mListParent = new ArrayList<>();
        mListData = new ArrayList<>();

        if (!NetworkUtils.isNetworkAvailable(getActivity())){
            binding.frameLayout.setVisibility(View.VISIBLE);
        }else{
            binding.frameLayout.setVisibility(View.GONE);
        }
    }

    private void showRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);
        lnParentAdapter = new LNParentAdapter(mListParent, getActivity(), addListLightNovel(), fragmentManager);

        mListParent.add(new LightNovelParent("Shounen"));
        mListParent.add(new LightNovelParent("Harem"));
        mListParent.add(new LightNovelParent("Comedy"));
        mListParent.add(new LightNovelParent("Martial Arts"));
        mListParent.add(new LightNovelParent("School Life"));
        mListParent.add(new LightNovelParent("Mystery"));
        mListParent.add(new LightNovelParent("Shoujo"));
        mListParent.add(new LightNovelParent("Romance"));
        mListParent.add(new LightNovelParent("Sci-fi"));
        mListParent.add(new LightNovelParent("Gender Bender"));

        binding.rcv.setAdapter(lnParentAdapter);

        lnParentAdapter.setOnClickListener(new OnParentClickListener() {
            @Override
            public void onClick(int position, String item) {
                if (DialogUtils.enableShowDialogFragment(getActivity().getSupportFragmentManager(), DetailGenres.class.getSimpleName())) {
                    new DetailGenres(item, adapter).show(getActivity().getSupportFragmentManager(), DetailGenres.class.getSimpleName());
                }
            }
        });
    }


    private List<Data> addListLightNovel() {
        mListData.clear();
        new Thread(() -> getActivity().runOnUiThread(() -> {
            RequestAPI.getComic(lnParentAdapter, mListData);
        })).start();
        return mListData;
    }

    private List<Data> addListLightNovel(String category) {
        mListData.clear();
        new Thread(() -> getActivity().runOnUiThread(() -> {
            RequestAPI.getComicByCategory(adapter, mListData, category);
        })).start();
        return mListData;
    }

}