package com.example.readlightnovel.api;


import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;

import com.example.readlightnovel.R;
import com.example.readlightnovel.adapter.detail.CommentAdapter;
import com.example.readlightnovel.adapter.lightnovel.LNAdapter;
import com.example.readlightnovel.adapter.lightnovel.LNParentAdapter;
import com.example.readlightnovel.dialog.DialogLoading;
import com.example.readlightnovel.callback.CallBackUser;
import com.example.readlightnovel.model.comment.Comment;
import com.example.readlightnovel.model.comment.add.AddCmt;
import com.example.readlightnovel.model.comment.add.RequestComment;
import com.example.readlightnovel.model.find.Email;
import com.example.readlightnovel.model.rate.PickRate;
import com.example.readlightnovel.model.rate.Rate;
import com.example.readlightnovel.model.uim.YourBookRequest;
import com.example.readlightnovel.model.chapter.Chapter;
import com.example.readlightnovel.model.comic.Comic;
import com.example.readlightnovel.model.comic.Data;
import com.example.readlightnovel.model.signin.Login;
import com.example.readlightnovel.model.signin.User;
import com.example.readlightnovel.model.signup.Account;
import com.example.readlightnovel.model.signup.SignUp;
import com.example.readlightnovel.model.user.DataUser;
import com.orhanobut.hawk.Hawk;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestAPI {
// 192.168.137.1
    private static final String URL = "http://192.168.137.1:8080/api/";
//192.168.159.1
    public static void postRequestLogin(String account, String password, TextView tv, Dialog dialog, CallBackUser myCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        User user = new User(account, password);

        Call<Login> call = retrofitAPI.callLogin(user);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    getWhoAmI(response.body().getDataLogin().getAccessToken(), myCallBack);
                    Hawk.put("access_token", response.body().getDataLogin().getAccessToken());
                    tv.setText(R.string.login_success);
                    dialog.dismiss();
                } else {
                    tv.setText(R.string.wrong_account);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                tv.setText(R.string.connection_errors);
            }
        });
    }

    public static void resetPassword(String account, Activity activity, DialogLoading loading) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Email email = new Email(account);

        Call<DataUser> call = retrofitAPI.callFindEmail(email);
        call.enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(@NonNull Call<DataUser> call, @NonNull Response<DataUser> response) {
                Log.e("CHECK-RESET-PASSWORD", response.code() + "");

                if (response.isSuccessful()) {
                    Log.e("CHECK-RESET-PASSWORD", response.body().toString());
                    loading.dismiss();
                    Toast.makeText(activity, R.string.reset_succes, Toast.LENGTH_SHORT).show();
                } else {
                    loading.dismiss();
                    Toast.makeText(activity, R.string.reset_fail, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                Log.e("CHECK-RESET-PASSWORD", t.getMessage());
                loading.dismiss();
                Toast.makeText(activity, R.string.reset_fail, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void getWhoAmI(String accessToken, CallBackUser myCallBack) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ApiInterceptor(accessToken)) // Replace with your authentication token
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();


        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);

        Call<DataUser> call = retrofitAPI.getWhoAmI();
        call.enqueue(new Callback<DataUser>() {
            @Override
            public void onResponse(Call<DataUser> call, Response<DataUser> response) {
                Log.e("CHECK-API-GET-WHO", response.code() + "");
                if (response.isSuccessful()) {
                    myCallBack.onCallBack(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<DataUser> call, Throwable t) {
                Log.e("CHECK-API-GET-WHO-FAIL", t.getMessage() + "");
            }
        });
    }


    public static void getComicByCategory(LNAdapter adapters, List<Data> mList, String category) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comic> call = retrofitAPI.callComicByCategory(category);
        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {
                    Comic comic1 = response.body();
                    mList.addAll(comic1.getData());
                    adapters.notifyDataSetChanged();

                } else {

                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Log.e("CHECK-DATA", "FAIL-API" + "");
            }
        });
    }

    public static void getComment(CommentAdapter commentAdapter, int bookId, List<com.example.readlightnovel.model.comment.Data> mList) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comment> call = retrofitAPI.callGetComment(bookId);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {
                    Comment comic1 = response.body();
                    mList.addAll(comic1.getData());
                    commentAdapter.notifyDataSetChanged();
                    ;
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e("CHECK-API-RQ-FAILED", t.getMessage() + "");
            }
        });
    }

    public static void getRattingByUser(int userId, int bookId) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);

        Call<Rate> call = retrofitAPI.getUserRatting(userId, bookId);
        call.enqueue(new Callback<Rate>() {
            @Override
            public void onResponse(Call<Rate> call, Response<Rate> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getData() != null) {
                        Hawk.put("user_rate", response.body().getData().getStar());
                    } else {
                        Hawk.put("user_rate", 0L);
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<Rate> call, Throwable t) {

            }
        });
    }

    public static void getComicByUserId(LNAdapter adapters, List<Data> mList, int id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comic> call = retrofitAPI.callComicByUserId(id);
        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {
                    Comic comic1 = response.body();
                    mList.addAll(comic1.getData());
                    System.out.println("API-LIST: " + mList.size());
                    adapters.notifyDataSetChanged();

                } else {

                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Log.e("CHECK-DATA", "FAIL-API" + "");
            }
        });
    }

    public static void getComic(LNParentAdapter adapter, List<Data> mList) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comic> call = retrofitAPI.callComic();

        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                if (response.isSuccessful()) {
                    Comic comic1 = response.body();
                    mList.addAll(comic1.getData());
                    adapter.notifyDataSetChanged();
                } else {
                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Log.e("CHECK-DATA", "FAIL-API" + "");
            }
        });
    }

    public static void getComic(List<Data> mList, int bookId, AppCompatRatingBar ratingBar) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comic> call = retrofitAPI.callComic();

        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                if (response.isSuccessful()) {
                    mList.clear();
                    Comic comic1 = response.body();
                    mList.addAll(comic1.getData().stream().filter(n -> n.getId() == bookId).collect(Collectors.toList()));
                    Log.e("CHECK-SIZE", mList.size() + "--" + mList.get(0).getStar());
                    ratingBar.setRating(mList.get(0).getStar());
                } else {
                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Log.e("CHECK-DATA", "FAIL-API" + "");
            }
        });
    }

    public static void getComicRefresh(List<Data> mList, int bookId, AppCompatRatingBar rattingBar) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comic> call = retrofitAPI.callComic();

        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                if (response.isSuccessful()) {
                    getComic(mList, bookId, rattingBar);

                } else {
                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Log.e("CHECK-DATA", "FAIL-API" + "");
            }
        });
    }

    public static void getComicSearch(LNAdapter adapters, List<Data> mList, String title) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comic> call = retrofitAPI.callComicSearch(title);

        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {
                    Comic comic1 = response.body();
                    mList.addAll(comic1.getData());
                    adapters.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Log.e("CHECK-DATA", "FAIL-API" + "");
            }
        });
    }

    public static void getComicDone(LNAdapter adapters, List<Data> mList) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Comic> call = retrofitAPI.callComicDone();

        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {
                    Comic comic1 = response.body();
                    mList.addAll(comic1.getData());
                    adapters.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Log.e("CHECK-DATA", "FAIL-API" + "");
            }
        });
    }

    public static void getChapter(int bookId, List<com.example.readlightnovel.model.chapter.Data> mList) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Call<Chapter> call = retrofitAPI.getChapter(bookId);

        call.enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                if (response.isSuccessful()) {
                    Chapter chapter = response.body();
                    mList.addAll(chapter.getData());
                } else {

                }
            }

            @Override
            public void onFailure(Call<Chapter> call, Throwable t) {
            }
        });
    }


    public static void postBookRequest(int bookId, int userId) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);

        YourBookRequest request = new YourBookRequest(bookId, userId);
        Call<YourBookRequest> call = retrofitAPI.bookRequest(request);
        call.enqueue(new Callback<YourBookRequest>() {
            @Override
            public void onResponse(Call<YourBookRequest> call, Response<YourBookRequest> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {

                } else {
                    Log.e("CHECK-DATA", "FAIL-DATA" + "");
                }
            }

            @Override
            public void onFailure(Call<YourBookRequest> call, Throwable t) {
                Log.e("CHECK-API", "FAIL-KN");
            }
        });
    }

    public static void postRequestSignUp(String email, String nickName, String password, DialogLoading dialogLoading, Dialog dialog) {
        Log.e("CHECK-REQUEST", "HERE");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        Account user = new Account(email, "null", "null", password, "admin", nickName);

        Call<SignUp> call = retrofitAPI.callRegister(user);
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                Log.e("CHECK-API-RQ", response.code() + "");
                if (response.isSuccessful()) {
                    dialogLoading.dismiss();
                    dialog.dismiss();
                } else {
                    dialogLoading.dismiss();
                    dialog.dismiss();
                    Log.e("CHECK-DATA", "FAIL-DATA" + "");
                }
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Log.e("CHECK-API", "FAIL-KN");
                dialogLoading.dismiss();
                dialog.dismiss();
            }
        });
    }

    public static void postRequestComment(String accessToken, int bookId, String content, CommentAdapter commentAdapter, List<com.example.readlightnovel.model.comment.Data> mListComment) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ApiInterceptor(accessToken)).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);
        RequestComment cmt = new RequestComment(bookId, content);
        Call<AddCmt> call = retrofitAPI.callCommentRequest(cmt);
        call.enqueue(new Callback<AddCmt>() {
            @Override
            public void onResponse(Call<AddCmt> call, Response<AddCmt> response) {
                if (response.isSuccessful()) {
                    mListComment.clear();
                    getComment(commentAdapter, bookId, mListComment);
                } else {
                    Log.e("CHECK-FAIL-DATA", "FAIL");
                }
            }

            @Override
            public void onFailure(Call<AddCmt> call, Throwable t) {
                Log.e("CHECK-FAIL", "FAIL");
            }
        });
    }

    public static void postRequestRating(String accessToken, long bookId, long star, DialogLoading loading) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ApiInterceptor(accessToken)).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceAPI retrofitAPI = retrofit.create(InterfaceAPI.class);

        PickRate pickRate = new PickRate(bookId, star);

        Call<Rate> call = retrofitAPI.callRateRequest(pickRate);
        call.enqueue(new Callback<Rate>() {
            @Override
            public void onResponse(Call<Rate> call, Response<Rate> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                } else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Rate> call, Throwable t) {
                loading.dismiss();
                Log.e("CHECK-API-FAIL", "FAIL-DATA" + "");
            }
        });
    }


}
