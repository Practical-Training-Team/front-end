package com.example.englishapp.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.NetUtil;
import com.example.englishapp.PassageAdapter;
import com.example.englishapp.R;
import com.example.englishapp.SentenceBody;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassageActivity extends AppCompatActivity {

    private TextView title;
    private ImageView back;
    private RecyclerView recyclerView;
    private int id = -1;
    private List<SentenceBody> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_practice);
        id = getIntent().getIntExtra("type", -1);
        initView(id);

    }

    private void initView(int id) {
        title = findViewById(R.id.type_tv);
        switch (id){
            case 0:
                title.setText("日常生活");break;
            case 1:
                title.setText("旅行见闻");break;
            case 2:
                title.setText("影音娱乐");break;
            case 3:
                title.setText("网络科技");break;
            case 4:
                title.setText("商业职场");break;
            case 5:
                title.setText("体育运动");break;

        }
        back = findViewById(R.id.practice_back);
        back.setOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.sentence_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NetUtil.getInstance().getApi().getSentence(id).enqueue(new Callback<List<SentenceBody>>() {
            @Override
            public void onResponse(Call<List<SentenceBody>> call, Response<List<SentenceBody>> response) {

                assert response.body() != null;
                list.addAll(response.body());
                recyclerView.setAdapter(new PassageAdapter(list));
            }

            @Override
            public void onFailure(Call<List<SentenceBody>> call, Throwable t) {
                Log.d("get_sentence", "onFailure: ");
            }
        });

    }

}
