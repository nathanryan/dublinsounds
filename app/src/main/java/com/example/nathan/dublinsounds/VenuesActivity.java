package com.example.nathan.dublinsounds;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

public class VenuesActivity extends FragmentActivity implements OnMapReadyCallback {

    // http://stackoverflow.com/questions/16663624/google-map-v2-on-top-half-of-the-android-screen-and-list-view-on-the-bottom-half
    private static final LatLng CITYCENTRE = new LatLng(53.3498,-6.2603);
    private static final LatLng VICARST = new LatLng(53.3419,-6.2774);
    private static final LatLng OLYMPIA = new LatLng(53.3443,-6.2661);
    private static final LatLng THREEARENA = new LatLng(53.3475,-6.2285);

    private Marker mVicarSt;
    private Marker mOlympia;
    private Marker mThreeArena;

    private GoogleMap mMap;

    //http://www.codemarvels.com/2013/09/list-view-using-simplecursor-adapter-part-i/
    DatabaseHelper db;
    SimpleCursorAdapter dataAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        db = new DatabaseHelper(this);

        try {

            db.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            db.openDataBase();
            displayList();
        } catch (SQLException sqle) {

            throw sqle;

        }
    }

    public void displayList(){
        Cursor cursor = db.getAllData();
        String from [] = new String[]{db.venueID, db.venueName, db.venueLocation, db.venueCapacity, db.venueDesc};
        int to [] = new int[] {R.id.venueID, R.id.venueName, R.id.venueLocation, R.id.venueCapacity, R.id.venueDescription};
        dataAdapter = new SimpleCursorAdapter(this, R.layout.custom_venue_row, cursor, from, to, 0);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(dataAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add some markers to the map, and add a data object to each marker.
        mVicarSt = mMap.addMarker(new MarkerOptions()
                .position(VICARST)
                .title("Vicar Street"));
        mVicarSt.setTag(0);

        mOlympia = mMap.addMarker(new MarkerOptions()
                .position(OLYMPIA)
                .title("Olympia Theatre"));
        mOlympia.setTag(0);

        mThreeArena = mMap.addMarker(new MarkerOptions()
                .position(THREEARENA)
                .title("3 Arena"));
        mThreeArena.setTag(0);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(CITYCENTRE));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.5f));
    }
}
