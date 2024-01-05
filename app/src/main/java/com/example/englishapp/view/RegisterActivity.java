package com.example.englishapp.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText account;
    private EditText pwd, repwd;
    private Button register;
    private TextView title;
    private String Aname;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_register);

        initView();
        initListener();
    }


    private void initView() {
        account = findViewById(R.id.telnum);
        pwd = findViewById(R.id.reg_pwd);
        repwd = findViewById(R.id.reg_repwd);
        register = findViewById(R.id.register);
        title = findViewById(R.id.reg_title);
    }

    private void initListener() {
        register.setOnClickListener(v->{
            if(account.getText() != null ) {
                String acc = account.getText().toString();
                Aname = acc;
            }else {
                Toast.makeText(this,"请输入账号！", Toast.LENGTH_SHORT).show();
                //return;
            }
            if(pwd.getText() != null) {
                String pwd = account.getText().toString();
            }else {
                Toast.makeText(this,"请输入密码！", Toast.LENGTH_SHORT).show();
                //return;
            }
            if(repwd.getText() != null) {
                String pwd = account.getText().toString();
            }else {
                Toast.makeText(this,"请确认密码！", Toast.LENGTH_SHORT).show();
                //return;
            }
            if (repwd.getText().toString() != null && pwd.getText().toString() != null && !repwd.getText().toString().equals(pwd.getText().toString())) {
                Toast.makeText(this,"两次输入密码不一致！", Toast.LENGTH_SHORT).show();
                //return;
            }else {
                password = pwd.getText().toString();
            }
            if (repwd.getText().toString() != null && pwd.getText().toString() != null && repwd.getText().toString().equals(pwd.getText().toString())) {
                //TODO
            }
        });
    }
}
