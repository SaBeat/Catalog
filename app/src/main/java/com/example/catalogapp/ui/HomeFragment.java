package com.example.catalogapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.catalogapp.R;
import com.example.catalogapp.adapters.MainAdapter;
import com.example.catalogapp.model.TitleModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements MainAdapter.ItemClickListener {
    MainAdapter mainAdapter;
    public static ArrayList<TitleModel> titleModels;
    RecyclerView mainRecycler;
    View view;
    Context context;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = requireContext();
//        fillArrayList();
//        setHasOptionsMenu(true);
//        setRecycler(titleModels);

        return view;
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
        mainRecycler = view.findViewById(R.id.main_recycler);
        mainRecycler.setHasFixedSize(true);
        mainAdapter = new MainAdapter(context,titleModel);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        mainRecycler.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mainRecycler.getContext(),
                layoutManager.getOrientation());
        mainRecycler.addItemDecoration(dividerItemDecoration);
        mainRecycler.setAdapter(mainAdapter);
        mainAdapter.setClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.second_activity_menu,menu);
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
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view, int position) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.container_home, SecondFragment.class, null);
        transaction.commit();
        mainRecycler.setVisibility(View.GONE);
    }

}