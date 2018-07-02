package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.telecom.Call;
import android.text.style.CharacterStyle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Mappa extends AppCompatActivity implements OnMapReadyCallback {

    private String TAG = "MAP_LIFE";

    private MapView vMappa;
    private GoogleMap mGoogleMap;
    private CardView vCard;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Locale locale = new Locale("it");
        Configuration configs = getBaseContext().getResources().getConfiguration();
        configs.locale = locale;
        getBaseContext().getResources().updateConfiguration(configs, getBaseContext().getResources().getDisplayMetrics());
        getApplicationContext().getResources().updateConfiguration(configs, getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.layout_mappa);

        vCard = findViewById(R.id.Card);

        vMappa = findViewById(R.id.vMappa);

        vMappa.onCreate(savedInstanceState);

        vMappa.getMapAsync(this);

        final PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.

                mGoogleMap.clear();

                mGoogleMap.addMarker(new MarkerOptions().position(place.getLatLng()));

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 10));

                Log.i(TAG, "Place: " + place.getName());

                LatLngBounds latLngBounds = new LatLngBounds(place.getLatLng(),
                        place.getLatLng());
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                builder.setLatLngBounds(latLngBounds);

                try {
                    startActivityForResult(builder.build(Mappa.this), 3);
                } catch (Exception e) {
                    Log.e(TAG, e.getStackTrace().toString());
                }

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }

    protected void onResume() {
        vMappa.onResume();
        super.onResume();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LatLng llAversa = new LatLng(40.9675999, 14.1996051);

        mGoogleMap.addMarker(new MarkerOptions().position(llAversa).title("Locazione abitazione").title("La mia posizione"));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(llAversa, 15));


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getApplicationContext(), data);
                mGoogleMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15));
                //String toastMsg = String.format("Place: %s", place.getName());
                //Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

}
