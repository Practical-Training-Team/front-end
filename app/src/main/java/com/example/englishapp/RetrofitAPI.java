package com.example.englishapp;
import com.example.englishapp.view.Article;
import com.example.englishapp.view.ArticlePage;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitAPI {

//    @GET("GetData/")
//    Call<String> test(@Query("sentence_id") int id, @Query("sort") int sort, @Query("content") int content);

    @GET("get_sentence/")
    Call<List<SentenceBody>> getSentence(@Query("sentence_class")int typeId);

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

    @GET("get_article/")
    Call<List<Article>> getAdviceList(@Query("user_id")int id);

    @GET("log_in/")
    Call<Integer> login(@Query("login_account")String login_account, @Query("login_password") String pwd);

    @GET("get_article_content/")
    Call<ArticlePage> getContent(@Query("article_id") int id);
}
