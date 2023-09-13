package com.example.readlightnovel.adapter.lightnovel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readlightnovel.R;
import com.example.readlightnovel.fragment.detail.DialogFragmentDetail;
import com.example.readlightnovel.interfaces.OnLightNovelClickListener;
import com.example.readlightnovel.interfaces.OnParentClickListener;
import com.example.readlightnovel.model.uim.LightNovelParent;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LNParentAdapter extends RecyclerView.Adapter<LNParentAdapter.ViewHolder> {

    private Activity mContext;
    private List<LightNovelParent> mListParent;
    private List<Data> mListData;
    private List<Data> fakeList = new ArrayList<>();
    private ChildRecyclerViewAdapter childAdapter;
    private FragmentManager fragmentManager;
    private OnParentClickListener onParentClickListener;


    public LNParentAdapter(List<LightNovelParent> mListParent, Activity mContext, List<Data> mListData, FragmentManager fragmentManager ) {
        this.mListParent = mListParent;
        this.mContext = mContext;
        this.mListData = mListData;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_novel_parent, parent, false);
        return new LNParentAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LightNovelParent currentItem = mListParent.get(position);
        holder.rcv.setHasFixedSize(true);

        holder.tvTitle.setText(currentItem.getCategory());
        childAdapter = new ChildRecyclerViewAdapter();

        holder.tvSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onParentClickListener != null) {
                    onParentClickListener.onClick(position, currentItem.getCategory());
                }
            }
        });

        if (mListParent.get(position).getCategory().equalsIgnoreCase("Shounen")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);

            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Shounen")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }

        if (mListParent.get(position).getCategory().equalsIgnoreCase("Harem")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);

            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Harem")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);

        }

        if (mListParent.get(position).getCategory().equalsIgnoreCase("Comedy")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);

            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Comedy")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);

        }
        if (mListParent.get(position).getCategory().equalsIgnoreCase("Martial Arts")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Martial Arts")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }

        if (mListParent.get(position).getCategory().equalsIgnoreCase("School Life")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("School Life")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }

        if (mListParent.get(position).getCategory().equalsIgnoreCase("Mystery")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Mystery")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }

        if (mListParent.get(position).getCategory().equalsIgnoreCase("Shoujo")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Shoujo")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }

        if (mListParent.get(position).getCategory().equalsIgnoreCase("Romance")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Romance")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }


        if (mListParent.get(position).getCategory().equalsIgnoreCase("Sci-fi")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Sci-fi")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }


        if (mListParent.get(position).getCategory().equalsIgnoreCase("Gender Bender")) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.rcv.setLayoutManager(layoutManager);
            fakeList = mListData.stream().filter(book -> book.getCategory().equalsIgnoreCase("Gender Bender")).collect(Collectors.toList());
            childAdapter.setData(fakeList);
            holder.rcv.setAdapter(childAdapter);
        }


        childAdapter.setOnClickListener(new OnLightNovelClickListener() {
            @Override
            public void onClick(int position, Data item) {
                if (DialogUtils.enableShowDialogFragment(fragmentManager, DialogFragmentDetail.class.getSimpleName())) {
                    new DialogFragmentDetail(item).show(fragmentManager, DialogFragmentDetail.class.getSimpleName());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListParent != null ? mListParent.size() : 0;
    }


    public void setOnClickListener(OnParentClickListener onClickListener) {
        this.onParentClickListener = onClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final RecyclerView rcv;
        private final TextView tvSeeMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            rcv = itemView.findViewById(R.id.rcv);
            tvSeeMore = itemView.findViewById(R.id.tvSeeMore);

        }
    }
}
