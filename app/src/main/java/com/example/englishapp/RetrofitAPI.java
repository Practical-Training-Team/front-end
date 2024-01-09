package com.example.englishapp;

import com.example.englishapp.databean.AdviceItem;
import com.example.englishapp.databean.Article;
import com.example.englishapp.databean.ArticlePage;
import com.example.englishapp.databean.SentenceBody;

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

    @GET("get_sentence/")
    Call<List<SentenceBody>> getSentence(@Query("sentence_class")int typeId);

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

    @GET("articles/article/")
    Call<List<Article>> getAdviceList(@Query("user_id")int id);

    @GET("signupsignin/signin/")
    Call<Integer> login(@Query("login_account")String login_account, @Query("login_password") String pwd);

    @GET("articles/singlearticle/")
    Call<Article> getContent(@Query("article_id") int id);

    @GET("signupsignin/signup/")
    Call<Integer> register(@Query("passeord") String password, @Query("account") String account);

    @GET("articles/articlepopularitylist/")
    Call<List<Article>> getSearchHotList();

    @GET("Edit_personal_information/")
    Call<String> changeInfo(@Query("user_id") int id, @Query("name") String name,
                      @Query("class") String uClass, @Query("faculty") String faculty);

    @GET("audio_api/get_audio/")
    Call<ResponseBody> getAudio(@Query("sentence") String sentence);

    @GET("articles/search/")
    Call<List<Article>> search(@Query("keywords") String keywords);
}
