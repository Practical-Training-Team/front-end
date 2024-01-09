package com.example.englishapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.databean.AdviceItem;
import com.example.englishapp.databean.Article;
import com.example.englishapp.databean.ArticlePage;
import com.example.englishapp.view.ArticleActivity;

import java.util.List;

public class HotRankAdapter extends RecyclerView.Adapter<HotRankAdapter.HotRankViewHolder> {

    private List<Article> rankItem;
    private Activity activity;

    public HotRankAdapter(List<Article> list, Activity activity) {
        rankItem = list;
        this.activity = activity;
    }
    @NonNull
    @Override
    public HotRankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_rank,parent,false);
        return new HotRankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotRankViewHolder holder, int position) {
        holder.rank.setText(rankItem.get(position).getTitle());
        holder.rank.setOnClickListener(v -> {
            Intent intent = new Intent(activity, ArticleActivity.class);
            intent.putExtra("article_id", rankItem.get(position).getArticle_id());
            activity.startActivity(intent);
        });
        if (position < 2) {
            holder.image.setBackgroundResource(R.mipmap.fire);
        } else {
            holder.image.setBackgroundResource(R.mipmap.point);
            holder.image.setMaxHeight(10);
            holder.image.setMaxWidth(10);
        }
    }

    @Override
    public int getItemCount() {
        return rankItem.size();
    }

    public class HotRankViewHolder extends RecyclerView.ViewHolder {
        private TextView rank;
        private ImageView image;
        public HotRankViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.hot_tv);
            image = itemView.findViewById(R.id.hot_im);
        }
    }
}
