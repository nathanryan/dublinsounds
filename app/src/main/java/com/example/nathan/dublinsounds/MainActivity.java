package com.example.nathan.dublinsounds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//<a href="https://icons8.com">Icon pack by Icons8</a>

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gigBtn = (Button) findViewById(R.id.gigsBtn);
        Button venueBtn = (Button) findViewById(R.id.venuesBtn);
        Button cameraBtn = (Button) findViewById(R.id.cameraBtn);
        Button radioBtn = (Button) findViewById(R.id.radioBtn);

        final Intent gigIntent = new Intent(this, GigSelectionActivity.class);
        final Intent venueIntent = new Intent(this, VenuesActivity.class);
        final Intent cameraIntent = new Intent(this, CameraActivity.class);
        final Intent radioIntent = new Intent(this, RadioActivity.class);

        gigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(gigIntent);
            }
        });

        venueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(venueIntent);
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(cameraIntent);
            }
        });

        radioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(radioIntent);
            }
        });
    }
}
