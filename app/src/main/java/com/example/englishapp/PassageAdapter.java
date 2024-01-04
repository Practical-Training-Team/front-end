package com.example.englishapp;

import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.List;

public class PassageAdapter extends RecyclerView.Adapter<PassageAdapter.ViewHolder> {
    private List<Sentence> list;
    private List<SentenceBody> list_test;
    private static final String TAG = "test test test";

    private String language = "en_us";
    // 评测题型
    private String category = "read_sentence";
    // 结果等级
    private String result_level;

    private String mLastResult;
    private final static String PREFER_NAME = "ise_settings";
    private final static int REQUEST_CODE_SETTINGS = 1;

    public PassageAdapter(List<SentenceBody> list_test) {
        this.list_test = list_test;
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
            holder.Read.setBackgroundResource(R.drawable.baseline_pause_circle_filled_24);

        });
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

}
