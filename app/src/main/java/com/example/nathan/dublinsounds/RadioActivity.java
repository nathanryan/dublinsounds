package com.example.nathan.dublinsounds;

/*
* RadioActivity.java
*
* Version 1
*
* 14/12/2016
*
* @reference https://www.youtube.com/watch?v=pPpVZ8YZXHk Tihomir Radeff
*
* License free music file sourced from Moby http://moby.com/la1/
*
* @author Nathan Ryan x13448212
 */

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class RadioActivity extends AppCompatActivity {
    Button b1, b2, b3, b4, b5;
    MediaPlayer radioPlayer = new MediaPlayer();
    boolean prepared = false;
    boolean started = false;
    //online dublin airport radio stream (it does work but takes longer than a music station to play)
    String stream = "http://mtl2.liveatc.net/eidw3";
    // test radio stream http://stream.radioreklama.bg/radio1rock128

    MediaPlayer mySound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        //song player
        b2 = (Button)findViewById(R.id.playMusicBtn);
        b3 = (Button)findViewById(R.id.pauseMusicBtn);
        b4 = (Button)findViewById(R.id.resumeMusicBtn);
        b5 = (Button)findViewById(R.id.stopMusicBtn);
        mySound = MediaPlayer.create(this, R.raw.la1);
        //song player

    /*
    * @author Nathan Ryan x13448212
    */
        b2.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick ( View v) {
                if (mySound != null ){
                    mySound.reset ();
                    mySound.release ();
                }
                mySound = MediaPlayer.create(getApplicationContext(),R.raw.la1);
                mySound.start ();
            }
        });

        //pause song
        b3.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick ( View v) {
                mySound.pause();
            }
        });

        //resume song
        b4.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick ( View v) {
                mySound.start();
            }
        });
        

        //radio streaming
        b1 = (Button)findViewById(R.id.playRadio);
        b1.setEnabled(false);
        b1.setText("LOADING");

        radioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        new PlayerTask().execute(stream);

        b1.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick ( View v) {
                if(started){
                    started = false;
                    radioPlayer.pause();
                    b1.setText("PLAY");
                }else{
                    started = true;
                    radioPlayer.start();
                    b1.setText("PAUSE");
                }
            }
        });
        //radio streaming
    }

    class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            try{
                radioPlayer.setDataSource(strings[0]);
                radioPlayer.prepare();
                prepared = true;
            } catch (IOException e){
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean){
            super.onPostExecute(aBoolean);
            b1.setEnabled(true);
            b1.setText("PLAY");
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(started){
            radioPlayer.pause();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(started){
            radioPlayer.start();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(prepared){
            radioPlayer.release();
        }
    }
}
