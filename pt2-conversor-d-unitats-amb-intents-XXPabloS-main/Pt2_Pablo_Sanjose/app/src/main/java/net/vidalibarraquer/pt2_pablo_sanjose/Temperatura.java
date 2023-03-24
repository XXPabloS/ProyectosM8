package net.vidalibarraquer.pt2_pablo_sanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Temperatura extends AppCompatActivity implements View.OnClickListener{

    private EditText textInput;
    private TextView textResposta;
    private Button btn_converteix;
    private RadioButton rd_K1;
    private RadioButton rd_K2;
    private RadioButton rd_C1;
    private RadioButton rd_C2;
    private RadioButton rd_F1;
    private RadioButton rd_F2;
    private RadioButton rd_Ra1;
    private RadioButton rd_Ra2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        textResposta = findViewById(R.id.textResposta3);
        textInput = findViewById(R.id.txt_input3);
        rd_C1 = findViewById(R.id.rdBtn_mi1);
        rd_C2 = findViewById(R.id.rdBtn_mi2);
        rd_K1 = findViewById(R.id.rdBtn_Km1);
        rd_K2 = findViewById(R.id.rdBtn_Km2);
        rd_F1 = findViewById(R.id.rdBtn_yd1);
        rd_F2 = findViewById(R.id.rdBtn_yd2);
        rd_Ra1 = findViewById(R.id.rdBtn_pol1);
        rd_Ra2 = findViewById(R.id.rdBtn_pol2);

        btn_converteix = findViewById(R.id.button_converteix3);
        btn_converteix.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        try {
            double solucio;

            if (rd_K1.isChecked()){
                if (rd_K2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    textResposta.setText(String.valueOf(solucio));
                }

                if (rd_C2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio-=273.15;
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_F2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = solucio*9/5-459.67;
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_Ra2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = (solucio * 9) / 5;
                    textResposta.setText(String.valueOf(solucio));
                }

            }



            if (rd_C1.isChecked()){
                if (rd_K2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio+=273.15;
                    textResposta.setText(String.valueOf(solucio));
                }

                if (rd_C2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_F2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = solucio*9/5+32;
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_Ra2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = (solucio + 273.15) * 9 / 5;
                    textResposta.setText(String.valueOf(solucio));
                }

            }



            if (rd_F1.isChecked()){
                if (rd_K2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = (solucio + 459.67) *5/9;
                    textResposta.setText(String.valueOf(solucio));
                }

                if (rd_C2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = (solucio - 32) *5/9;
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_F2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_Ra2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = solucio + 459.67;
                    textResposta.setText(String.valueOf(solucio));
                }

            }


            if (rd_Ra1.isChecked()){
                if (rd_K2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = solucio *5/9;
                    textResposta.setText(String.valueOf(solucio));
                }

                if (rd_C2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio = (solucio - 459.67) *5/9;
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_F2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    solucio-= 459.67;
                    textResposta.setText(String.valueOf(solucio));
                }
                if (rd_Ra2.isChecked()){
                    solucio=Double.parseDouble(textInput.getText().toString());
                    textResposta.setText(String.valueOf(solucio));
                }





            }
            if (!rd_K1.isChecked() && !rd_C1.isChecked() && !rd_F1.isChecked() && !rd_Ra1.isChecked() || !rd_K2.isChecked() && !rd_C2.isChecked() && !rd_F2.isChecked() && !rd_Ra2.isChecked()){
                Toast.makeText(this,"Selecciona les unitats", Toast.LENGTH_LONG).show();

            }




        } catch (NumberFormatException e) {
            Toast.makeText(this,"Escriu un número", Toast.LENGTH_LONG).show();
        }


    }
}