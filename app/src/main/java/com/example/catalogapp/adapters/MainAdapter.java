package com.example.catalogapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catalogapp.R;
import com.example.catalogapp.ui.SecondActivity;
import com.example.catalogapp.model.TitleModel;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<TitleModel> titleModels;
    ArrayList<TitleModel> FullList;

    public MainAdapter(Context context, ArrayList<TitleModel> titleModels) {
        this.context = context;
        this.titleModels = titleModels;
        FullList = new ArrayList<>(titleModels);
    }

    @Override
    public Filter getFilter() {
        return Searched_Filter;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text_name;
        ImageView image_main,image_right;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_name = itemView.findViewById(R.id.text_name);
            image_main = itemView.findViewById(R.id.image_main);
            image_right = itemView.findViewById(R.id.image_right);
        }
    }

    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position) {

        TitleModel model = titleModels.get(position);
        holder.text_name.setText(model.getTitle());
        Glide.with(context).load(model.getImage_main())
                .centerCrop()
                .into(holder.image_main);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), SecondActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return titleModels.size();
    }

    private Filter Searched_Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<TitleModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (TitleModel item : FullList) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
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
            titleModels.clear();
            titleModels.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}


