package com.example.readlightnovel.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.fragment.home.HomeFirst;
import com.example.readlightnovel.fragment.home.HomeSecond;
import com.example.readlightnovel.model.comic.Data;

import java.util.List;

public class ViewPagerHomeAdapter extends FragmentStateAdapter {
    private List<Data> mListLightNovel;
    private LNAdapter lightNovelAdapter;

    public ViewPagerHomeAdapter(@NonNull Fragment fragment, List<Data> mListLightNovel, LNAdapter lightNovelAdapter) {
        super(fragment);
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0 :
                return new HomeFirst();
            default:
                return new HomeSecond(mListLightNovel, lightNovelAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 21;
    }
}
