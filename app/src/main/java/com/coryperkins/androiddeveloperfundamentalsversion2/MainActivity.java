package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // put initial data into the word list
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        for (int i = 0; i < 20; i++) {
            Log.d("MainActivity", mWordList.get(i));
        }

        // setup the recyclerview
        // get a handle to the recyclerView
        mRecyclerView = findViewById(R.id.recyclerview);

        // create an adapter and supply the data to be displayed
        mAdapter = new WordListAdapter(this, mWordList);

        // connect the adapter with the recyclerView
        mRecyclerView.setAdapter(mAdapter);

        // give the RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
