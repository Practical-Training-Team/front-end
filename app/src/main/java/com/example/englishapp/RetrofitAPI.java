package com.example.englishapp;

import com.example.englishapp.databean.Article;
import com.example.englishapp.databean.ArticlePage;
import com.example.englishapp.databean.Credit;
import com.example.englishapp.databean.History;
import com.example.englishapp.databean.MyPicture;
import com.example.englishapp.databean.RankItem;
import com.example.englishapp.databean.Result;
import com.example.englishapp.databean.Sentence;
import com.example.englishapp.databean.UserInfo;

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

//    @Multipart
//    @POST("upload_audio/upload/audio/")
//    Call<ResponseBody> uploadFile(@Part("id")int id,
//            @Part("description") RequestBody description,
//            @Part MultipartBody.Part audioFile
//    );

    @GET("articles/article/")
    Call<List<Article>> getAdviceList(@Query("user_id")int id);

    @GET("signupsignin/signin/")
    Call<Integer> login(@Query("login_account")String login_account, @Query("login_password") String pwd);

    @GET("articles/singlearticle/?article_id=3&category_id=0&user_id=1")
    Call<ArticlePage> getContent();
            //(@Query("article_id") int id, @Query("category_id") int cid, @Query("user_id") int uid);

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

    @GET("score/analysis/")
    Call<Result> getResult(@Query("user_id") int uid, @Query("sentence_id") int sid, @Query("b_audio") String audio);

    @GET("userfunction/history/")
    Call<List<History>> getHistory(@Query("user_id") int id);

    @GET("userfunction/checkin/")
    Call<Integer> checkIn(@Query("user_id") int id, @Query("date_check_in") String date);

    @GET("userfunction/activity_ranking")
    Call<List<RankItem>> getRank();

    @GET("userfunction/rake_user_info/")
    Call<RankItem> getUserRank(@Query("user_id") int id);

    @GET("userfunction/honor_rank/")
    Call<Credit> getCredit(@Query("user_id") int id, @Query("year") int year);

    @GET("articles/analysis/")
    Call<Result> getPageResult(@Query("user_id") int uid, @Query("article_id") int aid, @Query("paragraph_i")int pid, @Query("b_audio") String audio);

    @GET("userfunction/update_user_info/")
    Call<Integer> putInfo(@Query("user_id")int id, @Query("user_name") String name, @Query("user_gender") String gender, @Query("user_class") String uclass,
                          @Query("user_faculty") String faculty, @Query("user_grade") String grade);

    @GET("userfunction/return_user_info_personal/")
    Call<UserInfo> getInfo(@Query("user_id") int id);

    @GET("userfunction/picture_personal/")
    Call<MyPicture> getPic(@Query("user_id") int id);
}
