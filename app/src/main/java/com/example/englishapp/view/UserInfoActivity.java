package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.MyPicture;
import com.example.englishapp.databean.UserInfo;

import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {

    private EditText name, gender, uClass, grade, faculty;
    private Button button;
    private ImageView pic1, pic2;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_userinfo);
        initView();
        initData();
        initPic();
    }

    private void initView () {
        name = findViewById(R.id.name_info);
        gender = findViewById(R.id.gender_info);
        uClass = findViewById(R.id.class_info);
        grade = findViewById(R.id.grade_info);
        faculty = findViewById(R.id.faculty_info);
        button = findViewById(R.id.commit_button);
        button.setOnClickListener(v -> query());
        pic1 = findViewById(R.id.pic1);
        pic2 = findViewById(R.id.pic2);
    }

    private void query() {
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);
        String uName = String.valueOf(name.getText());
        String uGender = String.valueOf(gender.getText());
        String uClasss = String.valueOf(uClass.getText());
        String uGrade = String.valueOf(grade.getText());
        String uFaculty = String.valueOf(faculty.getText());

        NetUtil.getInstance().getApi().putInfo(id, uName, uGender,uClasss,uFaculty,uGrade).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    successful();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    private void successful() {
        Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT);
    }

    private void initData() {
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);
        NetUtil.getInstance().getApi().getInfo(id).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.body() != null) {
                    UserInfo info = response.body();
                    name.setText(info.getName());
                    gender.setText(info.getGender());
                    uClass.setText(info.getClass_id());
                    faculty.setText(info.getFaculty());
                    grade.setText(info.getGrade());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    private void initPic() {
        Uri uri1 = Uri.parse("http://10.135.116.43:8080/images/5_pie.png");
        Uri uri2 = Uri.parse("http://10.135.116.43:8080/images/5_line.png");
        Glide.with(UserInfoActivity.this).load(uri1).into(pic1);
        Glide.with(UserInfoActivity.this).load(uri2).into(pic2);
        SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);
//        NetUtil.getInstance().getApi().getPic(id).enqueue(new Callback<MyPicture>() {
//            @Override
//            public void onResponse(Call<MyPicture> call, Response<MyPicture> response) {
//                if (response.body() != null) {
//                    Uri uri1 = Uri.parse(response.body().getUrl_pie());
//                    Uri uri2 = Uri.parse(response.body().getUrl_line());
//                    Glide.with(UserInfoActivity.this).load(uri1).into(pic1);
//                    Glide.with(UserInfoActivity.this).load(uri2).into(pic2);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MyPicture> call, Throwable t) {
//
//            }
//        });
    }
}
