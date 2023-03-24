package net.vib.dam.stocks_template;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, ValueEventListener, ChildEventListener {

    public Button btnInserir, btnModificaElimina, BtnConsultar;
    public RecyclerView rvStocks;

    private StockAdapter adapter;
    public DatabaseReference dbStocks;

    private List<Stock> stocks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbStocks = FirebaseDatabase.getInstance().getReference().child("stocks");
        dbStocks.addChildEventListener(this);
        dbStocks.addValueEventListener(this);

        stocks = new ArrayList<Stock>();
        rvStocks = (RecyclerView) findViewById(R.id.rvStocks);
        btnInserir = (Button) findViewById(R.id.add);
        btnModificaElimina = (Button) findViewById(R.id.modify);
        BtnConsultar = (Button) findViewById(R.id.search);

        rvStocks.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StockAdapter(this, dbStocks, stocks);

        rvStocks.setAdapter(adapter);

        btnInserir.setOnClickListener(this);
        btnModificaElimina.setOnClickListener(this);
        BtnConsultar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent moveScreen;
        if (v.getId() == R.id.add) {
            moveScreen = new Intent(this, addStock.class);
        } else if (v.getId() == R.id.modify) {
            moveScreen = new Intent(this, ModificaStock.class);
        } else {
            moveScreen = new Intent();
            //this, ConsultaStock.class
        }
        startActivity(moveScreen);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Toast.makeText(this, "Hi ha " + dataSnapshot.getChildrenCount() + " stocks a la llista", Toast.LENGTH_SHORT).show();
        if (stocks.size() > 0) {
            stocks.removeAll(stocks);
        }
        for (DataSnapshot element : dataSnapshot.getChildren()) {
            Stock st = new Stock();

            //System.out.println(element.child("country"));
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
    public void onCancelled(@NonNull DatabaseError error) {

    }
}