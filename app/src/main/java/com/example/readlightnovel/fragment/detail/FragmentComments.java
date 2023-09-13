package com.example.readlightnovel.fragment.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.readlightnovel.adapter.detail.CommentAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.FragmentCommentsBinding;
import com.example.readlightnovel.model.comment.Data;
import com.orhanobut.hawk.Hawk;

import java.util.List;

public class FragmentComments extends Fragment {
    private FragmentCommentsBinding binding;
    private List<Data> mListComment;
    private CommentAdapter commentAdapter;
    private com.example.readlightnovel.model.comic.Data data;

    public FragmentComments(List<Data> mListComment, CommentAdapter commentAdapter, com.example.readlightnovel.model.comic.Data data) {
        this.mListComment = mListComment;
        this.commentAdapter = commentAdapter;
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommentsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();
        showRecyclerView();
        initListener();
        return view;
    }

    private void showRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);
        binding.rcv.setAdapter(commentAdapter);
    }

    private void initView() {

    }

    private void initListener() {
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = binding.tvComment.getText().toString();
                if (Hawk.get("user_name") != null) {
                    if (s.length() > 1) {
                        RequestAPI.postRequestComment(Hawk.get("access_token"), data.getId(), s, commentAdapter, mListComment);
                    }
                } else {
                    Toast.makeText(getActivity(), "Đề nghị đăng nhập!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}