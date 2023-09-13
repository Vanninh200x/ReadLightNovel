package com.example.readlightnovel;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class ReadLightNovelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext()).build();
        Hawk.put("mode",2);
        Hawk.put("font_value",2);
    }
}
