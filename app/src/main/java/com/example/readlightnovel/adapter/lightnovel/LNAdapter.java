package com.example.readlightnovel.adapter.lightnovel;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.readlightnovel.R;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.model.comic.Data;
import com.orhanobut.hawk.Hawk;

import java.util.List;

public class LNAdapter extends RecyclerView.Adapter<LNAdapter.ViewHolder> {
    private List<Data> mList;
    private OnLightNovelClickListener onClickListener;


    public LNAdapter() {
    }

    public void setData(List<Data> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_novel_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data lightNovel = mList.get(position);

        Glide.with(holder.ivComic.getContext()).load(lightNovel.getAvatarUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(26))).into(holder.ivComic);

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

    public void setOnClickListener(OnLightNovelClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
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
