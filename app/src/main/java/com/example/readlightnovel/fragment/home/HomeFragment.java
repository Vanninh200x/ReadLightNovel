package com.example.readlightnovel.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.FragmentHomeBinding;
import com.example.readlightnovel.adapter.viewpager.ViewPagerHomeAdapter;
import com.example.readlightnovel.model.comic.Data;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private String[] title = {"Discovery", "Completed", "Romance", "Comedy", "Shounen", "Action", "Harem", "Martial Arts", "School Life", "Mystery", "Shoujo", "Sci-fi", "Gender Bender", "Mature", "Fantasy", "Horror", "Drama", "Tragedy", "Supernatural", "Ecchi", "Xuanhuan"};
    private ViewPagerHomeAdapter viewPagerHomeAdapter;

    private Activity activity;
    private List<Data> mListLightNovel;
    private LNAdapter lightNovelAdapter;

    public HomeFragment(List<Data> mListLightNovel, LNAdapter lightNovelAdapter) {
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        activity = getActivity();
        View view = binding.getRoot();
        init();
        return view;
    }

    private void init() {
        viewPagerHomeAdapter = new ViewPagerHomeAdapter(this, mListLightNovel, lightNovelAdapter);
        binding.viewPager.setAdapter(viewPagerHomeAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            tab.setText(title[position]);
        }).attach();

        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            final int tabPosition = i;
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            if (tab != null) {
                tab.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (tabPosition) {
                            case 0:
                                break;
                            case 1:
                                lightNovelAdapter.setData(addListDone());
                                break;
                            default:
                                lightNovelAdapter.setData(addListLightNovel(title[tabPosition]));
                                break;
                        }
                    }
                });
            }
        }

        binding.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.black));
        binding.tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.un_selected), ContextCompat.getColor(getContext(), R.color.black));
    }

    private List<Data> addListLightNovel(String category) {
        mListLightNovel.clear();
        new Thread(() -> activity.runOnUiThread(() -> {
            RequestAPI.getComicByCategory(lightNovelAdapter, mListLightNovel, category);
        })).start();
        return mListLightNovel;
    }

    private List<Data> addListDone() {
        mListLightNovel.clear();
        new Thread(() -> activity.runOnUiThread(() -> {
            RequestAPI.getComicDone(lightNovelAdapter, mListLightNovel);
        })).start();
        return mListLightNovel;
    }

}