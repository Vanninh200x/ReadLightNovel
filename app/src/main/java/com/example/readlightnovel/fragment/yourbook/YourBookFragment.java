package com.example.readlightnovel.fragment.yourbook;

import static com.example.readlightnovel.ReadLightNovelApplication.appDatabase;
import static com.example.readlightnovel.ReadLightNovelApplication.mSaveDataList;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.adapter.lightnovel.SaveDataAdapter;
import com.example.readlightnovel.adapter.viewpager.ViewPagerDetailAdapter;
import com.example.readlightnovel.adapter.viewpager.ViewPagerHomeAdapter;
import com.example.readlightnovel.adapter.viewpager.ViewPagerYourBookAdapter;
import com.example.readlightnovel.database.AppDatabase;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.databinding.FragmentYourBookBinding;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.interfaces.OnSaveDataClickListener;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class YourBookFragment extends Fragment {

    private FragmentYourBookBinding binding;
    private String[] title = {"History", "Downloaded"};
    private ViewPagerYourBookAdapter viewPagerYourBookAdapter;

    private List<Data> mListLightNovel;
    private LNAdapter lightNovelAdapter;
    private SaveDataAdapter saveDataAdapter;

    public YourBookFragment(List<Data> mListLightNovel, LNAdapter lightNovelAdapter) {
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentYourBookBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        init();
        return view;
    }

    private void init() {
        saveDataAdapter = new SaveDataAdapter();
        viewPagerYourBookAdapter = new ViewPagerYourBookAdapter(this, mListLightNovel, lightNovelAdapter, saveDataAdapter);
        binding.viewPager2.setAdapter(viewPagerYourBookAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
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
                                saveDataAdapter.setData(mSaveDataList);
                                break;
                        }
                    }
                });
            }
        }

        binding.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.black));
        binding.tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.un_selected), ContextCompat.getColor(getContext(), R.color.black));
    }


}