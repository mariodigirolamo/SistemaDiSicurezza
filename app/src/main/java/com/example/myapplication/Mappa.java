package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.telecom.Call;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mappa extends AppCompatActivity implements OnMapReadyCallback{

    private String TAG = "MAP_LIFE";

    private MapView vMappa;
    private GoogleMap mGoogleMap;
    private CardView vCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mappa);

        vCard = findViewById(R.id.Card);

        vMappa = findViewById(R.id.vMappa);

        vMappa.onCreate(savedInstanceState);

        vMappa.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.

                mGoogleMap.clear();

                mGoogleMap.addMarker(new MarkerOptions().position(place.getLatLng()));

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 10));

                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

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
