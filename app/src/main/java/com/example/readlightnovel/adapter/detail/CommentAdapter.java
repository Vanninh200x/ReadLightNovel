package com.example.readlightnovel.adapter.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readlightnovel.R;
import com.example.readlightnovel.customview.CircleTextView;
import com.example.readlightnovel.model.comment.Data;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Data> mList;

    public CommentAdapter(List<Data> mList) {
        this.mList = mList;
    }

    public void setData(List<Data> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data item = mList.get(position);

        String s = item.getUserName().substring(0, 2);
        holder.circleTextView.setText(s);
        holder.tvName.setText(item.getUserName());
        holder.tvComment.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleTextView circleTextView;
        private TextView tvName;
        private TextView tvComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleTextView = itemView.findViewById(R.id.ivCircleText);
            tvName = itemView.findViewById(R.id.tvName);
            tvComment = itemView.findViewById(R.id.tvComment);
        }
    }
}

