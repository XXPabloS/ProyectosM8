package net.vidalibarraquer.pt2_pablo_sanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Longitud extends AppCompatActivity implements View.OnClickListener {


    private EditText textInput2;
    private TextView textResposta2;
    private Button btn_converteix2;
    private RadioButton rd_km1, rd_km2, rd_mi1, rd_mi2, rd_yd1, rd_yd2, rd_pol1, rd_pol2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longitud);

        textResposta2 = findViewById(R.id.textResposta2);
        textInput2 = findViewById(R.id.txt_input2);

        rd_km1 = findViewById(R.id.rdBtn_Km1);
        rd_km2 = findViewById(R.id.rdBtn_Km2);
        rd_mi1 = findViewById(R.id.rdBtn_mi1);
        rd_mi2 = findViewById(R.id.rdBtn_mi2);
        rd_yd1 = findViewById(R.id.rdBtn_yd1);
        rd_yd2 = findViewById(R.id.rdBtn_yd2);
        rd_pol1 = findViewById(R.id.rdBtn_pol1);
        rd_pol2 = findViewById(R.id.rdBtn_pol2);

        btn_converteix2 = findViewById(R.id.button_converteix2);
        btn_converteix2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        try{
            double solucio;

            if (rd_km1.isChecked()){
                if (rd_km2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    textResposta2.setText(String.valueOf(solucio));
                }

                if (rd_mi2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio*=	0.6213712;
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_yd2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio*=   	1093.613;
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_pol2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *=  39370.08;
                    textResposta2.setText(String.valueOf(solucio));
                }

            }



            if (rd_mi1.isChecked()){
                if (rd_km2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio*=   	1.609344;
                    textResposta2.setText(String.valueOf(solucio));
                }

                if (rd_mi2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_yd2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *= 1760;
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_pol2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *= 63360;
                    textResposta2.setText(String.valueOf(solucio));
                }

            }



            if (rd_yd1.isChecked()){
                if (rd_km2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *= 	0.0009144;
                    textResposta2.setText(String.valueOf(solucio));
                }

                if (rd_mi2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *= 0.0005681818;
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_yd2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_pol2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *= 36;
                    textResposta2.setText(String.valueOf(solucio));
                }

            }


            if (rd_pol1.isChecked()){
                if (rd_km2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *= 0.0000254;
                    textResposta2.setText(String.valueOf(solucio));
                }

                if (rd_mi2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio *= 0.00001578283;
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_yd2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    solucio*= 0.02777778;
                    textResposta2.setText(String.valueOf(solucio));
                }
                if (rd_pol2.isChecked()){
                    solucio=Double.parseDouble(textInput2.getText().toString());
                    textResposta2.setText(String.valueOf(solucio));
                }

            }

            if (!rd_km1.isChecked() && !rd_mi1.isChecked() && !rd_yd1.isChecked() && !rd_pol1.isChecked() || !rd_km2.isChecked() && !rd_mi2.isChecked() && !rd_yd2.isChecked() && !rd_pol2.isChecked()){
                Toast.makeText(this,"Selecciona les unitats", Toast.LENGTH_LONG).show();

            }

        } catch (NumberFormatException e) {
            Toast.makeText(this,"Escriu un número", Toast.LENGTH_LONG).show();
        }


    }
}