package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.controller.RankAdapter;
import com.example.englishapp.databean.RankItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankActivity extends AppCompatActivity {

    private TextView num, score;
    private RecyclerView recyclerView;
    private ImageView avatar;
    private List<RankItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_rank);
        initView();
        initList();
    }

    private void initView() {
        num = findViewById(R.id.mine_rank);
        score = findViewById(R.id.mine_score);
        recyclerView = findViewById(R.id.rank_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);
        NetUtil.getInstance().getApi().getUserRank(id).enqueue(new Callback<RankItem>() {
            @Override
            public void onResponse(Call<RankItem> call, Response<RankItem> response) {
                if (response.body() != null) {
                    num.setText(response.body().getRank()+"");
                    score.setText(response.body().getScore()+"");
                }
            }

            @Override
            public void onFailure(Call<RankItem> call, Throwable t) {

            }
        });
    }

    private void initList() {

        NetUtil.getInstance().getApi().getRank().enqueue(new Callback<List<RankItem>>() {
            @Override
            public void onResponse(Call<List<RankItem>> call, Response<List<RankItem>> response) {
                if(response.body() != null) {
                    list.addAll(response.body());
                    recyclerView.setAdapter(new RankAdapter(list));
                }
            }

            @Override
            public void onFailure(Call<List<RankItem>> call, Throwable t) {

            }
        });
    }
}
