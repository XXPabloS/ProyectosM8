package net.vidalibarraquer.pt6_pablosanjose.menu;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import net.vidalibarraquer.pt6_pablosanjose.MainActivity;
import net.vidalibarraquer.pt6_pablosanjose.R;

public class ActivityProperties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new net.vidalibarraquer.pt6_pablosanjose.menu.SettingsFragment())
                .commit();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        MainActivity.URL = String.valueOf(sharedPreferences.getString("url", "https://www.vidalibarraquer.net/android/futbol/"));
        super.onDestroy();
    }
}
