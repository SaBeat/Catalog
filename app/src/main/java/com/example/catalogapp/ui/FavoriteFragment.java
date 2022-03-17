package com.example.catalogapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.catalogapp.R;
import com.example.catalogapp.adapters.FavoriteAdapter;
import com.example.catalogapp.adapters.SecondAdapterRecommend;
import com.example.catalogapp.adapters.SecondAdapterShow;
import com.example.catalogapp.model.SecondModelShow;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    FavoriteAdapter favoriteAdapter;
    SecondAdapterShow adapterShow;
    RecyclerView rv_favorite;
    ArrayList<SecondModelShow> modelShows;
    View view;
    ImageView imageView;
    Button btn_delete;
    MyDatabaseHelper myDB;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        myDB = new MyDatabaseHelper(requireContext());
        imageView = view.findViewById(R.id.image_show);

        rv_favorite = view.findViewById(R.id.rv_favorite);
        btn_delete = view.findViewById(R.id.btn_delete_all);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
        storeDataInArrays();
        setRecycler(modelShows);
        return view;
    }

    private void setRecycler(ArrayList<SecondModelShow> list){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_favorite.setLayoutManager(layoutManager);
        favoriteAdapter = new FavoriteAdapter(getContext(), list);
        rv_favorite.setAdapter(favoriteAdapter);
    }

    void storeDataInArrays(){
        modelShows = new ArrayList<>();
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "Count 0", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){

                modelShows.add(new SecondModelShow(
                        cursor.getInt(1) == 1,
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            }
        }
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.delete,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//
//            case R.id.icon_delete_all:
//                    confirmDialog();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(requireContext());
                myDB.deleteAllData();
//                myDB.readAllData();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}