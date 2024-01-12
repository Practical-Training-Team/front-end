package com.example.englishapp.controller;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.databean.RankItem;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private List<RankItem> list;

    public RankAdapter(List<RankItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank,parent,false);
        return new RankAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder holder, int position) {
        holder.num.setText(list.get(position).getRank()+"");
        holder.score.setText(list.get(position).getScore()+"");
        holder.name.setText(list.get(position).getName());
        if(position == 0) {
            holder.image.setBackgroundResource(R.mipmap.avatar);
        } else if (position == 1) {
            holder.image.setBackgroundResource(R.mipmap.avatar1);
        }else if (position == 2) {
            holder.image.setBackgroundResource(R.mipmap.avatar2);
        }else if (position == 3) {
            holder.image.setBackgroundResource(R.mipmap.avatar3);
        }else if (position == 4) {
            holder.image.setBackgroundResource(R.mipmap.avatar4);
        }else if (position == 5) {
            holder.image.setBackgroundResource(R.mipmap.avatar5);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView num, score, name;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.rank);
            score = itemView.findViewById(R.id.rank_score);
            name = itemView.findViewById(R.id.rank_name);
            image = itemView.findViewById(R.id.rank_avatar);
        }
    }
}
