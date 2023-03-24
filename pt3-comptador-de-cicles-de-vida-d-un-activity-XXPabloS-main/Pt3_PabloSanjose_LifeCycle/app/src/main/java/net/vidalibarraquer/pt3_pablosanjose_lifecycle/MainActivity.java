package net.vidalibarraquer.pt3_pablosanjose_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public TextView crear, iniciar, reiniciar, resumir, pausar, detener, destruir;
    public TextView TVtitulo, TVcrear, TViniciar, TVreiniciar, TVresumir, TVpausar, TVdetener, TVdestruir;
    public int c1=0, c2=0, c3=0, c4=0, c5=0, c6=0, c7=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crear=findViewById(R.id.crear);
        iniciar=findViewById(R.id.iniciar);
        reiniciar=findViewById(R.id.reiniciar);
        resumir=findViewById(R.id.resumir);
        pausar=findViewById(R.id.pausar);
        detener=findViewById(R.id.detenir);
        destruir=findViewById(R.id.destruir);

        c1++;
        crear.setText(String.valueOf(c1));

    }

    @Override
    protected void onStart() {
        super.onStart();
        c2++;
        iniciar.setText(String.valueOf(c2));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        c3++;
        reiniciar.setText(String.valueOf(c3));
    }

    @Override
    protected void onResume() {
        super.onResume();
        c4++;
        resumir.setText(String.valueOf(c4));
    }

    @Override
    protected void onPause() {
        super.onPause();
        c5++;
        pausar.setText(String.valueOf(c5));
    }

    @Override
    protected void onStop() {
        c6++;
        detener.setText(String.valueOf(c6));
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        c7++;
        destruir.setText(String.valueOf(c7));
        Toast.makeText(this,"onDestroy = "+c7,Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}