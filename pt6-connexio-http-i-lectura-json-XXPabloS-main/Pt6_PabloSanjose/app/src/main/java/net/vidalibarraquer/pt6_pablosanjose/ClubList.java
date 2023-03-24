package net.vidalibarraquer.pt6_pablosanjose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClubList extends AppCompatActivity {

    private final List<League> elements = new ArrayList<>();
    private RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_list);

        Intent intent = getIntent();
        String defaultURL = intent.getStringExtra("URLDefault");
        String URL = defaultURL + ".json";

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(elements, defaultURL);


        RecyclerView viewLlista = findViewById(R.id.viewLlista);
        viewLlista.setAdapter(adapter);

        loadData(viewLlista, URL);
    }


    /**
     * Carega les dades que es troben a la url indicada
     * @param viewLlista el recyclerview que s'ha d'actualitzar
     * @param url Una url on hi ha un objecte JSON
     */
    private void loadData(RecyclerView viewLlista, String url) {
        if ( queue == null )
            queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            elements.clear();
                            JSONArray jsonArray = response.getJSONArray("clubs");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                League league = new League(
                                        jsonArray.getJSONObject(i).getString("key"),
                                        jsonArray.getJSONObject(i).getString("name"),
                                        jsonArray.getJSONObject(i).getString("code")
                                );
                                elements.add(league);
                            }
                            viewLlista.getAdapter().notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ClubList.this, "Error en obtenir dades", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }
}