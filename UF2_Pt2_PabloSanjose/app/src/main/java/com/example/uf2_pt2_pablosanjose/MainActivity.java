package com.example.uf2_pt2_pablosanjose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btnInserir, btnModificaElimina, BtnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInserir = (Button) findViewById(R.id.add);
        btnModificaElimina = (Button) findViewById(R.id.modify);
        BtnConsultar = (Button) findViewById(R.id.search);

        btnInserir.setOnClickListener(this);
        btnModificaElimina.setOnClickListener(this);
        BtnConsultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent moveScreen;
        if (v.getId() == R.id.add) {
            moveScreen = new Intent(this, AddVehicle.class);
        } else if (v.getId() == R.id.modify) {
            moveScreen = new Intent(this, ModificaVehicle.class);
        } else {
            moveScreen = new Intent(this, ConsultaMatricula.class);
        }
        startActivity(moveScreen);
    }
}