package com.example.readlightnovel.adapter.uia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readlightnovel.R;
import com.example.readlightnovel.interfaces.OnItemClickListener;
import com.example.readlightnovel.model.uim.ItemMenu;

import java.util.List;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private final Context mContext;
    private final List<ItemMenu> mList;
    private OnItemClickListener onClickListener;

    public MenuAdapter(List<ItemMenu> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceType"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemMenu itemMenu = mList.get(position);
        holder.ivIcon.setImageDrawable(mContext.getDrawable(itemMenu.getIcon()));
        holder.tvTitle.setText(itemMenu.getTitle());
        holder.ivIcon.setColorFilter(Color.parseColor(mContext.getString(R.color.un_selected)));
        holder.tvTitle.setTextColor(Color.BLACK);

        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, itemMenu);
            }
        });
    }


    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivIcon;
        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivIcon = itemView.findViewById(R.id.ivIconMenu);
        }
    }
}
