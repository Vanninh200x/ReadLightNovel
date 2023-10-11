package com.example.readlightnovel;

import android.app.Application;

import com.example.readlightnovel.database.AppDatabase;
import com.example.readlightnovel.database.ChapterSave;
import com.example.readlightnovel.database.SaveData;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class ReadLightNovelApplication extends Application {

    public static AppDatabase appDatabase;
    public static List<SaveData> mSaveDataList;
    public static List<ChapterSave> mChapterSaveList;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = AppDatabase.getInstance(getApplicationContext());
        mSaveDataList = new ArrayList<>();
        mChapterSaveList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (appDatabase.saveDataDAO().getAll().size() != 0){
                    System.out.println("Add All");
                    mSaveDataList.addAll(appDatabase.saveDataDAO().getAll());
                }
            }
        }).start();

        Hawk.init(getApplicationContext()).build();
        Hawk.put("mode", 2);
        Hawk.put("font_value", 2);
    }
}
