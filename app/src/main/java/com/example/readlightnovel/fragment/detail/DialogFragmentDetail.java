package com.example.readlightnovel.fragment.detail;


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
import android.os.Environment;
import android.util.Size;
import android.widget.Toast;


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
import com.example.readlightnovel.adapter.detail.CommentAdapter;
import com.example.readlightnovel.adapter.viewpager.ViewPagerDetailAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.callback.CallBackRate;
import com.example.readlightnovel.database.AppDatabase;
import com.example.readlightnovel.database.ChapterSave;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.databinding.DialogFragmentDetailBinding;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.model.rate.PickRate;
import com.example.readlightnovel.utils.desa.BitmapEffectUtils;
import com.example.readlightnovel.utils.desa.BitmapUtils;
import com.example.readlightnovel.utils.desa.DialogUtils;
import com.example.readlightnovel.utils.desa.SizeUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DialogFragmentDetail extends DialogFragment implements CallBackRate {
    private Dialog dialog;
    private Activity activity;
    private DialogFragmentDetailBinding binding;
    private final Data data;
    private final String[] title = {"Description", "Chapters", "Comments"};
    private List<com.example.readlightnovel.model.chapter.Data> mListChapter;
    private List<com.example.readlightnovel.model.comment.Data> mListComment;
    private CommentAdapter commentAdapter;
    private List<Data> mListMakeRate;
    private Executor executor;
    private String pathImage;


    public DialogFragmentDetail(Data data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        dialog = DialogUtils.getNewDialog(activity);
        binding = DialogFragmentDetailBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        init();
        getChapter();
        initData();
        initThemes();
        initListener();
        return dialog;
    }

    private void init() {
        mListChapter = new ArrayList<>();
        mListComment = new ArrayList<>();
        commentAdapter = new CommentAdapter(mListComment);
        mListMakeRate = new ArrayList<>();
        executor = Executors.newSingleThreadExecutor();
    }

    private void initListener() {
        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            final int tabPosition = i;
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            if (tab != null) {
                tab.view.setOnClickListener(v -> {
                    if (tabPosition == 2) {
                        commentAdapter.setData(addComment());
                    }
                });
            }
        }

        binding.include.ivComic.setOnClickListener(v -> {
            executor.execute(() -> {
//                System.out.println("DBChapter: "+appDatabase.saveDataDAO().getAll() +"");
            });

        });


        binding.include.ivDownLoad.setOnClickListener(v -> {
            saveImageFromUrl(data.getAvatarUrl(), data.getId());

        });
    }


    private void saveImageFromUrl(String imageUrl, int id) {
        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    // Lấy thư mục cache của ứng dụng
                    File cacheDir = getContext().getCacheDir();

                    // Xây dựng tên tệp tin cho ảnh hoặc sử dụng tên từ URL (nếu có)
                    String fileName = "save_img" + id + ".jpg";

                    // Tạo tệp tin trong thư mục cache
                    File imageFile = new File(cacheDir, fileName);

                    // Ghi dữ liệu ảnh vào tệp tin
                    FileOutputStream fos = new FileOutputStream(imageFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();

                    pathImage = cacheDir + "/" + fileName;
                    System.out.println(pathImage);

                    executor.execute(() -> {
                        // Thực hiện các thao tác cơ sở dữ liệu ở đây
                        if (appDatabase.saveDataDAO().getSaveDataById(data.getId()) == null) {
                            appDatabase.saveDataDAO().insertData(convertSaveData(data, pathImage));
                            mSaveDataList.add(appDatabase.saveDataDAO().getSaveDataById(data.getId()));
                        }

                        if (mListChapter != null) {
                            for (com.example.readlightnovel.model.chapter.Data data1 : mListChapter) {
                                appDatabase.saveDataDAO().insertChapter(convertChapterSave(data1));
                                System.out.println("Chapter: "+ data1+"");
                            }
                        }
                    });
                    Toast.makeText(activity, "Lưu ảnh thành công", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Xử lý lỗi khi lưu ảnh thất bại
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                e.printStackTrace();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    private SaveData convertSaveData(Data data, String path) {
        SaveData saveData = new SaveData();
        saveData.setId(data.getId());
        saveData.setAvatarUrl(path);
        saveData.setStatus(data.isStatus());
        saveData.setDescription(data.getDescription());
        saveData.setTitle(data.getTitle());
        saveData.setDone(data.isDone());
        saveData.setStar(data.getStar());
        saveData.setCategory(data.getCategory());
        saveData.setChapter(data.getChapter());
        System.out.println(saveData.toString());
        return saveData;
    }

    private ChapterSave convertChapterSave(com.example.readlightnovel.model.chapter.Data data) {
        ChapterSave chapterSave = new ChapterSave(data.getBookId(), data.getName(), data.getNumber(), data.getChapterTitle(), data.getContent());
        return chapterSave;
    }

    private void getChapter() {
        RequestAPI.getChapter(data.getId(), mListChapter);
        mListComment = addComment();
    }

    private void initData() {

        Glide.with(this).asBitmap().load(data.getAvatarUrl()).into(new CustomTarget<Bitmap>() {
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

        Glide.with(getActivity()).load(data.getAvatarUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(26))).into(binding.include.ivComic);
        binding.include.tvTitle.setText(data.getTitle());
        binding.include.tvCategory.setText(data.getCategory());
        binding.include.tvInfo.setText(data.showChapter());
        binding.include.rattingBar.setRating(data.getStar());
        binding.include.rattingBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initThemes() {
        initToolBar();
        setTabLayoutAnimation();

        ViewPagerDetailAdapter viewPagerDetailAdapter = new ViewPagerDetailAdapter(this, data, mListChapter, mListComment, commentAdapter, this::onCallBack);
        binding.viewPager2.setAdapter(viewPagerDetailAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            tab.setText(title[position]);
        }).attach();


        binding.tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.black));
        binding.tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.un_selected), ContextCompat.getColor(getContext(), R.color.black));

        binding.include.tvTitle.setTextColor(Color.WHITE);
        binding.include.tvInfo.setTextColor(Color.WHITE);
        binding.include.ivDownLoad.setColorFilter(Color.WHITE);
        binding.include.ivDownLoad.setAlpha(1f);
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

    private List<com.example.readlightnovel.model.comment.Data> addComment() {
        mListComment.clear();
        new Thread(() -> activity.runOnUiThread(() -> {
            RequestAPI.getComment(commentAdapter, data.getId(), mListComment);

        })).start();
        return mListComment;
    }

    @Override
    public void onCallBack(PickRate pickRate) {
        //TODO
        RequestAPI.getComicRefresh(mListMakeRate, (int) pickRate.getBookId(), binding.include.rattingBar);
    }
}
