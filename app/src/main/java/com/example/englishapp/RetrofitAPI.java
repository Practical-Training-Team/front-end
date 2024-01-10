package com.example.englishapp;

import com.example.englishapp.databean.Article;
import com.example.englishapp.databean.History;
import com.example.englishapp.databean.Result;
import com.example.englishapp.databean.Sentence;

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

    @GET("score/sentence/")
    Call<List<Sentence>> getSentence(@Query("sentence_cate")int typeId);

    @Multipart
    @POST("upload_audio/upload/audio/")
    Call<ResponseBody> uploadFile(@Part("id")int id,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part audioFile
    );

    @GET("articles/article/")
    Call<List<Article>> getAdviceList(@Query("user_id")int id);

    @GET("signupsignin/signin/")
    Call<Integer> login(@Query("login_account")String login_account, @Query("login_password") String pwd);

    @GET("articles/singlearticle/")
    Call<Article> getContent(@Query("article_id") int id);

    @GET("signupsignin/signup/")
    Call<Integer> register(@Query("password") String password, @Query("account") String account);

    @GET("articles/articlepopularitylist/")
    Call<List<Article>> getSearchHotList();

//    @GET("Edit_personal_information/")
//    Call<String> changeInfo(@Query("user_id") int id, @Query("name") String name,
//                      @Query("class") String uClass, @Query("faculty") String faculty);

//    @GET("audio_api/get_audio/")
//    Call<ResponseBody> getAudio(@Query("sentence") String sentence);

    @GET("articles/search/")
    Call<List<Article>> search(@Query("keywords") String keywords);

    @GET("articles/likes/")
    Call<Integer> thumb(@Query("user_id") int uid, @Query("article_id") int aid);

    @GET("score/analysis/?url=http://10.135.116.43:8080/create_audios/1.mp3")
    Call<Result> getResult();

    @GET("userfunction/history/")
    Call<List<History>> getHistory(@Query("user_id") int id);


}
