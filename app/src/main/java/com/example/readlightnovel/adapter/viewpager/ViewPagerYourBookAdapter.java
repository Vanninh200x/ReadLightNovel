package com.example.readlightnovel.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.adapter.lightnovel.SaveDataAdapter;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.fragment.yourbook.Downloaded;
import com.example.readlightnovel.fragment.yourbook.History;
import com.example.readlightnovel.fragment.yourbook.YourBookFragment;
import com.example.readlightnovel.model.comic.Data;

import java.util.List;

public class ViewPagerYourBookAdapter extends FragmentStateAdapter {
    private  List<Data> mListLightNovel;
    private LNAdapter lightNovelAdapter;
    private SaveDataAdapter saveDataAdapter;
    public ViewPagerYourBookAdapter(@NonNull YourBookFragment fragmentActivity, List<Data> mListLightNovel, LNAdapter lightNovelAdapter,SaveDataAdapter saveDataAdapter) {
        super(fragmentActivity);
        this.mListLightNovel = mListLightNovel;
        this.lightNovelAdapter = lightNovelAdapter;
        this.saveDataAdapter = saveDataAdapter;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new History(mListLightNovel,lightNovelAdapter);
            case 1:
                return new Downloaded(saveDataAdapter);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}