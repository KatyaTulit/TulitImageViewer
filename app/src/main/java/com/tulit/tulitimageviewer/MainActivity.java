package com.tulit.tulitimageviewer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity {
    private String apiKey = BuildConfig.ClientID;
    private String partUrl = "https://api.unsplash.com/photos/random?count=15&client_id=";
    private final String URL_DATA = partUrl + apiKey;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewID);
        // Every item will have fixed size (so that everything is aligned)
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        listItems = new ArrayList<>();

        // Generate some data
        for (int i = 0; i < 20; i++) {
            ListItem item = new ListItem("Item #" + i);
            listItems.add(item);
        }

        adapter = new MyAdapter(this, listItems);
        recyclerView.setAdapter(adapter);

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Загружаем данные");
        progressDialog.show();

    }
}
