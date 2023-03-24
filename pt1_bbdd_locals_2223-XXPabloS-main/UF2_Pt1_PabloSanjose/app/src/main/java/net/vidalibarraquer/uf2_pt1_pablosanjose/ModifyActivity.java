package net.vidalibarraquer.uf2_pt1_pablosanjose;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class ModifyActivity extends AppCompatActivity {
    private List<Vehicle> vehicles;

    public EditText etNom;
    public EditText etCognom;
    public EditText etTelefon;
    public EditText etMarca;
    public EditText etModel;
    public EditText etMatricula;
    public Button btnBuscar;
    public Button btnModificar;
    public RecyclerView viewLlista;

    ManegadorDades db = new ManegadorDades(ModifyActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etNom = findViewById(R.id.etNom);
        etCognom = findViewById(R.id.etCognom);
        etTelefon = findViewById(R.id.etTelefon);
        etMarca = findViewById(R.id.etMarca);
        etModel = findViewById(R.id.etModel);
        etMatricula = findViewById(R.id.etMatricula);
        btnBuscar = findViewById(R.id.searchButton);
        btnModificar = findViewById(R.id.modifyButton);

        btnBuscar.setOnClickListener(v -> {
            int id = Integer.parseInt(etMatricula.getText().toString());
            //busca el vehicle amb id
            Vehicle vehicle = db.vehicle(id);
            //si el vehicle existeix
            if (vehicle != null) {
                //mostra el vehicle
                etNom.setText(vehicle.getNom());
                etCognom.setText(vehicle.getCognom());
                etTelefon.setText(vehicle.getTelefon());
                etMarca.setText(vehicle.getMarca());
                etModel.setText(vehicle.getModel());
                etMatricula.setText(vehicle.getMatricula());
            }

        });
        btnModificar.setOnClickListener(v -> {
            Vehicle veh = new Vehicle();
            veh.setNom(etNom.getText().toString());
            veh.setCognom(etCognom.getText().toString());
            veh.setTelefon(etTelefon.getText().toString());
            veh.setMarca(etMarca.getText().toString());
            veh.setModel(etModel.getText().toString());
            veh.setMatricula(etMatricula.getText().toString());
            db.updateVehicle(veh);
            vehicles = db.getAllVehicles();

        });
    }
}