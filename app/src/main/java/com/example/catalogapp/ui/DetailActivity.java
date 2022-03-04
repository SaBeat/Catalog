package com.example.catalogapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.catalogapp.R;
import com.example.catalogapp.adapters.SecondAdapterRecommend;
import com.example.catalogapp.adapters.ViewPagerAdapter;
import com.example.catalogapp.model.SecondModelRecommend;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    RatingBar ratingBar;
    ConstraintLayout persistent_bottom_sheet;
    TextView text_rating;
    private BottomSheetBehavior mBottomSheetBehavior1;
    ViewPager viewPager;
    WormDotsIndicator dot1;
    ImageButton btnDown;
    ViewPagerAdapter viewAdapter;
    SecondAdapterRecommend adapterRecommend;
    ArrayList<SecondModelRecommend>secondModels;
    RecyclerView rv_detail_recommend;
    ConstraintLayout bottomSheet;
    BottomSheetBehavior standardBottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        fillRecommedation();
        ratingBar = findViewById(R.id.ratingBar);
        text_rating = findViewById(R.id.text_rating);

        rv_detail_recommend = findViewById(R.id.rv_detail_recommend);
        viewPager=findViewById(R.id.viewPager);
        dot1=findViewById(R.id.dot1);
        btnDown = findViewById(R.id.btn_image_down);
        persistent_bottom_sheet = findViewById(R.id.persistent_bottom_sheet);

        View bottomSheet = findViewById(R.id.bottom_sheet1);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
                    persistent_bottom_sheet.setVisibility(View.VISIBLE);

                }
                else {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);
                    persistent_bottom_sheet.setVisibility(View.GONE);
                }
            }
        });


        viewAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewAdapter);
        dot1.setViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail");


        Intent intent = getIntent();
        String _price = intent.getStringExtra("price");
        boolean isNew = intent.getBooleanExtra("isNew",false);
        int image = intent.getIntExtra("image",0);

        text_rating.setText(String.valueOf(intent.getFloatExtra("rating",0.0f)));
        ratingBar.setRating(intent.getFloatExtra("rating",0.0f));

        TextView price = findViewById(R.id.textdetail_price);
        CardView _new = findViewById(R.id.cardView_detail_new);
//        ImageView imageView = findViewById(R.id.viewPager);

        price.setText(_price);
//        imageView.setImageResource(image);

        if(isNew){
            _new.setVisibility(View.VISIBLE);
        }else{
            _new.setVisibility(View.INVISIBLE);
        }

    }

    private void fillRecommedation() {
        secondModels = new ArrayList<>();
        secondModels.add(new SecondModelRecommend(R.drawable.apple, 4.5f, "575.00 $", "za kor 2kq", false));
        secondModels.add(new SecondModelRecommend(R.drawable.durian, 4.0f, "375.00 $", "za kor 3kq", true));
        secondModels.add(new SecondModelRecommend(R.drawable.kiwi, 3.7f, "265.00 $", "za kor 4kq", true));
        secondModels.add(new SecondModelRecommend(R.drawable.lemon, 4.9f, "475.00 $", "za kor 5kq", false));
        secondModels.add(new SecondModelRecommend(R.drawable.strawberry, 4.1f, "775.00 $", "za kor 6kq", false));

        setRecycler(secondModels);
    }

    private void setRecycler(ArrayList<SecondModelRecommend> list){
        rv_detail_recommend = findViewById(R.id.rv_detail_recommend);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rv_detail_recommend.setLayoutManager(layoutManager);
        adapterRecommend = new SecondAdapterRecommend(this, list);
        rv_detail_recommend.setAdapter(adapterRecommend);
    }
}