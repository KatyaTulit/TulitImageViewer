package com.tulit.tulitimageviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private TextView name;
    private ImageView image;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        extras = getIntent().getExtras(); // get the extras that we pass with intent

        name = findViewById(R.id.dNameID);
        image = findViewById(R.id.dImageID);

        if (extras != null) {
            name.setText(getString(R.string.dNameText, extras.getString("name")));
            Picasso.get()
                    .load(extras.getString("url"))
                    .fit()
                    .centerInside()
                    .into(image);
        }

    }
}
