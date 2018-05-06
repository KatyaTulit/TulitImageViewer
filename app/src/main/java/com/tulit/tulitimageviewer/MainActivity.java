package com.tulit.tulitimageviewer;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity {
    private String apiKey = BuildConfig.ClientID;
    private String partUrl = "https://api.unsplash.com/photos/random?count=10&client_id=";
    private final String URL_DATA = partUrl + apiKey;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonRefresh = findViewById(R.id.refreshButtonID);

        recyclerView = findViewById(R.id.recyclerViewID);
        // Every item will have fixed size (so that everything is aligned)
        recyclerView.setHasFixedSize(true);
        // We want our items to be displayed in two columns, hence GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        listItems = new ArrayList<>();
        loadRecyclerViewData(); // Load data when creating the activity

        // Also we can change data when the floating action button is clicked
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRecyclerViewData();
            }
        });

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Загружаем данные");
        progressDialog.show();

        listItems.clear(); // Remove old items

        // Here we make an API request and parse it using Volley library:
        // we need the name of the photographer and image url
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                URL_DATA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        progressDialog.dismiss();

                        try {

                            for (int i = 0; i<jsonArray.length(); i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                JSONObject user = o.getJSONObject("user");
                                JSONObject links = o.getJSONObject("links");
                                ListItem item = new ListItem(
                                        user.getString("name"),
                                        links.getString("download")
                                );
                                listItems.add(item);
                            }

                            adapter = new MyAdapter(getApplicationContext(), listItems);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}
