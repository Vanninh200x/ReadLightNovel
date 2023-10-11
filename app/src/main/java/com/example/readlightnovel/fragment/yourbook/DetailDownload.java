package com.example.readlightnovel.fragment.yourbook;

import static com.example.readlightnovel.ReadLightNovelApplication.appDatabase;
import static com.example.readlightnovel.ReadLightNovelApplication.mChapterSaveList;
import static com.example.readlightnovel.ReadLightNovelApplication.mSaveDataList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Size;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.palette.graphics.Palette;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.viewpager.ViewPagerDownload;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.databinding.DialogFragmentDetailDownloadBinding;

import com.example.readlightnovel.utils.desa.BitmapEffectUtils;
import com.example.readlightnovel.utils.desa.BitmapUtils;
import com.example.readlightnovel.utils.desa.DialogUtils;
import com.example.readlightnovel.utils.desa.SizeUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class DetailDownload extends DialogFragment {
    private Dialog dialog;
    private Activity activity;
    private DialogFragmentDetailDownloadBinding binding;
    private final String[] title = {"Description", "Chapters"};
    private SaveData saveData;
    private Executor executor;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        dialog = DialogUtils.getNewDialog(activity);
        binding = DialogFragmentDetailDownloadBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        init();
        initData();
        initThemes();
        initListener();
        return dialog;
    }

    public DetailDownload(SaveData saveData) {
        this.saveData = saveData;
    }

    private void init() {
        executor = Executors.newSingleThreadExecutor();
    }

    private void initListener() {
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            final int tabPosition = i;
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            if (tab != null) {
                tab.view.setOnClickListener(v -> {
                    if (tabPosition == 1) {

                    }
                });
            }
        }

    }


    private void initData() {
        mChapterSaveList.clear();
        executor.execute(() -> {
            appDatabase.saveDataDAO().getListByID(saveData.getId());
            mChapterSaveList.addAll(appDatabase.saveDataDAO().getListByID(saveData.getId()));
        });

        Glide.with(this).asBitmap().load(saveData.getAvatarUrl()).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap bitmapBlur = BitmapEffectUtils.toBlur(activity, resource.copy(resource.getConfig(), resource.isMutable()), 15);
                bitmapBlur = BitmapUtils.resizeBitmap(bitmapBlur, 1920, 1080);

                Size sizeFitVideo = SizeUtils.getSizeFitParent(new Size(1920, 1600), new Size(bitmapBlur.getWidth(), bitmapBlur.getHeight()));
                bitmapBlur = BitmapUtils.scaleBitmapKeepAspectRatio(bitmapBlur, sizeFitVideo.getHeight());
                binding.imageView.setAlpha(.9F);
                binding.imageView.setImageBitmap(bitmapBlur);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });

        Glide.with(getActivity()).load(saveData.getAvatarUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(26))).into(binding.include.ivComic);
        binding.include.tvTitle.setText(saveData.getTitle());
        binding.include.tvCategory.setText(saveData.getCategory());
        binding.include.tvInfo.setText(saveData.showChapter());
        binding.include.rattingBar.setRating(saveData.getStar());
        binding.include.rattingBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initThemes() {
        initToolBar();
        setTabLayoutAnimation();

        ViewPagerDownload viewPagerDownload = new ViewPagerDownload(this, saveData,mChapterSaveList);
        binding.viewPager2.setAdapter(viewPagerDownload);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            tab.setText(title[position]);
        }).attach();


        binding.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.black));
        binding.tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.un_selected), ContextCompat.getColor(getContext(), R.color.black));

        binding.include.tvTitle.setTextColor(Color.WHITE);
        binding.include.tvInfo.setTextColor(Color.WHITE);
        binding.include.ivDownLoad.setColorFilter(Color.WHITE);
        binding.include.rattingBar.setAlpha(0f);

    }

    private void initToolBar() {
        ((AppCompatActivity) activity).setSupportActionBar(binding.toolBar);
        if (((AppCompatActivity) activity).getSupportActionBar() != null) {
            ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolBar.setTitle("");
        binding.toolBar.setNavigationOnClickListener(v -> dialog.dismiss());

    }

    private void setTabLayoutAnimation() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lightnovel);
        Palette.from(bitmap).generate(palette -> {
            int myColor = palette.getVibrantColor(getResources().getColor(R.color.color_sign_left));
            int myDarkColor = palette.getVibrantColor(getResources().getColor(R.color.white));

            binding.toolBarLayout.setContentScrimColor(myColor);
            binding.toolBarLayout.setStatusBarScrimColor(myDarkColor);
        });
    }


}
