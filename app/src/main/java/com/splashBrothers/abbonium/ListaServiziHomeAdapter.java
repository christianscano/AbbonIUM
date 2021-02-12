package com.splashBrothers.abbonium;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.splashBrothers.abbonium.Data.Services.Servizio;

import java.util.ArrayList;
import java.util.Collection;

public class ListaServiziHomeAdapter extends RecyclerView.Adapter<ListaServiziHomeAdapter.ServizioHolder> implements Filterable {

    ArrayList<Servizio> serviziDisponibili;
    ArrayList<Servizio> serviziDisponibiliFiltrati;

    public ListaServiziHomeAdapter(ArrayList<Servizio> servizi) {
        serviziDisponibili = servizi;
        serviziDisponibiliFiltrati = new ArrayList<>(servizi);
    }

    @NonNull
    @Override
    public ServizioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gruppo, parent, false);
        ServizioHolder holder = new ServizioHolder(v);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ServizioHolder holder, int position) {
        Servizio servizio = serviziDisponibiliFiltrati.get(position);
        holder.imageView.setImageResource(servizio.getImgSource());
        holder.nomeProprietario.setText(servizio.getMembri().get(servizio.getCreatore()).getNome());
        holder.nomeServizio.setText(servizio.getNomeServizio());

        switch (servizio.getFrequenzaRinnovo()) {
            case MENSILE:
                holder.costo.setText(servizio.getCostoTotale() + "€ al mese");
                break;
            case ANNUALE:
                holder.costo.setText(servizio.getCostoTotale() + "€ all'anno");
        }

        holder.nPosti.setText(servizio.getMaxPosti() - servizio.getnPosti() + "/" + servizio.getMaxPosti() + " Posti liberi");
    }

    /* --- FILTRO --- */
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence query) {
            ArrayList<Servizio> serviziFiltered = new ArrayList<>();

            if (query == null || query.length() == 0)
               serviziFiltered.addAll(serviziDisponibili);
            else {
                for (Servizio s : serviziDisponibili)
                    if (s.getNomeServizio().toLowerCase().contains(query.toString().toLowerCase()))
                        serviziFiltered.add(s);
            }

            FilterResults filterResult = new FilterResults();
            filterResult.count = serviziFiltered.size();
            filterResult.values = serviziFiltered;
            return filterResult;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            serviziDisponibiliFiltrati.clear();
            serviziDisponibiliFiltrati.addAll((Collection<? extends Servizio>) results.values);
            notifyDataSetChanged();
        }
    };

    /* --- END FILTRO --- */

    @Override
    public int getItemCount() {
        return serviziDisponibiliFiltrati.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    /* --- PLACEHOLDER --- */

    class ServizioHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nomeProprietario;
        TextView nomeServizio;
        TextView costo;
        TextView nPosti;

        public ServizioHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgCard);
            nomeProprietario = itemView.findViewById(R.id.textNomeUtenteCard);
            nomeServizio = itemView.findViewById(R.id.textNomeServizioCard);
            costo = itemView.findViewById(R.id.textCostoCard);
            nPosti = itemView.findViewById(R.id.textNumPostiCard);
        }
    }

}
