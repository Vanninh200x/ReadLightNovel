package com.example.readlightnovel.api;

import com.example.readlightnovel.model.comment.Comment;
import com.example.readlightnovel.model.comment.add.AddCmt;
import com.example.readlightnovel.model.comment.add.RequestComment;
import com.example.readlightnovel.model.find.Email;
import com.example.readlightnovel.model.rate.PickRate;
import com.example.readlightnovel.model.rate.Rate;
import com.example.readlightnovel.model.uim.YourBookRequest;
import com.example.readlightnovel.model.chapter.Chapter;
import com.example.readlightnovel.model.comic.Comic;
import com.example.readlightnovel.model.signin.Login;
import com.example.readlightnovel.model.signin.User;
import com.example.readlightnovel.model.signup.Account;
import com.example.readlightnovel.model.signup.SignUp;
import com.example.readlightnovel.model.user.DataUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceAPI {
//    http://localhost:8080/api/books/yourbook



    @GET("auth/whoami")
    Call<DataUser> getWhoAmI();

    @POST("auth/register")
    Call<SignUp> callRegister(@Body Account account);

    //    http://localhost:8080/api/auth/findEmail
    @POST("auth/findEmail")
    Call<DataUser> callFindEmail(@Body Email email);

    @POST("books/yourbook")
    Call<YourBookRequest> bookRequest(@Body YourBookRequest request);

    @POST("auth/login")
    Call<Login> callLogin(@Body User user);

    @POST("rating")
    Call<Rate> callRateRequest(@Body PickRate rate);


    @POST("comment")
    Call<AddCmt> callCommentRequest(@Body RequestComment comment);

    @GET("books")
    Call<Comic> callComic();

    @GET("books/{category}")
    Call<Comic> callComicByCategory(@Path("category") String category);

    @GET("chapters/{id}")
    Call<Chapter> getChapter(@Path("id") int bookId);

    @GET("books/done")
    Call<Comic> callComicDone();

    @GET("books/search/{title}")
    Call<Comic> callComicSearch(@Path("title") String title);

    @GET("books/yourbook/{userId}")
    Call<Comic> callComicByUserId(@Path("userId") int id);

    @GET("rating/{userId}/{bookId}")
    Call<Rate> getUserRatting(@Path("userId") int userId, @Path(("bookId")) int bookId);

    @GET("comment/{bookId}")
    Call<Comment> callGetComment(@Path(("bookId")) int bookId);


}

