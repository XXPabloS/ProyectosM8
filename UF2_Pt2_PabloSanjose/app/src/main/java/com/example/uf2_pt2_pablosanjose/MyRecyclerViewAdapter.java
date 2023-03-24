package com.example.uf2_pt2_pablosanjose;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Vehicle> elements;
    private int origen;
    private RecyclerView viewLlista;
    private DatabaseReference db;

    public MyRecyclerViewAdapter(List<Vehicle> elements, DatabaseReference dbParking, RecyclerView viewLlista, int origen) {
        this.elements = elements;
        this.origen = origen;
        this.viewLlista = viewLlista;
        this.db = dbParking;
    }

    public MyRecyclerViewAdapter(List<Vehicle> vehicles, DatabaseReference dbParking, int origen) {
        this.elements = vehicles;
        this.origen = origen;
        this.db = dbParking;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View viewElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        return new ViewHolder(viewElement);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        int count = position + 1;
        holder.getTxtElement().setText("Vehicle " + count + ": " + elements.get(position).getMatricula());
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
                    if (origen == 1) {
                        ViewHolder.this.modificaElimina(v);
                    } else {
                        ViewHolder.this.mostraPropietari(v);
                    }
                }
            });
            txtElement = itemView.findViewById(R.id.textElement);
        }

        @SuppressLint("SetTextI18n")
        private void modificaElimina(View v) {
            //Al clicar l'element podem modificar o eliminar el registre
            //ManegadorDades db = new ManegadorDades(v.getContext());
            Vehicle vehicle = elements.get(getAdapterPosition());
            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
            Context context = v.getContext();
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);

            // Crea tots els elements neccesaris
            final TextView nomTxt = new TextView(context);
            final EditText nomBox = new EditText(context);
            final TextView CognomTxt = new TextView(context);
            final EditText CognomBox = new EditText(context);
            final TextView TelefonTxt = new TextView(context);
            final EditText TelefonBox = new EditText(context);
            final TextView MarcaTxt = new TextView(context);
            final EditText MarcaBox = new EditText(context);
            final TextView ModelTxt = new TextView(context);
            final EditText ModelBox = new EditText(context);

            // Crea noms // Crea pistes
            nomTxt.setText("Nom"); nomBox.setHint("Nom");
            CognomTxt.setText("Cognom"); CognomBox.setHint("Cognom");
            TelefonTxt.setText("Telefon"); TelefonBox.setHint("Telefon");
            MarcaTxt.setText("Marca"); MarcaBox.setHint("Marca");
            ModelTxt.setText("Model"); ModelBox.setHint("Model");

            // Estableix els valors per defecte del vehicle
            nomBox.setText(vehicle.getNom(), TextView.BufferType.EDITABLE);
            CognomBox.setText(vehicle.getCognoms(), TextView.BufferType.EDITABLE);
            TelefonBox.setText(String.valueOf(vehicle.getTelefon()), TextView.BufferType.EDITABLE);
            MarcaBox.setText(vehicle.getMarca(), TextView.BufferType.EDITABLE);
            ModelBox.setText(vehicle.getModel(), TextView.BufferType.EDITABLE);

            // Afegeix els elements en ordre
            layout.addView(nomTxt);
            layout.addView(nomBox);
            layout.addView(CognomTxt);
            layout.addView(CognomBox);
            layout.addView(TelefonTxt);
            layout.addView(TelefonBox);
            layout.addView(MarcaTxt);
            layout.addView(MarcaBox);
            layout.addView(ModelTxt);
            layout.addView(ModelBox);

            alert.setTitle(R.string.opcions);
            alert.setView(layout);
            alert.setPositiveButton(R.string.modifica,
                new DialogInterface.OnClickListener(){
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Vehicle vh = new Vehicle();
                        vh.setNom(nomBox.getText().toString());
                        vh.setCognoms(CognomBox.getText().toString());
                        vh.setMatricula(vehicle.getMatricula());
                        vh.setModel(ModelBox.getText().toString());
                        vh.setMarca(MarcaBox.getText().toString());
                        vh.setTelefon(Integer.parseInt(TelefonBox.getText().toString()));
                        elements.add(vh);

                        db.child(vehicle.getMatricula()).setValue(vh);
                        //db.updateVehicle(vehicle);
                        Toast.makeText(v.getContext(),"El vehicle ha sigut modificat",Toast.LENGTH_SHORT).show();
                    }
                }
            );
            alert.setNegativeButton(R.string.elimina,
                    new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            //db.eliminaVehicle(vehicle);
                            db.child(elements.get(getAdapterPosition()).getMatricula()).removeValue();
                            elements.remove(vehicle);
                            // No acaba de funcionar
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), getItemCount());
                            Toast.makeText(v.getContext(),"Registre esborrat",Toast.LENGTH_SHORT).show();
                        }
                    });
            alert.setNeutralButton(R.string.sortir,null);

            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }

        @SuppressLint("SetTextI18n")
        private void mostraPropietari(View v) {
            //Al clicar l'element podem modificar o eliminar el registre
            //ManegadorDades db = new ManegadorDades(v.getContext());
            Vehicle vehicle = elements.get(getAdapterPosition());
            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
            Context context = v.getContext();
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);

            // Crea tots els elements neccesaris
            final TextView nomTxt = new TextView(context);
            final TextView CognomTxt = new TextView(context);
            final TextView TelefonTxt = new TextView(context);

            nomTxt.setTextSize(24);
            CognomTxt.setTextSize(24);
            TelefonTxt.setTextSize(24);

            nomTxt.setText("Nom: " + vehicle.getNom());
            CognomTxt.setText("Cognom: " + vehicle.getCognoms());
            TelefonTxt.setText("Telefon: " + String.valueOf(vehicle.getTelefon()));

            // Afegeix els elements en ordre
            layout.addView(nomTxt);
            layout.addView(CognomTxt);
            layout.addView(TelefonTxt);

            alert.setTitle(R.string.propietari);
            alert.setView(layout);
            alert.setPositiveButton(R.string.sortir,null);

            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }

        public TextView getTxtElement() {
            return txtElement;
        }
    }

}
