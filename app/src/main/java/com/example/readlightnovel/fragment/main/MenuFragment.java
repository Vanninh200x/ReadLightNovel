package com.example.readlightnovel.fragment.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.uia.MenuAdapter;
import com.example.readlightnovel.customview.CircleTextView;
import com.example.readlightnovel.databinding.FragmentMenuBinding;
import com.example.readlightnovel.fragment.sub.DialogSignInFragment;
import com.example.readlightnovel.callback.CallBackUser;
import com.example.readlightnovel.model.uim.ItemMenu;
import com.example.readlightnovel.model.user.Data;
import com.example.readlightnovel.utils.desa.DialogUtils;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment implements CallBackUser {
    private final List<ItemMenu> mList = new ArrayList<>();
    private MenuAdapter menuAdapter;
    private FragmentMenuBinding binding;
    private String userName;
    private CircleTextView circleTextView;
    private ImageView imageView;
    private ViewGroup parentLayout;
    private int index;
    private String subString;

    public MenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        init();
        setView();
        showRecyclerView();
        initListener();
        return view;
    }

    @SuppressLint("ResourceType")
    private void init() {

        binding.header.tvTitle.setTextColor(Color.BLACK);
        binding.header.tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        userName = Hawk.get("user_name");
        binding.tvSignUpIn.setText((userName) == null ? getString(R.string.sign_up_in) : userName);

        binding.tvLogout.setVisibility((userName) == null ? View.INVISIBLE : View.VISIBLE);
        binding.tvSignUpIn.setTextColor(Color.WHITE);
        binding.tvSignUpIn.setTypeface(null, Typeface.ITALIC);
        binding.tvVersion.setTextColor(Color.BLACK);

        parentLayout = (ViewGroup) binding.imgUser.getParent();
        index = parentLayout.indexOfChild(binding.imgUser);
    }

    private void setView() {
        parentLayout.removeView(binding.imgUser);

        if (Hawk.get("user_name") == null) {
            setNoAvt();
        } else {
            circleTextView = new CircleTextView(getActivity());
            subString = Hawk.get("user_name").toString().substring(0, 2);
            circleTextView.setText(subString);
            circleTextView.setLayoutParams(binding.imgUser.getLayoutParams());
            circleTextView.setId(binding.imgUser.getId());
            parentLayout.removeView(binding.imgUser);
            parentLayout.addView(circleTextView, index);
        }
    }

    private void setNoAvt() {
        imageView = new ImageView(binding.imgUser.getContext());
        imageView.setLayoutParams(binding.imgUser.getLayoutParams());
        imageView.setImageResource(R.drawable.baseline_person_pin_24);
        imageView.setId(binding.imgUser.getId());
        int index = parentLayout.indexOfChild(binding.imgUser);
        parentLayout.removeView(circleTextView);
        parentLayout.addView(imageView, index);
    }

    private void initListener() {
        binding.header.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CHECK-ACCESS_TOKEN", Hawk.get("access_token"));
            }
        });
        menuAdapter.setOnClickListener((position, item) -> clickItemMenu(position));

        binding.tvSignUpIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Hawk.get("user_name") == null) {
                    if (DialogUtils.enableShowDialogFragment(getActivity().getSupportFragmentManager(), DialogSignInFragment.class.getSimpleName())) {
                        new DialogSignInFragment(MenuFragment.this::onCallBack).show(getActivity().getSupportFragmentManager(), DialogSignInFragment.class.getSimpleName());
                    }
                }
            }
        });

        binding.tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.delete("user_name");
                Hawk.delete("user_id");
                Hawk.delete("avt_url");
                userName = Hawk.get("user_name");
                setNoAvt();
                binding.tvSignUpIn.setText((userName) == null ? getString(R.string.sign_up_in) : userName);
                binding.tvLogout.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void showRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        binding.rcv.addItemDecoration(decoration);
        menuAdapter = new MenuAdapter(addList(), getContext());
        binding.rcv.setAdapter(menuAdapter);
    }


    private List<ItemMenu> addList() {
        mList.add(new ItemMenu(R.drawable.baseline_star_24, getString(R.string.review_rating)));
        mList.add(new ItemMenu(R.drawable.baseline_share_24, getString(R.string.share_to_friend)));
        mList.add(new ItemMenu(R.drawable.baseline_chat_bubble_24, getString(R.string.suggest_story)));
        mList.add(new ItemMenu(R.drawable.baseline_bug_report_24, getString(R.string.bug_report)));
        mList.add(new ItemMenu(R.drawable.baseline_term_conditions, getString(R.string.terms_conditions)));
        mList.add(new ItemMenu(R.drawable.baseline_report_24, getString(R.string.policy)));
        return mList;
    }

    private void clickItemMenu(int position) {
        Intent browserIntent;
        switch (position) {
            case 0:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=PackageName")));
                break;
            case 1:
                ShareCompat.IntentBuilder.from(getActivity()).setType("text/plain").setChooserTitle("Chooser title").setText("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName()).startChooser();
                break;
            case 2:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tk-team.glitch.me/terms-conditions.html"));
                startActivity(browserIntent);
                break;
            case 3:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tk-team.glitch.me/terms-conditions.html"));
                startActivity(browserIntent);
                break;
            case 4:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tk-team.glitch.me/terms-conditions.html"));
                startActivity(browserIntent);
                break;
            case 5:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tk-team.glitch.me/policy.html"));
                startActivity(browserIntent);
                break;
        }
    }

    @Override
    public void onCallBack(Data user) {
        //TODO update
        binding.tvSignUpIn.setText(user.getUsername());
        binding.tvLogout.setVisibility(View.VISIBLE);
        Hawk.put("user_name", user.getUsername());
        Hawk.put("user_id", user.getId());
        Hawk.put("avt_url", user.getAvatar_url());

        circleTextView = new CircleTextView(getActivity());
        subString = Hawk.get("user_name").toString().substring(0, 2);
        circleTextView.setText(subString);
        circleTextView.setLayoutParams(binding.imgUser.getLayoutParams());
        circleTextView.setId(binding.imgUser.getId());
        parentLayout.removeView(imageView);
        parentLayout.addView(circleTextView, index);
    }
}