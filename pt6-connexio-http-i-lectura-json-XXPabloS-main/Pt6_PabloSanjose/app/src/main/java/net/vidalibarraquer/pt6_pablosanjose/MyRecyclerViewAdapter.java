package net.vidalibarraquer.pt6_pablosanjose;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<League> elements;
    private String defaultURL;

    public MyRecyclerViewAdapter(List<League> elements, String defaultURL) {
        this.elements = elements;
        this.defaultURL = defaultURL;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View viewElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);

        return new ViewHolder(viewElement);
    }



    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.getTxtElement().setText(elements.get(position).getName());
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

            Intent mostrarLliga = new Intent(v.getContext(), ShowLeague.class);
            League league = elements.get(getAdapterPosition());
            mostrarLliga.putExtra("key", league.getKey());
            mostrarLliga.putExtra("name", league.getName());
            mostrarLliga.putExtra("code", league.getCode());
            mostrarLliga.putExtra("defaultURL" ,defaultURL);
            v.getContext().startActivity(mostrarLliga);

        }

        public TextView getTxtElement() {
            return txtElement;
        }
    }

}
