package com.example.englishapp.view;

import static com.youth.banner.util.LogUtils.TAG;

import android.content.Intent;
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

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineFragment extends Fragment {

    private ConstraintLayout historyLayout, infoLayout, creditLayout, settingLayout, punchLayout;
    private ImageView history, info, credit, setting, punch;
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
    }



}


