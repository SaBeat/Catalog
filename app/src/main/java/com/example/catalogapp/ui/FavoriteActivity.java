package com.example.catalogapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.catalogapp.R;
import com.example.catalogapp.adapters.FavoriteAdapter;
import com.example.catalogapp.adapters.SecondAdapterShow;
import com.example.catalogapp.model.SecondModelShow;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    FavoriteAdapter favoriteAdapter;
    SecondAdapterShow adapterShow;
    RecyclerView rv_favorite;
    ArrayList<SecondModelShow> modelShows;
    ImageView imageView;
    Button btn_delete;
    MyDatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        myDB = new MyDatabaseHelper(this);
        imageView = findViewById(R.id.image_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favorite");

        rv_favorite = findViewById(R.id.rv_favorite);
        storeDataInArrays();
        setRecycler(modelShows);
    }

    private void setRecycler(ArrayList<SecondModelShow> list){
        LinearLayoutManager layoutManager = new LinearLayoutManager(FavoriteActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_favorite.setLayoutManager(layoutManager);
        favoriteAdapter = new FavoriteAdapter(this, list);
        rv_favorite.setAdapter(favoriteAdapter);
    }

    void storeDataInArrays(){
        modelShows = new ArrayList<>();
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Count 0", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                modelShows.add(new SecondModelShow(
                        cursor.getInt(1) != 0,
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            }
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDB = new MyDatabaseHelper(FavoriteActivity.this);
                myDB.deleteAllData();
//                startActivity(new Intent(FavoriteActivity.this,FavoriteActivity.class));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
        myDB.readAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_refresh:
                Cursor cursor = myDB.readAllData();
                cursor.getString(1);
                break;
            case R.id.action_delete:
                if(favoriteAdapter.getItemCount()==0){
                    Toast.makeText(this, "Empty list", Toast.LENGTH_SHORT).show();
                }else{
                    confirmDialog();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}