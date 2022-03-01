package com.example.catalogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView text_rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");

        ratingBar = findViewById(R.id.ratingBar);
        text_rating = findViewById(R.id.text_rating);

//        Float _text_rating = ratingBar.getRating();
//        int star_size = ratingBar.getNumStars();

        Intent intent = getIntent();
        String _price = intent.getStringExtra("price");
        boolean isNew = intent.getBooleanExtra("isNew",false);
        int image = intent.getIntExtra("image",0);

        text_rating.setText(String.valueOf(intent.getFloatExtra("rating",0.0f)));
        ratingBar.setRating(intent.getFloatExtra("rating",0.0f));

        TextView price = findViewById(R.id.textdetail_price);
        CardView _new = findViewById(R.id.cardView_detail_new);
        ImageView imageView = findViewById(R.id.image_detail);

        price.setText(_price);
        imageView.setImageResource(image);

        if(isNew){
            _new.setVisibility(View.VISIBLE);
        }else{
            _new.setVisibility(View.INVISIBLE);
        }

    }
}