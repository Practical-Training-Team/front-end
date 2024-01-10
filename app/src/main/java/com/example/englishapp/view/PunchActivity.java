package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.History;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PunchActivity extends AppCompatActivity {
    private List<History> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_punch);
        getList();
    }

    private void getList() {
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);

        NetUtil.getInstance().getApi().getHistory(id).enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if(response.body() != null) {
                    list.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {

            }
        });
    }

}
