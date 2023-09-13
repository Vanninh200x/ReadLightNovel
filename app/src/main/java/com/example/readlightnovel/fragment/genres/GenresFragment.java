package com.example.readlightnovel.fragment.genres;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.uia.GenresAdapter;
import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.api.RequestAPI;
import com.example.readlightnovel.databinding.FragmentGenresBinding;
import com.example.readlightnovel.interfaces.OnItemGenresClickListener;
import com.example.readlightnovel.model.uim.ItemGenres;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.utils.GridSpacingItemDecoration;
import com.example.readlightnovel.utils.desa.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class GenresFragment extends Fragment {
    private List<ItemGenres> mList = new ArrayList<>();
    private FragmentGenresBinding binding;
    private GenresAdapter genresAdapter;
    private LNAdapter adapter;
    private List<Data> mListData = new ArrayList<>();

    public GenresFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGenresBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init();
        showRecyclerView();
        initListener();

        return view;
    }

    private void initListener() {
        genresAdapter.setOnClickListener(new OnItemGenresClickListener() {
            @Override
            public void onClick(int position, ItemGenres item) {

                if (DialogUtils.enableShowDialogFragment(getActivity().getSupportFragmentManager(), DetailGenres.class.getSimpleName())) {
                    new DetailGenres(item.getTitle(), adapter).show(getActivity().getSupportFragmentManager(), DetailGenres.class.getSimpleName());
                }
            }
        });
    }

    private void showRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcv.setLayoutManager(gridLayoutManager);

        int spanCount = 2;
        int spacing = dpToPx(getContext(), 15);
        boolean includeEdge = true;
        binding.rcv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        genresAdapter = new GenresAdapter(addList());
        binding.rcv.setAdapter(genresAdapter);

    }

    public static int dpToPx(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }

    private void init() {
        binding.header.tvTitle.setTextColor(Color.BLACK);
        binding.header.tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        binding.header.tvTitle.setText(getString(R.string.genres));

        adapter = new LNAdapter();
    }

    private List<ItemGenres> addList() {
        mList.add(new ItemGenres(1, getString(R.string.shounen)));
        mList.add(new ItemGenres(2, getString(R.string.harem)));
        mList.add(new ItemGenres(3, getString(R.string.comedy)));
        mList.add(new ItemGenres(4, getString(R.string.martial_arts)));
        mList.add(new ItemGenres(5, getString(R.string.school_life)));
        mList.add(new ItemGenres(6, getString(R.string.mystery)));
        mList.add(new ItemGenres(7, getString(R.string.shoujo)));
        mList.add(new ItemGenres(8, getString(R.string.romance)));
        mList.add(new ItemGenres(9, getString(R.string.sci_fi)));
        mList.add(new ItemGenres(10, getString(R.string.gender_bender)));
        mList.add(new ItemGenres(11, getString(R.string.mature)));
        mList.add(new ItemGenres(12, getString(R.string.fantasy)));
        mList.add(new ItemGenres(13, getString(R.string.horror)));
        mList.add(new ItemGenres(14, getString(R.string.drama)));
        mList.add(new ItemGenres(15, getString(R.string.tragedy)));
        mList.add(new ItemGenres(16, getString(R.string.supernatural)));
        mList.add(new ItemGenres(17, getString(R.string.ecchi)));
        mList.add(new ItemGenres(18, getString(R.string.xuanhuan)));
        mList.add(new ItemGenres(19, getString(R.string.adventure)));
        mList.add(new ItemGenres(20, getString(R.string.action)));
        mList.add(new ItemGenres(21, getString(R.string.psychological)));
        mList.add(new ItemGenres(22, getString(R.string.xianxia)));
        mList.add(new ItemGenres(23, getString(R.string.wuxia)));
        mList.add(new ItemGenres(24, getString(R.string.historical)));
        mList.add(new ItemGenres(25, getString(R.string.slice_of_life)));
        mList.add(new ItemGenres(26, getString(R.string.seinen)));
        mList.add(new ItemGenres(27, getString(R.string.lolicon)));
        mList.add(new ItemGenres(28, getString(R.string.adult)));
        mList.add(new ItemGenres(29, getString(R.string.josei)));
        mList.add(new ItemGenres(30, getString(R.string.sports)));
        mList.add(new ItemGenres(31, getString(R.string.smut)));
        mList.add(new ItemGenres(32, getString(R.string.mecha)));
        mList.add(new ItemGenres(33, getString(R.string.yaoi)));
        mList.add(new ItemGenres(34, getString(R.string.shounen_ai)));

        return mList;
    }
}