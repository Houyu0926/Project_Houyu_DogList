package com.example.projecthouyudoglist.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projecthouyudoglist.R;
import com.example.projecthouyudoglist.Singletons;
import com.example.projecthouyudoglist.presentation.controller.MainController;
import com.example.projecthouyudoglist.presentation.model.Dog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainController = new MainController(
                Singletons.getSharedPreferencesInstance(getApplicationContext()),
                Singletons.getGsonInstance(),
                this
        );
        mainController.onStart();
    }

    public void error() {
        Toast.makeText(getApplicationContext(),"API error", Toast.LENGTH_SHORT).show();
    }


    public void showList(List<Dog> dogList){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(dogList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Dog item) {
                mainController.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void navigateToDetails(Dog dog) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
//        myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);

//        Toast.makeText(getApplicationContext(),dog.getBreed(), Toast.LENGTH_SHORT).show();
    }


}