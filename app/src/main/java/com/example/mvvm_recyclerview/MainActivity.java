package com.example.mvvm_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvm_recyclerview.adapters.RecyclerViewAdapter;
import com.example.mvvm_recyclerview.models.NicePlace;
import com.example.mvvm_recyclerview.viewmodels.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;
    private MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recycler_view);
        progressBar=findViewById(R.id.progess_bar);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();//initialize the repository
        mainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.getIsUpdating().observe(this , new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
            if (aBoolean){
                showProgressbar();
            }
            else {
                hideProgressbar();
                recyclerView.smoothScrollToPosition(mainActivityViewModel.getNicePlaces().getValue().size() - 1);
            }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addNewValue(
                        new NicePlace(
                          "Iowa",
                          "https://cdn.onlyinyourstate.com/wp-content/uploads/2016/02/1-11.jpg"
                        )
                );
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerViewAdapter = new RecyclerViewAdapter(mainActivityViewModel.getNicePlaces().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void showProgressbar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar(){
        progressBar.setVisibility(View.GONE);
    }
}
