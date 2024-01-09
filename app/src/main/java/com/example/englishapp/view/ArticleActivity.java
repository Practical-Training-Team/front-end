package com.example.englishapp.view;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.englishapp.databean.Article;
import com.example.englishapp.databean.ArticlePage;
import com.example.englishapp.NetUtil;
import com.example.englishapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleActivity extends AppCompatActivity {

    private TextView title, content, thumbNum, readNum, time;
    private ImageView thumb, back, picture;
    Article articlePage;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_text_page);
        int id = getIntent().getIntExtra("article_id", -1);
        initView();
        initData(id);
    }

    private void initView() {
        title = findViewById(R.id.article_title1);
        picture = findViewById(R.id.article_im);
        content = findViewById(R.id.article_content);
        thumbNum = findViewById(R.id.article_thumb_num);
        thumb = findViewById(R.id.article_thumb);
        readNum = findViewById(R.id.article_read);
        time = findViewById(R.id.time);
        back = findViewById(R.id.article_back);
        back.setOnClickListener( v -> finish());
    }

    private void initData(int id) {
        NetUtil.getInstance().getApi().getContent(id).enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                articlePage = response.body();
                title.setText(articlePage.getTitle());
                content.setText(articlePage.getContent());
                thumbNum.setText("点赞数: "+articlePage.getLikes());
                readNum.setText("阅读数： "+articlePage.getPage_view());
                time.setText("发布时间："+ articlePage.getRelease_time());
                Uri uri = Uri.parse(response.body().getImage());
                Glide.with(ArticleActivity.this).load(uri).into(picture);
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {

            }
        });
    }
}
