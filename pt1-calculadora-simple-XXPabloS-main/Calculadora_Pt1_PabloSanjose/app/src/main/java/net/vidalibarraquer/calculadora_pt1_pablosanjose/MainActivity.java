package net.vidalibarraquer.calculadora_pt1_pablosanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ed1;
    TextView tvresult;
    Button btsum;
    Button btres;
    Button btmult;
    Button btdiv;
    Button btig;
    double n1 = 0, n2 = 0;
    boolean Sumar, Restar, Mult, Div;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ed1 = findViewById(R.id.ed1);

        tvresult = findViewById(R.id.tvresult);

        btsum = findViewById(R.id.btsum);
        btsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().length() != 0) {
                    n1 = Float.parseFloat(ed1.getText() + "");
                    Sumar = true;
                    ed1.setText(null);
                    tvresult.setText(null);
                }

            }
        });

        btdiv = findViewById(R.id.btdiv);
        btdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().length() != 0) {
                    n1 = Float.parseFloat(ed1.getText() + "");
                    Div = true;
                    ed1.setText(null);
                    tvresult.setText(null);
                }
            }
        });

        btmult = findViewById(R.id.btmult);
        btmult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().length() != 0) {
                    n1 = Float.parseFloat(ed1.getText() + "");
                    Mult = true;
                    ed1.setText(null);
                    tvresult.setText(null);
                }
            }
        });

        btres = findViewById(R.id.btres);
        btres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed1.getText().length() != 0) {
                    n1 = Float.parseFloat(ed1.getText() + "");
                    Restar = true;
                    ed1.setText(null);
                    tvresult.setText(null);
                }
            }
        });

        btig = findViewById(R.id.btig);
        btig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Sumar || Restar || Mult || Div) {
                    n2 = Float.parseFloat(ed1.getText() + "");
                }

                if (Sumar) {
                    tvresult.setText(n1 + n2 + "");
                    Sumar = false;
                }

                if (Restar) {
                    tvresult.setText(n1 - n2 + "");
                    Restar = false;
                }

                if (Mult) {
                    tvresult.setText(n1 * n2 + "");
                    Mult = false;
                }

                if (Div) {
                    if  (n1 == 0 || n2 == 0){
                        tvresult.setText("No es pot fer");
                    } else {
                        tvresult.setText(n1 / n2 + "");
                        Div = false;
                    }

                }
            }
        });

    }

}