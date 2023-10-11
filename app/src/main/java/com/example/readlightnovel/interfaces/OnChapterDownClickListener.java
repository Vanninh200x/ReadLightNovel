package com.example.readlightnovel.interfaces;

import com.example.readlightnovel.database.ChapterSave;
import com.example.readlightnovel.model.chapter.Data;

public interface OnChapterDownClickListener {
    void onClick(int position, ChapterSave item);
}
