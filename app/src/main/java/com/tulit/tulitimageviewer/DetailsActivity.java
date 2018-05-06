package com.tulit.tulitimageviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView name;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        extras = getIntent().getExtras(); // get the extras that we pass with intent


        name = findViewById(R.id.dNameID);

        if (extras != null) {
            name.setText(getString(R.string.dNameText, extras.getString("name")));
        }

    }
}
