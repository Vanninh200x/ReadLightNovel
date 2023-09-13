package com.example.readlightnovel.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.readlightnovel.adapter.detail.CommentAdapter;
import com.example.readlightnovel.callback.CallBackRate;
import com.example.readlightnovel.fragment.detail.FragmentChapter;
import com.example.readlightnovel.fragment.detail.FragmentComments;
import com.example.readlightnovel.fragment.detail.FragmentDescription;
import com.example.readlightnovel.model.chapter.Data;

import java.util.List;

public class ViewPagerDetailAdapter extends FragmentStateAdapter {

    private List<Data> mListChapter;
    private List<com.example.readlightnovel.model.comment.Data> mListComment;
    private com.example.readlightnovel.model.comic.Data data;
    private CommentAdapter commentAdapter;
    private CallBackRate callBackRate;

    public ViewPagerDetailAdapter(@NonNull Fragment fragment, com.example.readlightnovel.model.comic.Data data, List<Data> mListChapter, List<com.example.readlightnovel.model.comment.Data> mListComment, CommentAdapter commentAdapter, CallBackRate callBackRate) {
        super(fragment);
        this.data = data;
        this.mListChapter = mListChapter;
        this.mListComment = mListComment;
        this.commentAdapter = commentAdapter;
        this.callBackRate = callBackRate;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new FragmentChapter(mListChapter);
            case 2:
                return new FragmentComments(mListComment, commentAdapter, data);
            case 0:
                return new FragmentDescription(data, callBackRate);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}