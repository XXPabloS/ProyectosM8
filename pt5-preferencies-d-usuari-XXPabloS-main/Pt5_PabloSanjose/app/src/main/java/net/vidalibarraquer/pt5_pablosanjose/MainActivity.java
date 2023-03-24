package net.vidalibarraquer.pt5_pablosanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_opcions = (Button) findViewById(R.id.bt_Resum);
        btn_opcions.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LecturaOpcion.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // fer tres puntos para abrir el settings
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // click setting me manda a opcionapp classe
        int id = item.getItemId();

        Intent intent = new Intent(this, OpcionsApp.class);

        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}



