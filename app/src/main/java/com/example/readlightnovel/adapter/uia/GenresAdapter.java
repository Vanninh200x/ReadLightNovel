package com.example.readlightnovel.adapter.uia;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readlightnovel.R;
import com.example.readlightnovel.interfaces.OnItemGenresClickListener;
import com.example.readlightnovel.model.uim.ItemGenres;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private List<ItemGenres> mList;
    private OnItemGenresClickListener onClickListener;

    public GenresAdapter(List<ItemGenres> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genres_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemGenres item = mList.get(position);
        holder.button.setTextColor(Color.WHITE);
        holder.button.setText(item.getTitle());

        holder.button.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, item);
            }
        });
    }


    public void setOnClickListener(OnItemGenresClickListener onClickListener) {
        this.onClickListener = onClickListener;
        Log.e("CHECK-GENRES", "GENRES");
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnGenres);
        }
    }
}
