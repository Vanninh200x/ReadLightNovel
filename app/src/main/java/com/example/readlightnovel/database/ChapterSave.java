package com.example.readlightnovel.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.readlightnovel.model.chapter.Data;

import java.util.List;


@Entity(tableName = "ChapterSave")
public class ChapterSave extends Data{
    @PrimaryKey(autoGenerate = true)
    private int id;
    public ChapterSave(int bookId, String name, String number, String chapterTitle, String content) {
        super(bookId, name, number, chapterTitle, content);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
