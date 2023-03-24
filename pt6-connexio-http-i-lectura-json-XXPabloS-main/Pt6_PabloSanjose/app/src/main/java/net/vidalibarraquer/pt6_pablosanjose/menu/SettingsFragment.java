package net.vidalibarraquer.pt6_pablosanjose.menu;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import net.vidalibarraquer.pt6_pablosanjose.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
