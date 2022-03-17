package com.example.catalogapp.adapters;

import static com.example.catalogapp.Util.roundFloatNumber;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogapp.R;
import com.example.catalogapp.model.SecondModelShow;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    Context context;
    ArrayList<SecondModelShow> secondModels;

    public FavoriteAdapter(Context context, ArrayList<SecondModelShow> secondModels) {
        this.context = context;
        this.secondModels = secondModels;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text_content_show,text_price_show,text_title_show,_new,star_point;
        ImageView image_show;
        CardView cardView_new;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_content_show = itemView.findViewById(R.id.text_content_show);
            text_price_show = itemView.findViewById(R.id.text_price_show);
            text_title_show = itemView.findViewById(R.id.text_kilo_show);
            star_point = itemView.findViewById(R.id.text_star_point_show);
            _new = itemView.findViewById(R.id.text_detail_new);
            image_show = itemView.findViewById(R.id.image_show);
            cardView_new = itemView.findViewById(R.id.cardView_show);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_show,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SecondModelShow model = secondModels.get(position);

        holder.text_content_show.setText(model.getDetail());
        holder.text_price_show.setText(model.getPrice());
        holder.text_title_show.setText(model.getKilo());
        holder.star_point.setText(String.valueOf(roundFloatNumber((float) model.getStar_point())));

        if (model.getStar_point() > 3.5) {
            holder.star_point.setTextColor(Color.GREEN);
        } else {
            holder.star_point.setTextColor(Color.RED);
        }

        if (model.is_new()) {

            holder.cardView_new.setVisibility(View.VISIBLE);

        } else {

            holder.cardView_new.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return secondModels.size();
    }

}
