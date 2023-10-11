package com.example.readlightnovel.adapter.detail;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readlightnovel.R;
import com.example.readlightnovel.database.ChapterSave;
import com.example.readlightnovel.database.SaveData;
import com.example.readlightnovel.interfaces.OnChapterClickListener;
import com.example.readlightnovel.interfaces.OnChapterDownClickListener;
import com.example.readlightnovel.model.chapter.Data;

import java.util.List;

public class ChapterDownAdapter extends RecyclerView.Adapter<ChapterDownAdapter.ViewHolder> {
    private List<ChapterSave> mList;
    private OnChapterDownClickListener onClickListener;

    public ChapterDownAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ChapterSave> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_layout, parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChapterSave item = mList.get(position);

        holder.tvChapter.setText("Chapter " + item.getNumber() + ": " + item.getChapterTitle());
        holder.itemView.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(position, item);
            }
        });
    }

    public void setOnClickListener(OnChapterDownClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvChapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapter = itemView.findViewById(R.id.tvChapter);
        }
    }
}
