package net.vidalibarraquer.pt6_pablosanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import net.vidalibarraquer.pt6_pablosanjose.menu.ActivityProperties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img1, img2, img3, img4;

    public static String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.bundesliga);
        img2 = findViewById(R.id.laliga);
        img3 = findViewById(R.id.seriea);
        img4 = findViewById(R.id.premierleague);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

        if (URL.equalsIgnoreCase("")) {
            URL = "https://www.vidalibarraquer.net/android/futbol/";
        }
    }

    @Override
    public void onClick(View view) {
        Intent moveScreen = new Intent(this, ClubList.class);

        if (hiHaConnexio()) {
            switch (view.getId()) {
                case R.id.bundesliga:
                    moveScreen.putExtra("URLDefault", URL + "bundesliga");
                    startActivity(moveScreen);
                    break;
                case R.id.laliga:
                    moveScreen.putExtra("URLDefault", URL + "laliga");
                    startActivity(moveScreen);
                    break;
                case R.id.seriea:
                    moveScreen.putExtra("URLDefault", URL + "seriea");
                    startActivity(moveScreen);
                    break;
                case R.id.premierleague:
                    moveScreen.putExtra("URLDefault", URL + "premier");
                    startActivity(moveScreen);
                    break;
                default:
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Comprova si el dispositiu té connexió
     * @return cert si en té i fals si no en té
     */
    public boolean hiHaConnexio() {
        boolean resultat = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    resultat = true;
                }
            }
        } else {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                resultat = true;
            } else {
                resultat = false;
            }
        }

        return resultat;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.stngs_btn:
                Intent intent = new Intent(this, ActivityProperties.class);
                startActivity(intent);
                return true;
            default:
                Toast.makeText(this, "Nothing Happened( Tiananmen Square 1989 )", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

}