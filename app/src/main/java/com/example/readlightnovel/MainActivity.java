package com.example.readlightnovel;


import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.ActivityMainBinding;
import com.example.readlightnovel.adapter.viewpager.ViewPagerMainAdapter;
import com.example.readlightnovel.model.comic.Data;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPagerMainAdapter viewPagerAdapter;
    private List<Data> mListLightNovel = new ArrayList<>();
    private LNAdapter lightNovelAdapter = new LNAdapter();
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        setViewPager();
        initListener();
    }

    private void init() {
        userId = (Hawk.get("user_id")) == null ? -1 : Hawk.get("user_id");

    }

    private void initListener() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    binding.viewPager.setCurrentItem(0);
                    break;
                case R.id.action_genres:
                    binding.viewPager.setCurrentItem(1);
                    break;
                case R.id.action_search:
                    binding.viewPager.setCurrentItem(2);
                    break;
                case R.id.action_your_book:
                    userId = (Hawk.get("user_id")) == null ? -1 : Hawk.get("user_id");
                    if (userId != -1) {
                        lightNovelAdapter.setData(addListByUserId(userId));
                    } else {
                        lightNovelAdapter.setData(clearList());
                    }
                    binding.viewPager.setCurrentItem(3);
                    break;
                case R.id.action_menu:
                    binding.viewPager.setCurrentItem(4);
                    break;
            }
            return false;
        });
    }

    private void setViewPager() {
        viewPagerAdapter = new ViewPagerMainAdapter(this, mListLightNovel, lightNovelAdapter);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.bottomNavigation.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigation.getMenu().findItem(R.id.action_genres).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigation.getMenu().findItem(R.id.action_search).setChecked(true);
                        break;
                    case 3:
                        binding.bottomNavigation.getMenu().findItem(R.id.action_your_book).setChecked(true);
                        break;
                    case 4:
                        binding.bottomNavigation.getMenu().findItem(R.id.action_menu).setChecked(true);
                        break;
                }
            }
        });
    }

    private List<Data> clearList(){
        mListLightNovel.clear();;
        return mListLightNovel;
    }

    private List<Data> addListByUserId(int id) {
        if (mListLightNovel.size() != 0) {
            mListLightNovel.clear();
        }
        new Thread(() -> runOnUiThread(() -> {
            RequestAPI.getComicByUserId(lightNovelAdapter, mListLightNovel, id);
        })).start();
        return mListLightNovel;
    }

}