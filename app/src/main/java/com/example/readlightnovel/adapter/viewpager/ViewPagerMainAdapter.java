package com.example.readlightnovel.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.fragment.genres.GenresFragment;
import com.example.readlightnovel.fragment.home.HomeFragment;
import com.example.readlightnovel.fragment.main.MenuFragment;
import com.example.readlightnovel.fragment.search.SearchFragment;
import com.example.readlightnovel.fragment.yourbook.YourBookFragment;
import com.example.readlightnovel.model.comic.Data;

import java.util.List;

public class ViewPagerMainAdapter extends FragmentStateAdapter  {
    private List<Data> mListLightNovel;
    private LNAdapter lightNovelAdapter;
    public ViewPagerMainAdapter(@NonNull FragmentActivity fragmentActivity, List<Data> mListLightNovel, LNAdapter lightNovelAdapter) {
        super(fragmentActivity);
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new GenresFragment();
            case 2:
                return new SearchFragment();
            case 3:
                return new YourBookFragment(mListLightNovel, lightNovelAdapter);
            case 4:
                return new MenuFragment();
            default:
                return new HomeFragment(mListLightNovel, lightNovelAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
