package com.example.nathan.dublinsounds;

/*
* MainActivity.java
*
* Version 1
*
* 14/12/2016
*
* @author Nathan Ryan x13448212
*
* Icon pack by Icons8 https://icons8.com
*
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
