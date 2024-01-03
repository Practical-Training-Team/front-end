package com.example.englishapp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("GetData/")
    Call<String> test(@Query("sentence_id") int id, @Query("sort") int sort, @Query("content") int content);

}
