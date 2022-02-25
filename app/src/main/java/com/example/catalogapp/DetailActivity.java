package com.example.catalogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");

        Intent intent = getIntent();
        String star_point = intent.getStringExtra("star_point");
        boolean isNew = intent.getBooleanExtra("isNew",false);
        int image = intent.getIntExtra("image",0);

        TextView star = findViewById(R.id.textdetail_price);
        CardView _new = findViewById(R.id.cardView_detail_new);
        ImageView imageView = findViewById(R.id.image_detail);

        star.setText(star_point);
        imageView.setImageResource(image);
        if(isNew){
            _new.setVisibility(View.VISIBLE);
        }else{
            _new.setVisibility(View.INVISIBLE);
        }
    }
}