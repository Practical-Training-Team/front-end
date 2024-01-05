package com.example.englishapp.view;

import static com.youth.banner.util.LogUtils.TAG;

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
        //button = view.findViewById(R.id.button);
//        button.setOnClickListener(v -> {
//            uploadFile();
//        });
    }


    private void uploadFile() {
        // Replace with your file and description
        File audioFile = new File("/data/data/front-end/test.m4a");
        String descriptionString = "Audio file description";

        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), descriptionString);
        RequestBody audioFileRequestBody = RequestBody.create(MediaType.parse("audio/*"), audioFile);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("audioFile", audioFile.getName(), audioFileRequestBody);

        NetUtil.getInstance().getApi().uploadFile(description, filePart).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: 1");
                    // File uploaded successfully
                    // Handle the response
                } else {
                    // File upload failed
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Request failed
                Log.d(TAG, "onFailure: 2");
                // Handle the failure2
            }
        });
    }
}


