package com.example.englishapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.AdviceAdapter;
import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    String keywords;
    private List<Article> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_result);
        recyclerView = findViewById(R.id.result_rv);
        keywords = String.valueOf(getIntent().getStringExtra("keywords"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search(keywords);
    }

    private void search(String query) {
        NetUtil.getInstance().getApi().search(query).enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                list.addAll(response.body());
                recyclerView.setAdapter(new AdviceAdapter(list, SearchResultActivity.this));
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {

            }
        });
    }
}
