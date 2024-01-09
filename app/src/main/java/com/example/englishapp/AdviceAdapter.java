package com.example.englishapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.databean.Article;
import com.example.englishapp.view.ArticleActivity;

import java.util.List;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.ViewHolder> {
    private List<Article> list;
    private Activity activity;

    public AdviceAdapter(List<Article> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdviceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advice,parent,false);
        return new AdviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceAdapter.ViewHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getImage());
        holder.title.setText(list.get(position).getTitle());
        holder.type.setText(list.get(position).getCategory());
        Glide.with(activity).load(uri).into(holder.image);
        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(activity, ArticleActivity.class);
            intent.putExtra("article_id", list.get(position).getArticle_id());
            Log.d("articleid" , list.get(position).getArticle_id() + "\\");
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title, type;
        private ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.advice_im);
            title = itemView.findViewById(R.id.advice_title);
            type = itemView.findViewById(R.id.advice_type);
            layout = itemView.findViewById(R.id.advice_layout);
        }
    }
}
