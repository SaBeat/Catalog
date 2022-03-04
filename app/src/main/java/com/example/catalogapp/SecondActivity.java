package com.example.catalogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView_tovsiye, recyclerview_show;
    SecondAdapterRecommend secondAdapter;
    SecondAdapterShow secondAdapterShow;
    ArrayList<SecondModelRecommend> secondModels;
    ArrayList<SecondModelShow> modelShows;
    LinearLayout linearBaha, linearFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        linearBaha = findViewById(R.id.linear_sortlama);
        linearFilter = findViewById(R.id.linearFilter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Second Activity");

        openBottomSheetDialogs();

        fillRecommedation();
        fillShow();
    }

    private void openBottomSheetDialogs(){
        linearBaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogSort();
            }
        });

        linearFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialogFilter();
            }
        });
    }

    private void fillRecommedation() {
        secondModels = new ArrayList<>();
        secondModels.add(new SecondModelRecommend(R.drawable.apple, 4.5f, "575.00 $", "za kor 2kq", false));
        secondModels.add(new SecondModelRecommend(R.drawable.durian, 4.0f, "375.00 $", "za kor 3kq", true));
        secondModels.add(new SecondModelRecommend(R.drawable.kiwi, 3.7f, "265.00 $", "za kor 4kq", true));
        secondModels.add(new SecondModelRecommend(R.drawable.lemon, 4.9f, "475.00 $", "za kor 5kq", false));
        secondModels.add(new SecondModelRecommend(R.drawable.strawberry, 4.1f, "775.00 $", "za kor 6kq", false));

        setRecyclerView_tovsiye(secondModels);
    }

    private void fillShow() {
        modelShows = new ArrayList<>();

        modelShows.add(new SecondModelShow(R.drawable.strawberry, true, 3.3f,
                "Letraset sabit sheets containing Lorem Ipsum passages, and more recently with desktop",
                "78.00 $", "za kor . 2kq"));

        modelShows.add(new SecondModelShow(R.drawable.lemon, false, 4.3f,
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop",
                "78.00 $", "za kor . 3kq"));

        modelShows.add(new SecondModelShow(R.drawable.kiwi, true, 3.1f,
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop",
                "78.00 $", "za kor . 4kq"));

        modelShows.add(new SecondModelShow(R.drawable.durian, false, 4.9f,
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop",
                "78.00 $", "za kor . 5kq"));

        modelShows.add(new SecondModelShow(R.drawable.apple, true, 2.5f,
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop",
                "78.00 $", "za kor . 6kq"));

        setRecyclerView_show(modelShows);
    }

    public void setRecyclerView_tovsiye(ArrayList<SecondModelRecommend> list) {
        recyclerView_tovsiye = findViewById(R.id.recycler_tovsiye);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_tovsiye.setLayoutManager(layoutManager);
        secondAdapter = new SecondAdapterRecommend(this, list);
        recyclerView_tovsiye.setAdapter(secondAdapter);
    }

    public void setRecyclerView_show(ArrayList<SecondModelShow> list) {
        recyclerview_show = findViewById(R.id.recycler_show);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview_show.setLayoutManager(layoutManager);
        secondAdapterShow = new SecondAdapterShow(this, list);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerview_show.getContext(),
                layoutManager.getOrientation());
        recyclerview_show.addItemDecoration(dividerItemDecoration);

        recyclerview_show.setAdapter(secondAdapterShow);
    }

    private void showBottomSheetDialogSort() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_sort);

        LinearLayout baha = bottomSheetDialog.findViewById(R.id.bahaLinearLayout);
        LinearLayout ucuz = bottomSheetDialog.findViewById(R.id.ucuzLinearLaySout);

        baha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "baha clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.cancel();
            }
        });

        ucuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ucuz clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.cancel();
            }
        });

        bottomSheetDialog.show();
    }

    private void showBottomSheetDialogFilter() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_filter);

        LinearLayout baha = bottomSheetDialog.findViewById(R.id.filterLinearLayout_baha);
        LinearLayout ucuz = bottomSheetDialog.findViewById(R.id.filterLinearLayout_ucuz);
        LinearLayout any = bottomSheetDialog.findViewById(R.id.filterLinearLayout_any);

        baha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Filter baha clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.cancel();
            }
        });

        ucuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Filter ucuz clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.cancel();
            }
        });

        any.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Filter any clicked", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.cancel();
            }
        });

        bottomSheetDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search for anything...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                secondAdapterShow.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


}