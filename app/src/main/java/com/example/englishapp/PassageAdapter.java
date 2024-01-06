package com.example.englishapp;

import android.Manifest;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassageAdapter extends RecyclerView.Adapter<PassageAdapter.ViewHolder> {
    private List<Sentence> list;
    private List<SentenceBody> list_test;
    private List<Boolean> isRecordingList;
    private static final String TAG = "test test test";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    private MediaRecorder mediaRecorder;

    public PassageAdapter(List<SentenceBody> list_test) {
        this.list_test = list_test;
        for(int i = 0 ; i < list_test.size(); i++) {
            isRecordingList.add(false);
        }
    }


    @NonNull
    @Override
    public PassageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice,parent,false);
        return new PassageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassageAdapter.ViewHolder holder, int position) {
        holder.Read.setOnClickListener(v -> {
            onRecordButtonClick(position);
        });
        holder.Play.setOnClickListener(v -> getAudioFile(holder.Content.getText().toString()));
        holder.Content.setText(list_test.get(position).getContent().toString());
        holder.Number.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return list_test.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Content, Number;
        private ImageView Play, Read;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Number = itemView.findViewById(R.id.number_tv);
            Content = itemView.findViewById(R.id.content_tv);
            Play = itemView.findViewById(R.id.play);
            Read = itemView.findViewById(R.id.read);
        }
    }

    private void onRecordButtonClick(int position) {
        boolean isRecording = isRecordingList.get(position);

        if(isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
        isRecordingList.set(position, !isRecording);
        notifyItemChanged(position);
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/recording.3gp");
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void getAudioFile(String sentence) {
        NetUtil.getInstance().getApi().getAudio(sentence).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new SaveFileTask().execute(response.body());
                } else {
                    //Toast.makeText(activity, "下载失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private class SaveFileTask extends AsyncTask<ResponseBody, Void, Void> {

        @Override
        protected Void doInBackground(ResponseBody... responseBodies) {
            try {
                ResponseBody responseBody = responseBodies[0];
                InputStream inputStream = responseBody.byteStream();
                File file = new File(Environment.getExternalStorageDirectory(), "downloaded_file.mp3");
                FileOutputStream outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Toast.makeText(.this, "文件下载成功", Toast.LENGTH_SHORT).show();
        }
    }
}
