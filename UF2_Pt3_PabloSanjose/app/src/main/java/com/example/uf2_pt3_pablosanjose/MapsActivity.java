package com.example.uf2_pt3_pablosanjose;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogByCoords();
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogByName();
            }
        });

    }


    /**
     *
     */
    private void showAlertDialogByCoords() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(this);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View textView = inflater.inflate(R.layout.alert_dialog_by_coord, null);

        final EditText mLatitude = (EditText) textView.findViewById(R.id.latitude);
        final EditText mLongitude = (EditText) textView.findViewById(R.id.longitude);

        builder.setView(textView);

        // Add the buttons
        builder.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked button
                // Add a marker and move the camera
                double latitude = Double.parseDouble(mLatitude.getText().toString());
                double longitude = Double.parseDouble(mLongitude.getText().toString());
                LatLng location = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(location).title("Mark"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            }
        });

        // Show
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     *
     */
    private void showAlertDialogByName() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = LayoutInflater.from(this);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View textView = inflater.inflate(R.layout.alert_dialog_by_name, null);

        final EditText mLocation = (EditText) textView.findViewById(R.id.location);

        builder.setView(textView);

        // Add the buttons
        builder.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                Geocoder geo = new Geocoder(getApplicationContext());
                List<Address> address = null;
                Log.d(TAG,mLocation.getText().toString());
                try {
                    address = geo.getFromLocationName(mLocation.getText().toString(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LatLng location = new LatLng(address.get(0).getLatitude(), address.get(0).getLongitude());
                mMap.addMarker(new MarkerOptions().position(location).title(mLocation.getText().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            }
        });

        // Show
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

}

