package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mappa extends AppCompatActivity implements OnMapReadyCallback{

    private MapView vMappa;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mappa);

        vMappa = findViewById(R.id.vMappa);

        vMappa.onCreate(savedInstanceState);

        vMappa.getMapAsync(this);

    }

    protected void onResume(){
        vMappa.onResume();
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        try{
            MapsInitializer.initialize(this);
        } catch (Exception e){
            e.printStackTrace();
        }

        LatLng llAversa = new LatLng(40.9675999,14.1996051);

        mGoogleMap.addMarker(new MarkerOptions().position(llAversa));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(llAversa, 10));

    }
}
