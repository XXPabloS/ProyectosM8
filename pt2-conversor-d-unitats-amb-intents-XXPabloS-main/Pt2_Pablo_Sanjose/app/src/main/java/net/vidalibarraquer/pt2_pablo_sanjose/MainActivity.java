package net.vidalibarraquer.pt2_pablo_sanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view){
        Intent miIntent = null;

        switch (view.getId()){
            case R.id.btn_temperatura:
                miIntent = new Intent(this,Temperatura.class);
                break;
            case R.id.btn_longitud:
                miIntent = new Intent(this,Longitud.class);
                break;
            case R.id.btn_massa:
                miIntent = new Intent(this,Pes.class);
                break;

        }
        startActivity(miIntent);
    }

}