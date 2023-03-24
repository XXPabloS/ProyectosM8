package net.vib.dam.stocks_template;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class StockAdapter  extends RecyclerView.Adapter<StockAdapter.ViewHolder> {
        private View mView;
        public List<Stock> llistaStocks;
        private Context context;
        private DatabaseReference databaseReference;

        public StockAdapter(Context context, DatabaseReference databaseReference, List<Stock> llistaStocks) {
            this.context = context;
            this.databaseReference = databaseReference;
            this.llistaStocks = llistaStocks;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView tvId;
            public TextView tvStockName;
            public TextView tvLatitude;
            public TextView tvLongitude;

            public ViewHolder(View itemView) {
                super(itemView);
                tvId = itemView.findViewById(R.id.tvId);
                tvStockName = itemView.findViewById(R.id.tvStockName);
                tvLatitude = itemView.findViewById(R.id.tvLatitude);
                tvLongitude = itemView.findViewById(R.id.tvLongitude);
                itemView.setOnClickListener(this); // Per escoltar els clicks (no oblidar!)
            }

            // Mètode a implementar de la interfície View.OnClickListener
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                mostraPopupMenu(v, position);
                //Toast.makeText(context, "Has clicat la posició: " + position, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_llista, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Stock item = llistaStocks.get(position);
            holder.tvId.setText(item.getId());
            holder.tvStockName.setText(item.getStock_name());
            holder.tvLatitude.setText(String.valueOf(item.getLatitude()));
            holder.tvLongitude.setText(String.valueOf(item.getLongitude()));
        }

        @Override
        public int getItemCount() {
            return llistaStocks.size();
        }

        private void mostraPopupMenu(View view, int position) {
            PopupMenu popupMenu = new PopupMenu(this.context, view);
            MenuInflater menuInflater = popupMenu.getMenuInflater();
            menuInflater.inflate(R.menu.menu_main, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new Menu(position));
            popupMenu.show();
        }

        public class Menu implements PopupMenu.OnMenuItemClickListener {
            Integer pos;

            public Menu(int pos) {
                this.pos = pos;
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Implementar cada opció del menú    
                    
                Stock stock;
                switch (menuItem.getItemId()) {
                    case R.id.mostraMapa:

                        return true;
                    case R.id.insereixStock:

                        return true;
                    case R.id.editaStock:
                        //databaseReference.child(llistaStocks.get(pos).getId()).setValue(stock);

                        return true;
                    case R.id.esborraStock:
                        databaseReference.child(llistaStocks.get(pos).getId()).removeValue();

                    default:
                }
                return false;
            }
        }

}
