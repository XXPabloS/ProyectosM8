package net.vidalibarraquer.pablosanjose_examen;

        import android.content.Context;
        import android.net.ConnectivityManager;
        import android.net.NetworkCapabilities;
        import android.net.NetworkInfo;
        import android.os.Build;
        import android.view.View;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import androidx.recyclerview.widget.RecyclerView;
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

public class MainActivity extends AppCompatActivity {
    private final List<Torneig> elements = new ArrayList<Torneig>();
    private RequestQueue queue = null;

    public List<Torneig> getElements() {
        return elements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(elements);

        RecyclerView viewLlista = findViewById(R.id.viewLlista);
        viewLlista.setAdapter(adapter);

        findViewById(R.id.btnLoadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiHaConnexio())
                    MainActivity.this.loadData(viewLlista, "https://www.vidalibarraquer.net/android/EXAMEN/TENNIS/masters.json");
                else
                    Toast.makeText(getApplicationContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Comprova si el dispositiu té connexió
     * @return cert si en té i fals si no en té
     */
    private boolean hiHaConnexio() {
        boolean resultat = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
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
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            elements.clear();
                            JSONArray jsonArray = response.getJSONArray("masters");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Torneig torneig = new Torneig(
                                        jsonArray.getJSONObject(i).getString("tourney_name"),
                                        jsonArray.getJSONObject(i).getString("tourney_location"),
                                        jsonArray.getJSONObject(i).getString("key")



                                );
                                System.out.println(torneig.gettourney_name());
                                elements.add(torneig);
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
                        Toast.makeText(MainActivity.this, "Error en obtenir dades", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }


}