package com.example.englishapp;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtil {
    private final OkHttpClient client;
    private final RetrofitAPI api;
    private final Gson gson;
    private static final String baseUrl = "http://10.135.48.46:8000/";

    private NetUtil() {
        gson = new Gson();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60000, TimeUnit.MILLISECONDS)
                .build();

        api = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build().create(RetrofitAPI.class);
    }

    public Gson getGson() {
        return gson;
    }

    public static NetUtil getInstance() {
        return NetUtilHolder.INSTANCE;
    }

    private static class NetUtilHolder {
        private static final NetUtil INSTANCE = new NetUtil();
    }

    public OkHttpClient getClient() {
        return client;
    }

    public RetrofitAPI getApi() {
        return api;
    }
}
