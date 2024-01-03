package com.example.englishapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.englishapp.NetUtil;
import com.example.englishapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineFragment extends Fragment {

    private ConstraintLayout historyLayout, infoLayout, creditLayout, settingLayout;
    private ImageView history, info, credit, setting;
    private Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        historyLayout = view.findViewById(R.id.mine_history);
        infoLayout = view.findViewById(R.id.mine_info);
        creditLayout = view.findViewById(R.id.mine_credit);
        settingLayout = view.findViewById(R.id.mine_setting);
        history = view.findViewById(R.id.history_next);
        info = view.findViewById(R.id.info_next);
        credit = view.findViewById(R.id.credit_next);
        setting = view.findViewById(R.id.setting_next);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            NetUtil.getInstance().getApi().test(1,2,3).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    System.out.println(response);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        });
    }

}
