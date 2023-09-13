package com.example.readlightnovel.fragment.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.readlightnovel.R;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.DialogDetailChapterBinding;
import com.example.readlightnovel.model.chapter.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.orhanobut.hawk.Hawk;

import java.util.List;

public class DetailRead extends DialogFragment {
    private Dialog dialog;
    private Activity activity;
    private DialogDetailChapterBinding binding;
    private Data data;
    private List<Data> mList;
    private int currentChapter;
    private int userId;
    private ImageView ivModeDark, ivModeLight, ivModeYellow;
    private TextView tvCherrySwash, tvNormal, tvAvo, tvDominique;
    private LinearLayout fontNormal, fontCherrySwash, fontAvo, fontDominique;
    private int mode;
    private BottomSheetDialog bottomSheetDialog;
    private ImageView ivModeFont1, ivModeFont2, ivModeFont3, ivModeFont4;

    public DetailRead(Data data, List<Data> mList) {
        this.data = data;
        this.mList = mList;
        currentChapter = Integer.parseInt(data.getNumber()) - 1;
        userId = (Hawk.get("user_id")) == null ? -1 : Hawk.get("user_id");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        dialog = DialogUtils.getNewDialog(activity);
        binding = DialogDetailChapterBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());

        init();
        initBottomSheetData();
        initFontData();
        initView();
        initListener();
        return dialog;
    }

    @SuppressLint("ResourceType")
    private void init() {
        bottomSheetDialog = new BottomSheetDialog(activity);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_font);
        fontNormal = bottomSheetDialog.findViewById(R.id.fontNormal);
        fontCherrySwash = bottomSheetDialog.findViewById(R.id.fontCherrySwash);
        fontAvo = bottomSheetDialog.findViewById(R.id.fontAvo);
        fontDominique = bottomSheetDialog.findViewById(R.id.fontDominique);

        ivModeDark = bottomSheetDialog.findViewById(R.id.ivModeDark);
        ivModeLight = bottomSheetDialog.findViewById(R.id.ivModeLight);
        ivModeYellow = bottomSheetDialog.findViewById(R.id.ivModeYellow);
        ivModeDark.setColorFilter(Color.BLACK);
        ivModeYellow.setColorFilter(Color.YELLOW);

        tvNormal = bottomSheetDialog.findViewById(R.id.tvNormal);
        tvCherrySwash = bottomSheetDialog.findViewById(R.id.tvCherrySwash);
        tvAvo = bottomSheetDialog.findViewById(R.id.tvAvo);
        tvDominique = bottomSheetDialog.findViewById(R.id.tvDominique);

        ivModeFont1 = bottomSheetDialog.findViewById(R.id.ivModeFont1);
        ivModeFont2 = bottomSheetDialog.findViewById(R.id.ivModeFont2);
        ivModeFont3 = bottomSheetDialog.findViewById(R.id.ivModeFont3);
        ivModeFont4 = bottomSheetDialog.findViewById(R.id.ivModeFont4);

        binding.ivBack.setColorFilter(activity.getColor(R.color.default_tv));
        binding.ivFont.setColorFilter(activity.getColor(R.color.default_tv));
        binding.ivNext.setColorFilter(activity.getColor(R.color.default_tv));


        tvCherrySwash.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "FS CherrySwash-Regular.otf"));
        tvAvo.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "SVN-Avo.ttf"));
        tvDominique.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "SVN-Dominique.otf"));
    }

    private void initView() {
        binding.tvTitle.setText("Chapter " + data.getNumber() + ": " + data.getChapterTitle());
        if (userId != -1) {
            RequestAPI.postBookRequest(data.getBookId(), userId);
        }
//        binding.tvContent.setText(data.getContent());
        binding.tvContent.setText(Html.fromHtml(data.getContent()));

    }

    private void initBottomSheetData() {
        int x = Hawk.get("mode");
        switch (x) {
            case 1:
                binding.scrollView.setBackgroundColor(activity.getColor(R.color.bg_black));
                binding.tvContent.setTextColor(activity.getColor(R.color.text_mode_bg_black));
                ivModeDark.setBackgroundColor(activity.getColor(R.color.default_tv));
                ivModeLight.setBackgroundColor(Color.TRANSPARENT);
                ivModeYellow.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 2:
                binding.scrollView.setBackgroundColor(Color.TRANSPARENT);
                binding.tvContent.setTextColor(Color.BLACK);
                ivModeDark.setBackgroundColor(Color.TRANSPARENT);
                ivModeLight.setBackgroundColor(activity.getColor(R.color.default_tv));
                ivModeYellow.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 3:
                binding.scrollView.setBackgroundColor(activity.getColor(R.color.bg_yellow));
                binding.tvContent.setTextColor(Color.BLACK);
                ivModeDark.setBackgroundColor(Color.TRANSPARENT);
                ivModeLight.setBackgroundColor(Color.TRANSPARENT);
                ivModeYellow.setBackgroundColor(activity.getColor(R.color.default_tv));
                break;
        }
    }


    private void initFontData() {
        int x = Hawk.get("font_value");
        switch (x) {
            case 1:
                ivModeFont1.setColorFilter(Color.BLACK);
                tvNormal.setTextColor(Color.BLACK);
                setUnSelected(ivModeFont2, tvCherrySwash);
                setUnSelected(ivModeFont3, tvAvo);
                setUnSelected(ivModeFont4, tvDominique);

                binding.tvContent.setTypeface(Typeface.DEFAULT);
                break;
            case 2:
                ivModeFont2.setColorFilter(Color.BLACK);
                tvCherrySwash.setTextColor(Color.BLACK);
                setUnSelected(ivModeFont1, tvNormal);
                setUnSelected(ivModeFont3, tvAvo);
                setUnSelected(ivModeFont4, tvDominique);
                binding.tvContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "FS CherrySwash-Regular.otf"));

                break;
            case 3:
                ivModeFont3.setColorFilter(Color.BLACK);
                tvAvo.setTextColor(Color.BLACK);
                setUnSelected(ivModeFont2, tvCherrySwash);
                setUnSelected(ivModeFont1, tvNormal);
                setUnSelected(ivModeFont4, tvDominique);
                binding.tvContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "SVN-Avo.ttf"));

                break;
            case 4:
                ivModeFont4.setColorFilter(Color.BLACK);
                tvDominique.setTextColor(Color.BLACK);
                setUnSelected(ivModeFont2, tvCherrySwash);
                setUnSelected(ivModeFont3, tvAvo);
                setUnSelected(ivModeFont1, tvNormal);
                binding.tvContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "SVN-Dominique.otf"));

                break;
        }
    }

    private void setUnSelected(ImageView iv, TextView tv){
        iv.setColorFilter(activity.getColor(R.color.default_tv));
        tv.setTextColor(activity.getColor(R.color.default_tv));
    }

    private void initListener() {
        binding.ivBack.setOnClickListener(v -> {
            dialog.dismiss();
        });

        binding.ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentChapter == mList.size() - 1) {
                    Toast.makeText(activity, R.string.last_chapter, Toast.LENGTH_SHORT).show();
                } else {
                    currentChapter++;
                    binding.tvTitle.setText("Chapter " + mList.get(currentChapter).getNumber() + ": " + mList.get(currentChapter).getChapterTitle());
                    binding.tvContent.setText(mList.get(currentChapter).getContent());
                }
            }
        });

        binding.ivFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
                initBottomSheetData();
                initFontData();
            }
        });


    }

    private void showBottomSheetDialog() {
        bottomSheetDialog.show();

        ivModeDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.scrollView.setBackgroundColor(activity.getColor(R.color.bg_black));
                binding.tvContent.setTextColor(activity.getColor(R.color.text_mode_bg_black));
                bottomSheetDialog.dismiss();
                mode = 1;
                Hawk.put("mode", mode);
            }
        });

        ivModeLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.scrollView.setBackgroundColor(Color.TRANSPARENT);
                binding.tvContent.setTextColor(Color.BLACK);
                bottomSheetDialog.dismiss();
                mode = 2;
                Hawk.put("mode", mode);
            }
        });

        ivModeYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.scrollView.setBackgroundColor(activity.getColor(R.color.bg_yellow));
                binding.tvContent.setTextColor(Color.BLACK);
                bottomSheetDialog.dismiss();
                mode = 3;
                Hawk.put("mode", mode);
            }
        });


        fontNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setTypeface(Typeface.DEFAULT);
                bottomSheetDialog.dismiss();
                Hawk.put("font_value", 1);
            }
        });

        fontCherrySwash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "FS CherrySwash-Regular.otf"));
                bottomSheetDialog.dismiss();
                Hawk.put("font_value", 2);
            }
        });

        fontAvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "SVN-Avo.ttf"));
                bottomSheetDialog.dismiss();
                Hawk.put("font_value", 3);
            }
        });


        fontDominique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "SVN-Dominique.otf"));
                bottomSheetDialog.dismiss();
                Hawk.put("font_value", 4);
            }
        });
    }
}
