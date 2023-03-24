package net.vidalibarraquer.pt5_pablosanjose;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;

public class LecturaOpcion extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lectura_opcions);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        // mostrar nombre
        String nomCognom = sharedPreferences.getString("nom","");

        TextView tv = (TextView) findViewById(R.id.tv1);

        tv.setText("Nom i cognom: " + nomCognom);


        System.out.println("Hola  " + nomCognom);
        //mostrar razas
        Set<String> llistaRaces = sharedPreferences.getStringSet("races", new HashSet<String>());

        TextView tv2 = (TextView) findViewById(R.id.tv4);

        tv2.setText("Llista de raçes: " + llistaRaces);

        // mostrar notificacion

        String notificacions = String.valueOf(sharedPreferences.getBoolean("not",false));

        TextView tv3 = (TextView) findViewById(R.id.tv2);

        tv3.setText("Notificacions: " + notificacions);


        // mostrar pelicula preferida

        String peliculaFav = sharedPreferences.getString("pelis","");

        TextView tv4 = (TextView) findViewById(R.id.tv3);

        tv4.setText("Pel·lícula preferida: " + peliculaFav);


    }
}
