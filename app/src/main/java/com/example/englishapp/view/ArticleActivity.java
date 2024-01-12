package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.controller.ContentAdapter;
import com.example.englishapp.databean.ArticlePage;
import com.example.englishapp.NetUtil;
import com.example.englishapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    private TextView title, content, thumbNum, readNum, time;
    private ImageView thumb, back, picture;
    ArticlePage articlePage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_text_page);
        int id = getIntent().getIntExtra("article_id", -1);
        int cid = getIntent().getIntExtra("category_id", -1);
        initView(id);
        initData(id,cid);
    }

    private void initView(int id) {
        title = findViewById(R.id.article_title1);
        picture = findViewById(R.id.article_im);
        recyclerView = findViewById(R.id.article_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        thumbNum = findViewById(R.id.article_thumb_num);
        thumb = findViewById(R.id.article_thumb);
        thumb.setOnClickListener(v -> addThumb(id));
        readNum = findViewById(R.id.article_read);
        time = findViewById(R.id.time);
        back = findViewById(R.id.article_back);
        back.setOnClickListener( v -> finish());

    }

    private void addThumb(int id) {
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int uid =  preferences.getInt(KEY_INT_VALUE, 0);
        NetUtil.getInstance().getApi().thumb(uid, id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    successful();
                } else if (response.body() == -1) {
                    failed();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void failed() {
        Toast.makeText(this, "这篇文章已经点过赞了哦！" , Toast.LENGTH_SHORT).show();
    }

    private void successful() {
        Toast.makeText(this, "点赞成功！" , Toast.LENGTH_SHORT).show();
        thumbNum.setText("点赞数: "+articlePage.getLikes()+1+"");
    }

    private void initData(int id, int cid) {
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int uid =  preferences.getInt(KEY_INT_VALUE, 0);
        NetUtil.getInstance().getApi().getContent().enqueue(new Callback<ArticlePage>() {
            @Override
            public void onResponse(Call<ArticlePage> call, Response<ArticlePage> response) {
                articlePage = response.body();
                title.setText(articlePage.getTitle());
                thumbNum.setText("点赞数: "+articlePage.getLikes());
                readNum.setText("阅读数： "+articlePage.getPage_view());
                time.setText("发布时间："+ articlePage.getRelease_time().substring(0,9) + articlePage.getRelease_time().substring(11,18));
                recyclerView.setAdapter(new ContentAdapter(response.body().getContent(), ArticleActivity.this, articlePage.getArticle_id()));
                Uri uri = Uri.parse(response.body().getImage());
                Glide.with(ArticleActivity.this).load(uri).into(picture);
            }

            @Override
            public void onFailure(Call<ArticlePage> call, Throwable t) {

            }
        });
    }
}
