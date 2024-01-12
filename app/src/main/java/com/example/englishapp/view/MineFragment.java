package com.example.englishapp.view;

import static com.example.englishapp.view.LoginActivity.KEY_INT_VALUE;
import static com.example.englishapp.view.SearchActivity.PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.englishapp.NetUtil;
import com.example.englishapp.R;
import com.example.englishapp.databean.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineFragment extends Fragment {

    private ConstraintLayout historyLayout, infoLayout, creditLayout, settingLayout, punchLayout;
    private ImageView history, info, credit, setting, punch;
    private TextView name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        getName();
        return view;
    }

    private void initView(View view) {
        historyLayout = view.findViewById(R.id.mine_history);
        historyLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), RankActivity.class);
            startActivity(intent);
        });
        infoLayout = view.findViewById(R.id.mine_info);
        infoLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);
        });
        creditLayout = view.findViewById(R.id.mine_credit);
        settingLayout = view.findViewById(R.id.mine_setting);
        history = view.findViewById(R.id.history_next);
        history.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), RankActivity.class);
            startActivity(intent);
        });
        info = view.findViewById(R.id.info_next);
        info.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);
        });
        credit = view.findViewById(R.id.credit_next);
        credit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreditActivity.class);
            startActivity(intent);
        });
        creditLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CreditActivity.class);
            startActivity(intent);
        });
        setting = view.findViewById(R.id.setting_next);
        punchLayout = view.findViewById(R.id.mine_punch);
        punch = view.findViewById(R.id.punch_next);
        punchLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PunchActivity.class);
            startActivity(intent);
        });
        punch.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), PunchActivity.class);
            startActivity(intent);
        });
        name = view.findViewById(R.id.name);
    }

    private void getName() {
        SharedPreferences preferences = getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int id =  preferences.getInt(KEY_INT_VALUE, 0);
        NetUtil.getInstance().getApi().getInfo(id).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.body() != null) {
                    name.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getName();
    }



}


