package com.tulit.tulitimageviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras(); // get the extras that we pass with intent

        TextView name = findViewById(R.id.dNameID);
        ImageView image = findViewById(R.id.dImageID);

        if (extras != null) {
            name.setText(getString(R.string.dNameText, extras.getString("name"))); // format string using R.string
            Picasso.get()
                    .load(extras.getString("url")) // loads from url (or uses cached image)
                    .fit()
                    .centerInside()
                    .into(image);
        }

    }
}
