package com.example.catalogapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SecondAdapterRecommend extends RecyclerView.Adapter<SecondAdapterRecommend.MyViewHolder> {

    Context context;
    ArrayList<SecondModelRecommend> secondModels;

    public SecondAdapterRecommend(Context context, ArrayList<SecondModelRecommend> secondModels) {
        this.context = context;
        this.secondModels = secondModels;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView star_point,price,kilo;
        Button btn_add_recommend;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_tovsiye);
            star_point = itemView.findViewById(R.id.text_star_point);
            price = itemView.findViewById(R.id.text_price);
            kilo = itemView.findViewById(R.id.text_kilo);
            btn_add_recommend = itemView.findViewById(R.id.btn_add);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tovsiye,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         SecondModelRecommend model = secondModels.get(position);

        Glide.with(context).load(model.getImage()).into(holder.image);
        holder.star_point.setText(model.getStar_text());
        holder.kilo.setText(model.getKilo());
        holder.price.setText(model.getPrice());
        holder.btn_add_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button recommendation add clicked : "+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return secondModels.size();
    }

}