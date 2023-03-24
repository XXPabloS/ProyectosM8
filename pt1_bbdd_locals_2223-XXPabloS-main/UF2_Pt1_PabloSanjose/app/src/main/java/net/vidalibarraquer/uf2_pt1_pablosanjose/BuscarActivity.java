package net.vidalibarraquer.uf2_pt1_pablosanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class BuscarActivity extends AppCompatActivity {
    public EditText etMatricula;
    public EditText etNom;
    public EditText etCognom;
    public EditText etTelefon;
    public Button btnBuscar;

    ManegadorDades db = new ManegadorDades(BuscarActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        etMatricula = findViewById(R.id.etMatricula);
        etNom = findViewById(R.id.etNom);
        etCognom = findViewById(R.id.etCognom);
        etTelefon = findViewById(R.id.etTelefon);
        btnBuscar = findViewById(R.id.searchButton);

        btnBuscar.setOnClickListener(v -> {
            String matricula = etMatricula.getText().toString();
            Vehicle vehicle = db.vehicleMatricula(matricula);
            if (vehicle != null) {
                etNom.setText(vehicle.getNom());
                etCognom.setText(vehicle.getCognom());
                etTelefon.setText(vehicle.getTelefon());
            }
        });
    }
}