package com.example.catalogapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SecondAdapterShow extends RecyclerView.Adapter<SecondAdapterShow.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<SecondModelShow> secondModels;
    ArrayList<SecondModelShow> FullList;

    public SecondAdapterShow(Context context, ArrayList<SecondModelShow> secondModels) {
        this.context = context;
        this.secondModels = secondModels;
        FullList = new ArrayList<>(secondModels);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView star_point,price,kilo,detail,_new;
        CardView cardView_new;
        Button btn_add_show;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_show);
            star_point = itemView.findViewById(R.id.text_star_point_show);
            price = itemView.findViewById(R.id.text_price_show);
            _new = itemView.findViewById(R.id.text_new);
            kilo = itemView.findViewById(R.id.text_kilo_show);
            detail = itemView.findViewById(R.id.text_content_show);
            cardView_new = itemView.findViewById(R.id.cardView_show);
            btn_add_show = itemView.findViewById(R.id.btn_add_show);
        }
    }

    @NonNull
    @Override
    public SecondAdapterShow.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SecondAdapterShow.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_show,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SecondAdapterShow.MyViewHolder holder, int position) {
        SecondModelShow model = secondModels.get(position);

        Glide.with(context).load(model.getImage()).into(holder.image);
        holder.star_point.setText(String.valueOf(model.getStar_point()));
        if(model.getStar_point()>3.5){
            holder.star_point.setTextColor(Color.GREEN);
        }else{
            holder.star_point.setTextColor(Color.RED);
        }

        holder.kilo.setText(model.getKilo());
        holder.price.setText(model.getPrice());
        holder.detail.setText(model.getDetail());
        if(model.is_new()){
              holder.cardView_new.setVisibility(View.VISIBLE);
//            holder._new.setText("NEW");
        }else{
            holder.cardView_new.setVisibility(View.INVISIBLE);
        }
        holder.btn_add_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button show add clicked : "+holder.getAdapterPosition() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return secondModels.size();
    }

    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }

    private Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<SecondModelShow> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SecondModelShow item : FullList) {
                    if (item.getDetail().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            secondModels.clear();
            secondModels.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
