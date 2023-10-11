package com.example.readlightnovel.adapter.lightnovel;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.readlightnovel.R;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.interfaces.OnSaveDataClickListener;
import com.orhanobut.hawk.Hawk;

import java.util.List;

public class SaveDataAdapter extends RecyclerView.Adapter<SaveDataAdapter.ViewHolder>{
    private OnSaveDataClickListener onClickListener;
    private List<SaveData> listSaveData;

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<SaveData> mList) {
        this.listSaveData = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_novel_item, parent, false);
        return new ViewHolder(view);
    }

    public void setOnClickListener(OnSaveDataClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SaveData lightNovel = listSaveData.get(position);

        Glide.with(holder.ivComic.getContext()).load(lightNovel.getAvatarUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(26))).into(holder.ivComic);

        holder.tvTitle.setText(lightNovel.getTitle());
        holder.tvCategory.setText(lightNovel.getCategory());
        holder.tvInfo.setText(lightNovel.showChapter());
        holder.ratingBar.setRating(lightNovel.getStar());
        holder.ratingBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                if (Hawk.get("user_id") != null) {
                    RequestAPI.getRattingByUser(Hawk.get("user_id"), lightNovel.getId());
                }
                onClickListener.onClick(position, lightNovel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSaveData != null ? listSaveData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivComic;
    private TextView tvTitle;
    private TextView tvCategory;
    private TextView tvInfo;
    private AppCompatRatingBar ratingBar;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        ivComic = itemView.findViewById(R.id.ivComic);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvCategory = itemView.findViewById(R.id.tvCategory);
        tvInfo = itemView.findViewById(R.id.tvInfo);
        ratingBar = itemView.findViewById(R.id.rattingBar);
    }
}
}
