package com.splashBrothers.abbonium.Activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ListaServiziAdapter extends RecyclerView.Adapter<ListaServiziAdapter.ServizioHolder> implements Filterable {

    ArrayList<Servizio> serviziDisponibili;
    ArrayList<Servizio> serviziDisponibiliFiltrati;
    ListaServiziClickInterface listaServiziClickInterface;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public ListaServiziAdapter(ArrayList<Servizio> servizi, String emailUtenteAttivo, boolean isPersonal, ListaServiziClickInterface listaServiziClickInterface) {
        //Controlla se la lista di servizi è gia stata filtrata, quindi se true non c'è bisogno di filtrarla
        if(!isPersonal) {
            serviziDisponibili = servizi.stream()
                    .filter(s -> !s.getCreatore().equals(emailUtenteAttivo) && s.getMembri().get(emailUtenteAttivo) == null)
                    .collect(Collectors.toCollection(ArrayList<Servizio>::new));
            serviziDisponibiliFiltrati = new ArrayList<>(serviziDisponibili);
        } else {
            serviziDisponibili = servizi;
            serviziDisponibiliFiltrati = new ArrayList<Servizio>(serviziDisponibili);
        }

        this.listaServiziClickInterface = listaServiziClickInterface;
    }

    @NonNull
    @Override
    public ServizioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_gruppo, parent, false);
        ServizioHolder holder = new ServizioHolder(v);
        return holder;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ServizioHolder holder, int position) {
        Servizio servizio = serviziDisponibiliFiltrati.get(position);
        holder.imageView.setImageResource(servizio.getImgSource());
        holder.nomeProprietario.setText(servizio.getMembri().get(servizio.getCreatore()).getNome());
        holder.nomeServizio.setText(servizio.getNomeServizio());

        switch (servizio.getFrequenzaRinnovo()) {
            case "Mensile":
                holder.costo.setText(String.format("%.2f € al mese", servizio.getCostoSingolo()));
                break;
            case "Annuale":
                holder.costo.setText(String.format("%.2f € all'anno", servizio.getCostoSingolo()));
        }

        holder.nPosti.setText(servizio.getMaxPosti() - servizio.getnPosti() + "/" + servizio.getMaxPosti() + " Posti liberi");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaServiziClickInterface.onItemClick(servizio);
            }
        });
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
