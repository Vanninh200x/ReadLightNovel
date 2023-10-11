package com.example.readlightnovel.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.readlightnovel.model.chapter.Data;

import java.util.List;

@Dao
public interface SaveDataDAO {
    @Query("SELECT * FROM SaveData")
    List<SaveData> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertData(SaveData saveData);

    @Query("SELECT * FROM SaveData WHERE id = :id")
    SaveData getSaveDataById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertChapter(ChapterSave chapterSave);

    @Query("SELECT * FROM chaptersave WHERE bookId = :id")
    List<ChapterSave> getListByID(int id);

}
