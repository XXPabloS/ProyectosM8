package net.vib.dam.stocks_template;


import android.annotation.SuppressLint;
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

public class ModificaStock extends AppCompatActivity implements View.OnClickListener, ChildEventListener, ValueEventListener {

    private List<Stock> stocks;
    private StockAdapter adapter;

    public DatabaseReference dbStocks;

    public RecyclerView rvStocks;


    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();

        super.onResume();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_stock);

        dbStocks = FirebaseDatabase.getInstance().getReference().child("stocks");
        dbStocks.addChildEventListener(this);
        dbStocks.addValueEventListener(this);

        stocks = new ArrayList<Stock>();
        rvStocks = (RecyclerView) findViewById(R.id.rvStocks);
        rvStocks.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StockAdapter(this, dbStocks, stocks);
        rvStocks.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Toast.makeText(ModificaStock.this, "Has canviat " + dataSnapshot.getKey() + ": " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Toast.makeText(ModificaStock.this, "Has eliminat " + dataSnapshot.getKey() + ": " + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Toast.makeText(ModificaStock.this, "Hi ha " + dataSnapshot.getChildrenCount() + " stocks a la llista", Toast.LENGTH_SHORT).show();
        if (stocks.size() > 0) {
            stocks.removeAll(stocks);
        }
        for (DataSnapshot element : dataSnapshot.getChildren()) {
            Stock st = new Stock();
            System.out.println(element);
            st.setId(element.getKey().toString());
            st.setLatitude(Double.valueOf(element.child("latitude").getValue().toString()));
            st.setLongitude(Double.valueOf(element.child("longitude").getValue().toString()));
            st.setStock_name(element.child("stock_name").getValue().toString());

            stocks.add(st);
        }
        adapter.notifyDataSetChanged();
        rvStocks.scrollToPosition(stocks.size() - 1);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}