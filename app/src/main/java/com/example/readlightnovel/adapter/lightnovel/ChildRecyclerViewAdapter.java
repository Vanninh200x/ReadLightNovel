package com.example.readlightnovel.adapter.lightnovel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.readlightnovel.R;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.model.comic.Data;
import com.orhanobut.hawk.Hawk;

import java.util.List;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder> {
    private List<Data> childModelArrayList;
    private Context cxt;
    private OnLightNovelClickListener onClickListener;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView heroImage;
        public TextView movieName;

        public MyViewHolder(View itemView) {
            super(itemView);
            heroImage = itemView.findViewById(R.id.hero_image);
            movieName = itemView.findViewById(R.id.movie_name);
        }
    }

    public void setData(List<Data> childModelArrayList) {
        this.childModelArrayList = childModelArrayList;
        notifyDataSetChanged();

    }

    public ChildRecyclerViewAdapter() {
    }

    public void setOnClickListener(OnLightNovelClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_novel_child, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data currentItem = childModelArrayList.get(position);

        Glide.with(holder.heroImage.getContext()).load(currentItem.getAvatarUrl()).centerCrop().apply(RequestOptions.bitmapTransform(new RoundedCorners(26))).into(holder.heroImage);

        holder.movieName.setText(currentItem.getTitle());

        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                if (Hawk.get("user_id") != null) {
                    RequestAPI.getRattingByUser((Hawk.get("user_id")), currentItem.getId());
                }
                onClickListener.onClick(position, currentItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}