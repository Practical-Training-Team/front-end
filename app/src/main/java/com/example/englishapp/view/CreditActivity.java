package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.Credit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditActivity extends AppCompatActivity {
    private ImageView article_rank1, article_rank2, article_rank3;
    private ImageView sentence_rank1, sentence_rank2, sentence_rank3;
    private ImageView check_in_rank1, check_in_rank2, check_in_rank3;
    private ImageView january, february, march, april, may, june, july, august, september, october, november, december;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_credit);
        initView();
        initData();
    }

    private void initView() {
        article_rank1 = findViewById(R.id.book1);
        article_rank2 = findViewById(R.id.book2);
        article_rank3 = findViewById(R.id.book3);
        sentence_rank1 = findViewById(R.id.sentence1);
        sentence_rank2 = findViewById(R.id.sentence2);
        sentence_rank3 = findViewById(R.id.sentence3);
        check_in_rank1 = findViewById(R.id.punch1);
        check_in_rank2 = findViewById(R.id.punch2);
        check_in_rank3 = findViewById(R.id.punch3);
        january = findViewById(R.id.january);
        february = findViewById(R.id.february);
        march = findViewById(R.id.march);
        april = findViewById(R.id.april);
        may = findViewById(R.id.may);
        june = findViewById(R.id.june);
        july = findViewById(R.id.july);
        august = findViewById(R.id.august);
        september = findViewById(R.id.september);
        october = findViewById(R.id.october);
        november = findViewById(R.id.november);
        december = findViewById(R.id.december);
    }
    private void initData() {
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id = preferences.getInt(KEY_INT_VALUE, 0);
        NetUtil.getInstance().getApi().getCredit(id, 2024).enqueue(new Callback<Credit>() {
            @Override
            public void onResponse(Call<Credit> call, Response<Credit> response) {
                if(response.body() != null) {
                    Credit credit = response.body();
                    if (credit.getArticle_rank1() == 0) article_rank1.setBackgroundResource(R.mipmap.book1_grey);
                    if (credit.getArticle_rank2() == 0) article_rank2.setBackgroundResource(R.mipmap.book2_grey);
                    if (credit.getArticle_rank3() == 0) article_rank3.setBackgroundResource(R.mipmap.book3_grey);
                    if (credit.getCheck_in_rank1() == 0) check_in_rank1.setBackgroundResource(R.mipmap.punch1_grey);
                    if (credit.getCheck_in_rank2() == 0) check_in_rank2.setBackgroundResource(R.mipmap.punch2_grey);
                    if (credit.getCheck_in_rank3() == 0) check_in_rank3.setBackgroundResource(R.mipmap.punch3_grey);
                    if (credit.getSentence_rank1() == 0) sentence_rank1.setBackgroundResource(R.mipmap.sentence1_grey);
                    if (credit.getSentence_rank2() == 0) sentence_rank2.setBackgroundResource(R.mipmap.sentence2_grey);
                    if (credit.getSentence_rank3() == 0) sentence_rank3.setBackgroundResource(R.mipmap.sentence3_grey);
                    if (credit.getJanuary() == 0) january.setBackgroundResource(R.mipmap.january_grey);
                    if (credit.getFebruary() == 0) february.setBackgroundResource(R.mipmap.feburary_grey);
                    if (credit.getMarch() == 0) march.setBackgroundResource(R.mipmap.march_grey);
                    if (credit.getApril() == 0) april.setBackgroundResource(R.mipmap.april_grey);
                    if (credit.getMay() == 0) may.setBackgroundResource(R.mipmap.may_grey);
                    if (credit.getJune() == 0) june.setBackgroundResource(R.mipmap.june_grey);
                    //if (credit.getJuly() == 0)
                        july.setBackgroundResource(R.mipmap.july);
                    //if (credit.getAugust() == 0)
                        august.setBackgroundResource(R.mipmap.august_grey);
                    //if (credit.getSeptember() == 0)
                        september.setBackgroundResource(R.mipmap.september_grey);
                    if (credit.getOctober() == 0) october.setBackgroundResource(R.mipmap.october_grey);
                    if (credit.getNovember() == 0) november.setBackgroundResource(R.mipmap.november_grey);
                    if (credit.getDecember() == 0) december.setBackgroundResource(R.mipmap.december_grey);
                }
            }

            @Override
            public void onFailure(Call<Credit> call, Throwable t) {

            }
        });
    }
}
