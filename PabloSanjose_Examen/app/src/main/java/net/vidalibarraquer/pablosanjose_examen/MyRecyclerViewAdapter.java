package net.vidalibarraquer.pablosanjose_examen;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Torneig> elements;

    public MyRecyclerViewAdapter(List<Torneig> elements) {
        this.elements = elements;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View viewElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);

        return new ViewHolder(viewElement);
    }


    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.getTxtElement().setText(new StringBuilder().append(elements.get(position).gettourney_name().toString()).toString());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtElement;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder.this.mostraElement(v);
                }
            });

            txtElement = itemView.findViewById(R.id.textElement);
        }

        private void mostraElement(View v) {

            Intent mostrarTorneig = new Intent(v.getContext(), MostrarTorneig.class);
            Torneig torneig = elements.get(getAdapterPosition());
            mostrarTorneig.putExtra("key", torneig.getkey());
            mostrarTorneig.putExtra("tourney_name", torneig.gettourney_name());
            mostrarTorneig.putExtra("tourney_location", torneig.gettourney_location());
            System.out.println(torneig.getkey());

            v.getContext().startActivity(mostrarTorneig);

        }

        public TextView getTxtElement() {
            return txtElement;
        }
    }

}
