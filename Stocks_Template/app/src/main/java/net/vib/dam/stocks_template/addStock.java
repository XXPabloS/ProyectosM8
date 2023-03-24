package net.vib.dam.stocks_template;

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

public class addStock extends AppCompatActivity implements View.OnClickListener, ChildEventListener, ValueEventListener {

    private List<Stock> stocks;
    private StockAdapter adapter;

    public EditText etId, etCountry, etStockIndustry, etStockMarket, etStockMarketCap, etStockName, etStockSector, etStockSymbol, etLatitude, etLongitude;
    public Button btnInserir;
    public DatabaseReference dbStocks;
    private boolean checkRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        dbStocks = FirebaseDatabase.getInstance().getReference().child("stocks");
        dbStocks.addChildEventListener(this);
        dbStocks.addValueEventListener(this);

        stocks = new ArrayList<Stock>();
        adapter = new StockAdapter(this, dbStocks, stocks);

        etId = (EditText) findViewById(R.id.etId);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etStockIndustry = (EditText) findViewById(R.id.etStockIndustry);
        etStockMarket = (EditText) findViewById(R.id.etStockMarket);
        etStockMarketCap = (EditText) findViewById(R.id.etStockMarketCap);
        etStockName = (EditText) findViewById(R.id.etStockName);
        etStockSector = (EditText) findViewById(R.id.etStockSector);
        etStockSymbol = (EditText) findViewById(R.id.etStockSymbol);
        etLatitude = (EditText) findViewById(R.id.etLatitude);
        etLongitude = (EditText) findViewById(R.id.etLongitude);
        btnInserir = (Button) findViewById(R.id.add);

        btnInserir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {

            //if (comprovaRepetits(etMatricula.getText().toString())) {

            Stock st = new Stock();
            st.setId(etId.getText().toString());
            st.setCountry(etCountry.getText().toString());
            st.setLatitude(Double.valueOf(etLatitude.getText().toString()));
            st.setLongitude(Double.valueOf(etLongitude.getText().toString()));
            st.setStock_industry(etStockIndustry.getText().toString());
            st.setStock_market(etStockMarket.getText().toString());
            st.setStock_market_cap(etStockMarketCap.getText().toString());
            st.setStock_name(etStockName.getText().toString());
            st.setStock_sector(etStockSector.getText().toString());
            st.setStock_symbol(etStockSymbol.getText().toString());

            adapter.notifyDataSetChanged();


            dbStocks.child(st.getId()).setValue(st);
            stocks.add(st);

            etId.setText("");
            etCountry.setText("");
            etStockIndustry.setText("");
            etStockMarket.setText("");
            etStockMarketCap.setText("");
            etStockName.setText("");
            etStockSector.setText("");
            etStockSymbol.setText("");
            etLatitude.setText("");
            etLongitude.setText("");
            //}

        }
    }

    private boolean comprovaRepetits(String id) {
        checkRepeat = true;
        dbStocks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    if (data.getKey().equalsIgnoreCase(id)) {
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
        if (stocks.size() > 0) {
            stocks.removeAll(stocks);
        }
        for (DataSnapshot element : dataSnapshot.getChildren()) {
            Stock st = new Stock();
            //System.out.println(element);
            st.setId(element.getKey().toString());
            st.setCountry(element.child("country").getValue().toString());
            st.setLatitude(Double.valueOf(element.child("latitude").getValue().toString()));
            st.setLongitude(Double.valueOf(element.child("longitude").getValue().toString()));
            st.setStock_industry(element.child("stock_industry").getValue().toString());
            st.setStock_market(element.child("stock_market").getValue().toString());
            st.setStock_market_cap(element.child("stock_market_cap").getValue().toString());
            st.setStock_name(element.child("stock_name").getValue().toString());
            st.setStock_sector(element.child("stock_sector").getValue().toString());
            st.setStock_symbol(element.child("stock_symbol").getValue().toString());

            stocks.add(st);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}