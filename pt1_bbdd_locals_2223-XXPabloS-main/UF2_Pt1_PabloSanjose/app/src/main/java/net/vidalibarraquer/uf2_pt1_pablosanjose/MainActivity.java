package net.vidalibarraquer.uf2_pt1_pablosanjose;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Vehicle> vehicles;

    public EditText etNom;
    public EditText etCognom;
    public EditText etTelefon;
    public EditText etMarca;
    public EditText etModel;
    public EditText etMatricula;
    public Button btnInserir;
    public Button btnModificar;
    public Button btnBuscar;
    public RecyclerView viewLlista;

    ManegadorDades db = new ManegadorDades(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicles = db.getAllVehicles();
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(vehicles);
        viewLlista = findViewById(R.id.viewLlista);
        viewLlista.setAdapter(adapter);

        etNom = findViewById(R.id.etNom);
        etCognom = findViewById(R.id.etCognom);
        etTelefon = findViewById(R.id.etTelefon);
        etMarca =  findViewById(R.id.etMarca);
        etModel =  findViewById(R.id.etModel);
        etMatricula = findViewById(R.id.etMatricula);

        btnInserir = findViewById(R.id.add);
        btnInserir.setOnClickListener(this);
        btnModificar = findViewById(R.id.modify);
        btnModificar.setOnClickListener(this);
        btnBuscar = findViewById(R.id.buttonSearch);
        btnBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.add) {
            Vehicle veh = new Vehicle();
            veh.setNom(etNom.getText().toString());
            veh.setCognom(etCognom.getText().toString());
            veh.setTelefon(etTelefon.getText().toString());
            veh.setMarca(etMarca.getText().toString());
            veh.setModel(etModel.getText().toString());
            veh.setMatricula(etMatricula.getText().toString());


            db.addVehicles(veh);

            //Buscar si la matrícula coincide
            etNom.setText("");
            etCognom.setText("");
            etTelefon.setText("");
            etMarca.setText("");
            etModel.setText("");
            etMatricula.setText("");

            vehicles.add(veh);
            //Notificar que s'ha afegit un element
            Objects.requireNonNull(viewLlista.getAdapter()).notifyItemInserted(vehicles.size()-1);

        }
        if (v.getId() == R.id.modify) {
            Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.buttonSearch) {
            Intent intent = new Intent(MainActivity.this, BuscarActivity.class);
            startActivity(intent);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        vehicles = db.getAllVehicles();
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(vehicles);
        viewLlista.setAdapter(adapter);
    }
}
