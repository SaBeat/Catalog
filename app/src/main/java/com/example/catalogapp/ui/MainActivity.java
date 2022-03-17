package com.example.catalogapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.catalogapp.R;
import com.example.catalogapp.adapters.MainAdapter;
import com.example.catalogapp.model.TitleModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    MainAdapter mainAdapter;
    ArrayList<TitleModel> titleModels;
    RecyclerView mainRecycler;
//    HomeFragment homeFragment;
//    FavoriteFragment favoriteFragment;
//    BasketFragment basketFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillArrayList();

        setRecycler(titleModels);
//        homeFragment = new HomeFragment();
//        favoriteFragment = new FavoriteFragment();
//        basketFragment = new BasketFragment();
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.icon_home);
    }

    public void fillArrayList(){
        titleModels = new ArrayList<>();

        titleModels.add(new TitleModel("Alma",R.drawable.apple));
        titleModels.add(new TitleModel("Armud",R.drawable.lemon));
        titleModels.add(new TitleModel("Banan",R.drawable.strawberry));
        titleModels.add(new TitleModel("Qreyfurt",R.drawable.durian));
        titleModels.add(new TitleModel("Portagal",R.drawable.kiwi));
        titleModels.add(new TitleModel("Limon",R.drawable.strawberry));
        titleModels.add(new TitleModel("Nar",R.drawable.lemon));
        titleModels.add(new TitleModel("Incir",R.drawable.durian));
        titleModels.add(new TitleModel("Uzum",R.drawable.lemon));
        titleModels.add(new TitleModel("Gilas",R.drawable.durian));
        titleModels.add(new TitleModel("Albali",R.drawable.strawberry));
        titleModels.add(new TitleModel("Ezgil",R.drawable.apple));
        titleModels.add(new TitleModel("Erik",R.drawable.kiwi));
        titleModels.add(new TitleModel("Saftali",R.drawable.durian));
        titleModels.add(new TitleModel("Heyva",R.drawable.apple));
        titleModels.add(new TitleModel("Yapon ezgili",R.drawable.lemon));
        titleModels.add(new TitleModel("Ananas",R.drawable.strawberry));
        titleModels.add(new TitleModel("Alca",R.drawable.kiwi));
    }

    public void setRecycler(ArrayList<TitleModel> titleModel){
        mainRecycler = findViewById(R.id.main_recycler);
        mainRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecycler.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(this,titleModel);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mainRecycler.getContext(),
                layoutManager.getOrientation());
        mainRecycler.addItemDecoration(dividerItemDecoration);
        mainRecycler.setAdapter(mainAdapter);
    }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.icon_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
//                return true;
//
//            case R.id.icon_favorite:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, favoriteFragment).commit();
//                return true;
//
//            case R.id.icon_basket:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, basketFragment).commit();
//                return true;
//        }
//        return false;
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        HomeFragment homeFragment = new HomeFragment();
//        mainAdapter = new MainAdapter(this,homeFragment.titleModels);
//        getMenuInflater().inflate(R.menu.menu,menu);
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search for anything...");
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                mainAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_manu,menu);
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
                mainAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_favorite:
                startActivity(new Intent(this,FavoriteActivity.class));
                break;

            case R.id.action_basket:
                startActivity(new Intent(this,BasketActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}