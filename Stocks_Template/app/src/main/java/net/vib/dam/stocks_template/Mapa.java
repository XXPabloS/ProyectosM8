
package net.vib.dam.stocks_template;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {


   // Posar variables necessaries



    /* *
        Recordeu de crear la vostra API KEY A LA CONSOLA DE DEVELOPER DE GOOGLE;
        https://console.cloud.google.com/
        https://developers.google.com/maps/documentation/android-sdk/get-api-key
        No useu la meva!!!!!!!!
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // obtenir informació de la pantalla anterior

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Carregar mapa en la posició indicada 
        
        
        // Mostrar marcaador
        
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

    }


    /**
     * Donat un nom de país retorna les seves coordenades
     * @param country Nom del país
     * @return les coordenades del país
     */
    public LatLng getLocationFromCountry(String country) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng coord = null;

        try {
            address = coder.getFromLocationName(country, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            coord = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return coord;
    }

}
