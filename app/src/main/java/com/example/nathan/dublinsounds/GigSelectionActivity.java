package com.example.nathan.dublinsounds;

/*
* GigsSelectionActivity.java
*
* Version 1
*
* 14/12/2016
*
* @author Nathan Ryan x13448212
*
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GigSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gig_selection);

        Button submitBtn = (Button) findViewById(R.id.submitEventBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText artist = (EditText) findViewById(R.id.artistName);
                String message = artist.getText().toString();
                Intent myIntent = new Intent(view.getContext(), GigsActivity.class);
                myIntent.putExtra("artist", message);
                startActivity(myIntent);
            }
        });
    }
}
