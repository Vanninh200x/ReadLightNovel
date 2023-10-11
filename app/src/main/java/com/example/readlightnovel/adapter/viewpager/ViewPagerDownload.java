package com.example.readlightnovel.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.readlightnovel.database.ChapterSave;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.fragment.detail.FragmentChapter;
import com.example.readlightnovel.fragment.detail.FragmentDescription;
import com.example.readlightnovel.fragment.yourbook.DetailDownload;

import java.util.List;


public class ViewPagerDownload extends FragmentStateAdapter {
    private SaveData saveData;
    private List<ChapterSave> mChapterSaveList;

    public ViewPagerDownload(@NonNull DetailDownload fragmentActivity, SaveData saveData, List<ChapterSave> mChapterSaveList) {
        super(fragmentActivity);
        this.saveData = saveData;
        this.mChapterSaveList = mChapterSaveList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentDescription(saveData);
            case 1:
                return new FragmentChapter(mChapterSaveList,saveData);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}