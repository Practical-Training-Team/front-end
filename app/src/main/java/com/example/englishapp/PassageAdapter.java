package com.example.englishapp;

import android.Manifest;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.databean.Result;
import com.example.englishapp.databean.Sentence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassageAdapter extends RecyclerView.Adapter<PassageAdapter.ViewHolder> {
    private List<Sentence> list;
    private List<Boolean> isRecordingList = new ArrayList<>();
    private static final String TAG = "testv vvv test test";
    private Activity activity;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    public PassageAdapter(List<Sentence> list_test, Activity activity) {
        this.list = list_test;
        for (int i = 0; i < list_test.size(); i++) {
            isRecordingList.add(false);
        }
        this.activity = activity;
    }


    @NonNull
    @Override
    public PassageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice, parent, false);
        return new PassageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassageAdapter.ViewHolder holder, int position) {
        holder.Read.setOnClickListener(v -> {
            String path = activity.getFilesDir() + "/sample";
            boolean isRecording = isRecordingList.get(position);
            if (isRecording) {
                holder.Read.setBackgroundResource(R.mipmap.mic2);
                stopRecording(path);
            } else {
                try {
                    holder.Read.setBackgroundResource(R.mipmap.stop);
                    startRecording(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            isRecordingList.set(position, !isRecording);
            notifyItemChanged(position);
        });
        holder.Play.setOnClickListener(v -> playMp3(list.get(position).getUrl()));
        holder.Content.setText(list.get(position).getContent().toString());
        holder.Number.setText(String.valueOf(position + 1));
        holder.Result.setOnClickListener(v -> showResult());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Content, Number;
        private ImageView Play, Read, Result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Number = itemView.findViewById(R.id.number_tv);
            Content = itemView.findViewById(R.id.content_tv);
            Play = itemView.findViewById(R.id.play);
            Read = itemView.findViewById(R.id.read);
            Result = itemView.findViewById(R.id.result);
        }
    }

    private void playMp3(String url) {
        mediaPlayer = new MediaPlayer();
        playAudio(url);
    }

    private void playAudio(String url) {
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startRecording(String path) throws IOException {

        File audioFile = new File(path);
        if (!audioFile.exists()) {
            audioFile.mkdirs();
        } else if (!audioFile.isDirectory()) {
            audioFile.delete();
            audioFile.mkdirs();
        }
        //+String.valueOf(list.get(position).getSentence_id()) +timeStamp + ""

        File file = new File(path + "Sample.mp3");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            file.createNewFile();
        }

        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置麦克风
            /*
             * 设置保存输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);

            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//设置音频文件编码格式
            mediaRecorder.setOutputFile(path + "Sample.mp3");
        }
        try {
            mediaRecorder.prepare();  //start之前要先prepare
            mediaRecorder.start();
        } catch (IllegalStateException el) {
            el.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void stopRecording(String path) {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();//停止录音
                mediaRecorder.release();//释放资源
                mediaRecorder = null;
            } catch (Exception exception) {
                mediaRecorder.reset();//重置
                mediaRecorder.release();//释放资源
                mediaRecorder = null;
            }
            Log.d(TAG, "stopRecording: 1233456");
        }
        uploadAudio(new File(path + "Sample.mp3"));
    }

    private void uploadAudio(File audioFile) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("audio/*"), audioFile);
        MultipartBody.Part audioPart = MultipartBody.Part.createFormData("audio", audioFile.getName(), requestBody);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "Audio description");

        NetUtil.getInstance().getApi().uploadFile(1, description, audioPart).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void showResult() {
        final Result[] result = {new Result()};

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(activity, R.layout.popwindow_result, null));
        dialog.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.popwindow_result);
        ImageView res = window.findViewById(R.id.score);

        NetUtil.getInstance().getApi().getResult().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                result[0] = response.body();
                Glide.with(activity).load(result[0].getUrl()).into(res);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
