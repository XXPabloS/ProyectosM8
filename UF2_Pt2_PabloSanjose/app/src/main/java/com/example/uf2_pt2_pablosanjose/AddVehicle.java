package com.example.uf2_pt2_pablosanjose;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddVehicle extends AppCompatActivity implements View.OnClickListener, ChildEventListener, ValueEventListener {

    private List<Vehicle> vehicles;
    private MyRecyclerViewAdapter adapter;

    public EditText etNom, etCognom, etTel, etMatricula, etMarca, etModel;
    public Button btnInserir;
    public DatabaseReference dbParking;
    private boolean checkRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        dbParking = FirebaseDatabase.getInstance().getReference().child("parking");
        dbParking.addChildEventListener(this);
        dbParking.addValueEventListener(this);

        vehicles = new ArrayList<Vehicle>();
        adapter = new MyRecyclerViewAdapter(vehicles, dbParking, 3);

        etNom = (EditText) findViewById(R.id.etNom);
        etCognom = (EditText) findViewById(R.id.etCognom);
        etTel = (EditText) findViewById(R.id.etTel);
        etMatricula = (EditText) findViewById(R.id.etMatricula);
        etMarca = (EditText) findViewById(R.id.etMarca);
        etModel = (EditText) findViewById(R.id.etModel);
        btnInserir = (Button) findViewById(R.id.add);

        btnInserir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {

            //if (comprovaRepetits(etMatricula.getText().toString())) {

            Vehicle vh = new Vehicle();
            vh.setNom(etNom.getText().toString());
            vh.setCognoms(etCognom.getText().toString());
            vh.setMatricula(etMatricula.getText().toString());
            vh.setModel(etModel.getText().toString());
            vh.setMarca(etMarca.getText().toString());
            vh.setTelefon(Integer.parseInt(etTel.getText().toString()));

            dbParking.child(vh.getMatricula()).setValue(vh);
            vehicles.add(vh);

            etNom.setText("");
            etCognom.setText("");
            etTel.setText("");
            etMatricula.setText("");
            etMarca.setText("");
            etModel.setText("");
            //}
        }
    }

    private boolean comprovaRepetits(String matricula) {
        checkRepeat = true;
        dbParking.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    if (data.getKey().equalsIgnoreCase(matricula)) {
                        checkRepeat = true;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        System.out.println(checkRepeat);
        return checkRepeat;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (vehicles.size() > 0) {
            vehicles.removeAll(vehicles);
        }
        for (DataSnapshot element : dataSnapshot.getChildren()) {
            Vehicle vh = new Vehicle();
            vh.setNom(element.child("nom").getValue().toString());
            vh.setCognoms(element.child("cognoms").getValue().toString());
            vh.setMatricula(element.child("matricula").getValue().toString());
            vh.setModel(element.child("model").getValue().toString());
            vh.setMarca(element.child("marca").getValue().toString());
            vh.setTelefon(Integer.parseInt(String.valueOf(element.child("telefon").getValue())));
            vehicles.add(vh);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}