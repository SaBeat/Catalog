package com.example.catalogapp.adapters;

import static com.example.catalogapp.Util.roundFloatNumber;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.catalogapp.ui.DetailActivity;
import com.example.catalogapp.R;
import com.example.catalogapp.model.SecondModelShow;
import com.example.catalogapp.ui.FavoriteFragment;
import com.example.catalogapp.ui.FeedReaderContract;
import com.example.catalogapp.ui.MyDatabaseHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SecondAdapterShow extends RecyclerView.Adapter<SecondAdapterShow.MyViewHolder> implements Filterable {
    Context context;
    final Animation animation;
    public int firstTimeIndex = 0;
    public int index = -1;
    public boolean isColoredHeart = false;
    ArrayList<SecondModelShow> secondModels;
    ArrayList<SecondModelShow> FullList;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();

    Handler handler = new Handler();
    Runnable runnable;

    public SecondAdapterShow(Context context, ArrayList<SecondModelShow> secondModels) {
        this.context = context;
        this.secondModels = secondModels;
        FullList = new ArrayList<>(secondModels);
        animation = AnimationUtils.loadAnimation(context, R.anim.animation);
    }

    public void setShows(ArrayList<SecondModelShow> secondModels) {
        this.secondModels = new ArrayList<>();
        this.secondModels = secondModels;
        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image, image_fav;
        TextView star_point, price, kilo, detail, _new;
        CardView cardView_new, add_favorite;
        Button btn_add_show, btn_minus_show;
        SwipeRevealLayout swipeRevealLayout;
        FrameLayout frame_show;
        EditText et_show_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_show);
            image_fav = itemView.findViewById(R.id.image_fav);
            star_point = itemView.findViewById(R.id.text_star_point_show);
            price = itemView.findViewById(R.id.text_price_show);
            _new = itemView.findViewById(R.id.text_detail_new);
            kilo = itemView.findViewById(R.id.text_kilo_show);
            detail = itemView.findViewById(R.id.text_content_show);
            cardView_new = itemView.findViewById(R.id.cardView_show);
            btn_add_show = itemView.findViewById(R.id.btn_add_show);
            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            add_favorite = itemView.findViewById(R.id.add_favorite);
            frame_show = itemView.findViewById(R.id.frame_show);
            et_show_price = itemView.findViewById(R.id.et_show_price);
            btn_minus_show = itemView.findViewById(R.id.btn_minus_show);
        }

    }

    @NonNull
    @Override
    public SecondAdapterShow.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SecondAdapterShow.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_show, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SecondAdapterShow.MyViewHolder holder, int position) {
        SecondModelShow model = secondModels.get(position);
        binderHelper.setOpenOnlyOne(true);
        binderHelper.bind(holder.swipeRevealLayout, String.valueOf(model.getPrice()));
        binderHelper.closeLayout(String.valueOf(model.getPrice()));

        Glide.with(context).load(model.getImage())
                .centerCrop()
                .into(holder.image);
        holder.star_point.setText(String.valueOf(roundFloatNumber((float) model.getStar_point())));

        holder.frame_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", model.getImage());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("isNew", model.is_new());
                intent.putExtra("rating", model.getStar_point());
                context.startActivity(intent);
            }
        });

        if (model.getStar_point() > 3.5) {
            holder.star_point.setTextColor(Color.GREEN);
        } else {
            holder.star_point.setTextColor(Color.RED);
        }

        holder.kilo.setText(model.getKilo());
        holder.price.setText(model.getPrice());
        holder.detail.setText(model.getDetail());
        if (model.is_new()) {

            holder.cardView_new.setVisibility(View.VISIBLE);

        } else {

            holder.cardView_new.setVisibility(View.INVISIBLE);
        }
        holder.et_show_price.setId(holder.getAdapterPosition());

        holder.btn_add_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.et_show_price.setVisibility(View.VISIBLE);
                holder.btn_minus_show.setVisibility(View.VISIBLE);
                holder.et_show_price.startAnimation(animation);

                firstTimeIndex++;
                holder.et_show_price.setText(String.valueOf(firstTimeIndex));

            }
        });

        holder.btn_add_show.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                holder.et_show_price.setVisibility(View.VISIBLE);
                holder.btn_minus_show.setVisibility(View.VISIBLE);
                holder.et_show_price.startAnimation(animation);
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!holder.btn_add_show.isPressed()) return;
                        firstTimeIndex++;
                        holder.et_show_price.setText(String.valueOf(firstTimeIndex));
                        handler.postDelayed(runnable, 100);
                    }
                };

                handler.postDelayed(runnable, 100);
                return true;

            }

        });

        holder.btn_minus_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.et_show_price.startAnimation(animation);
                if (firstTimeIndex > 0) {
                    firstTimeIndex--;
                }

                holder.et_show_price.setText(String.valueOf(firstTimeIndex));
            }
        });

        holder.btn_minus_show.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                holder.et_show_price.startAnimation(animation);
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!holder.btn_minus_show.isPressed()) return;
                        if (firstTimeIndex > 0) {
                            firstTimeIndex--;
                        }
                        holder.et_show_price.setText(String.valueOf(firstTimeIndex));
                        handler.postDelayed(runnable, 100);
                    }
                };

                handler.postDelayed(runnable, 100);
                return true;

            }

        });

        holder.add_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isColoredHeart = !isColoredHeart;
                if (isColoredHeart) {
                    holder.image_fav.setImageResource(R.drawable.ic_favorite_red);
                    MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                    myDB.addBook(model.is_new(),(float) model.getStar_point(),model.getDetail(),model.getPrice(),model.getKilo());
                } else {
                    holder.image_fav.setImageResource(R.drawable.ic_favorite_black);
                    MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                    myDB.deleteOneRow(model.getPrice());
                }

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


