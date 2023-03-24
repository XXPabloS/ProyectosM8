package net.vidalibarraquer.pablosanjose_examen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MostrarTorneig extends AppCompatActivity {
    List<Torneig> torneigs;
    private final List<Torneig> elements = new ArrayList<Torneig>();
    private RequestQueue queue2 = null;

    ImageView im1;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_torneos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);

        Intent intent = getIntent();

        final String tourney_name = intent.getStringExtra("tourney_name");
        final String surface = intent.getStringExtra("surface");
        final String prize = intent.getStringExtra("prize");
        final String key = intent.getStringExtra("key");

        final String imatge = key+".png";


        //Creem URL
        String URL = "https://www.vidalibarraquer.net/android/EXAMEN/TENNIS/logos/" + imatge;
        System.out.println(URL);

        im1 = (ImageView) findViewById(R.id.imageView);


        ImageRequest imageRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(final Bitmap response) {
                im1.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MostrarTorneig.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MostrarTorneig.this);
        requestQueue.add(imageRequest);

        String urlData = "https://www.vidalibarraquer.net/android/EXAMEN/TENNIS/" + key + ".json";
        queue2 = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(urlData, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            elements.clear();
                            JSONArray jsonArray = response.getJSONArray("data");
                            tv1.setText(jsonArray.getJSONObject(0).getString("venue"));
                            tv2.setText(jsonArray.getJSONObject(0).getString("surface"));
                            tv3.setText(jsonArray.getJSONObject(0).getString("prize"));
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MostrarTorneig.this, "Error en obtenir dades", Toast.LENGTH_SHORT).show();
                    }
                });
        queue2.add(request);

        bt1 = findViewById(R.id.boton);
       // Button.Onc(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View v) {
           //     Intent intent = new Intent(Intent.ACTION_VIEW);
            //    intent.setData(Uri.parse("geo:Coordenada 1,Coordenada 2"));
        //      //if (intent.resolveActivity(getPackageManager()) != null) {
        //      startActivity(intent);
                //}
        //       Toast toast1 =
        //               Toast.makeText(getApplicationContext(),
        //                        "Ho he intentat, no he tingut temps :c", Toast.LENGTH_SHORT);
        //        toast1.show();
        //    }
    //     });

                }

    }