package com.example.englishapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.NetUtil;
import com.example.englishapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText account;
    private EditText pwd;
    private Button login;
    private TextView register;
    private TextView title;
    private TextView changePwd;
    private String passwd;
    private String acc;

    private static int id = -1;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        initView();
        initListener();
    }

    private void initView() {
        account = findViewById(R.id.login_acc);
        pwd = findViewById(R.id.login_pwd);
        login = findViewById(R.id.login);
        register = findViewById(R.id.login_register);
        title = findViewById(R.id.log_title);
    }

    private void initListener() {
        login.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            if(account.getText() != null ) {
                acc = account.getText().toString();
            }else {
                Toast.makeText(this,"请输入账号！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(pwd.getText() != null) {
                passwd = pwd.getText().toString();
            }else {
                Toast.makeText(this,"请输入密码！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(acc != null && passwd != null) {
                NetUtil.getInstance().getApi().login(acc, passwd).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body() == -1) {
                            failed();
                        } else if (response.body() != -1) {
                            successful(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }

        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
    private void failed() {
        Toast.makeText(this, "失败！请检查输入的账号和密码！", Toast.LENGTH_SHORT).show();
    }

    private void successful(int id) {
        this.id = id;
    }
}
