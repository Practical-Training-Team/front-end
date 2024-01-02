package com.example.englishapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.view.Type;

import java.util.ArrayList;
import java.util.List;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {

    private List<Type> list ;

    public PracticeAdapter(List<Type> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice_clarify,parent,false);
        return new PracticeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageBitmap(list.get(position).getBitmap());
        holder.description.setText(list.get(position).getDescription());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView description;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.type_im);
            description = itemView.findViewById(R.id.type_des);
            name = itemView.findViewById(R.id.type_name);
        }
    }
}
