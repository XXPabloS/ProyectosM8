package net.vidalibarraquer.pt4_pablosanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button Boton_alarma;
    private Button Boton_mapas;
    public String message = "Funciona";
    public int minutes;
    public int hour;
    public EditText TextoHoras;
    public EditText TextoMinutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boton_alarma =findViewById(R.id.Boton_alarma);
        Boton_alarma.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
              TextoHoras =findViewById(R.id.TextoHoras);
              TextoMinutos =findViewById(R.id.TextoMinutos);
              String horas = TextoHoras.getText().toString();
              String minutos = TextoMinutos.getText().toString();
            if (horas.equals("") || minutos.equals("")){
                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                "Introduce bien la hora bobi", Toast.LENGTH_SHORT);
                toast2.show();
            } else {
                hour = Integer.parseInt(TextoHoras.getText().toString());
                minutes = Integer.parseInt(TextoMinutos.getText().toString());
                if (hour < 24 && hour >= 0 && minutes < 60 && minutes >= 0){
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                            .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                            .putExtra(AlarmClock.EXTRA_HOUR, hour)
                            .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
                    // if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                    //}
            }else {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce una hora correcta", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }

        }
    });

        Boton_mapas =findViewById(R.id.Boton_mapas);
        Boton_mapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:39.90569769959284,116.39763411838068"));
                //if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                //}
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Nothing happened here", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

}
}