package com.example.uf2_pt2_pablosanjose;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModificaVehicle extends AppCompatActivity implements View.OnClickListener, ChildEventListener, ValueEventListener {

    private List<Vehicle> vehicles;
    private MyRecyclerViewAdapter adapter;

    public DatabaseReference dbParking;
    public RecyclerView viewLlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_vehicle);

        dbParking = FirebaseDatabase.getInstance().getReference().child("parking");
        dbParking.addChildEventListener(this);
        dbParking.addValueEventListener(this);

        vehicles = new ArrayList<Vehicle>();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MyRecyclerViewAdapter(vehicles, dbParking, viewLlista, 1);
        viewLlista = (RecyclerView) findViewById(R.id.viewLlista);
        viewLlista.setLayoutManager(llm);
        viewLlista.setHasFixedSize(true);
        viewLlista.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        //Toast.makeText(ModificaVehicle.this, "Has canviat " + dataSnapshot.getKey() + ": " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        //Toast.makeText(ModificaVehicle.this, "Has eliminat " + dataSnapshot.getKey() + ": " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Toast.makeText(ModificaVehicle.this, "Hi ha " + dataSnapshot.getChildrenCount() + " vehicles a la llista", Toast.LENGTH_SHORT).show();
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
        adapter.notifyDataSetChanged();
        viewLlista.scrollToPosition(vehicles.size() - 1);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}