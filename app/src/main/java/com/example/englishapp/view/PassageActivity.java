package com.example.englishapp.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.NetUtil;
import com.example.englishapp.PassageAdapter;
import com.example.englishapp.R;
import com.example.englishapp.databean.Sentence;

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
    private List<Sentence> list = new ArrayList<>();
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_practice);
        id = getIntent().getIntExtra("type", -1);
        initView(id);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }

    private void initView(int id) {
        title = findViewById(R.id.type_tv);
        switch (id){
            case 0:
                title.setText("养生健康");break;
            case 1:
                title.setText("旅行见闻");break;
            case 2:
                title.setText("社会见闻");break;
            case 3:
                title.setText("国防军事");break;
            case 4:
                title.setText("创新思维");break;
            case 5:
                title.setText("生态环境");break;

        }
        back = findViewById(R.id.practice_back);
        back.setOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.sentence_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NetUtil.getInstance().getApi().getSentence(id).enqueue(new Callback<List<Sentence>>() {
            @Override
            public void onResponse(Call<List<Sentence>> call, Response<List<Sentence>> response) {

                assert response.body() != null;
                list.addAll(response.body());
                recyclerView.setAdapter(new PassageAdapter(list, PassageActivity.this));
            }

            @Override
            public void onFailure(Call<List<Sentence>> call, Throwable t) {
                Log.d("get_sentence", "onFailure: ");
            }
        });
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE};
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, 200);
                    return;
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && requestCode == 200) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivityForResult(intent, 200);
                    return;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200) {
            checkPermission();
        }
    }

}
