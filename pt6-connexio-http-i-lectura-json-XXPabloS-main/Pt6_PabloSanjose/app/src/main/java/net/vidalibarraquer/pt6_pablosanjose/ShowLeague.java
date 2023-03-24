package net.vidalibarraquer.pt6_pablosanjose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ShowLeague extends AppCompatActivity {

    private RequestQueue queue = null;

    TextView stadium;
    TextView nom;
    TextView codi;
    TextView fundated;

    private ImageView logo = null;
    private String fundacio = null;
    private String estadi = null;
    private String URLimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_league);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String key = intent.getStringExtra("key");
        final String name = intent.getStringExtra("name");
        final String code = intent.getStringExtra("code");
        final String URLDefault = intent.getStringExtra("defaultURL");



        stadium = (TextView) findViewById(R.id.textView);
        nom = (TextView) findViewById(R.id.textView2);
        codi = (TextView) findViewById(R.id.textView3);
        fundated = (TextView) findViewById(R.id.textView4);
        logo = (ImageView) findViewById(R.id.icono);


        getExtras(code, URLDefault);

        ImageRequest imageRequest = new ImageRequest(URLimg, new Response.Listener<Bitmap>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(final Bitmap response) {
                nom.setText(getString(R.string.name) + ": " + name);
                codi.setText(getString(R.string.code) + ": " + code);
                stadium.setText(getString(R.string.estadiStr) + ": " + estadi);
                fundated.setText(getString(R.string.fundacioStr) + ": " + fundacio);
                logo.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowLeague.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ShowLeague.this);
        requestQueue.add(imageRequest);
    }

    private void getExtras(String code, String URLDefault) {
        String preURL = code.toLowerCase(Locale.ROOT);
        URLimg = URLDefault + "/" + preURL + ".png";
        String URLjson = URLDefault + "/" + preURL + ".json";

        if (hiHaConnexio()) {
            if (queue == null)
                queue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest(URLjson, null,
                    new Response.Listener<JSONObject>() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    fundacio = jsonArray.getJSONObject(i).getString("founded");
                                    estadi = jsonArray.getJSONObject(i).getString("ground");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ShowLeague.this, "Error en obtenir dades", Toast.LENGTH_SHORT).show();
                        }
                    });
            queue.add(request);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean hiHaConnexio() {
        boolean resultat = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    resultat = true;
                }
            }
        } else {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                resultat = true;
            } else {
                resultat = false;
            }
        }

        return resultat;
    }
}