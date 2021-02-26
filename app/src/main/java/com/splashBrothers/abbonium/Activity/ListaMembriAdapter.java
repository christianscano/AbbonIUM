package com.splashBrothers.abbonium.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

import java.util.ArrayList;

public class ListaMembriAdapter extends RecyclerView.Adapter<ListaMembriAdapter.MembroHolder> {

    ArrayList<Utente> listaMembri;

    public ListaMembriAdapter(ArrayList<Utente> listaMembri) {
       this.listaMembri = listaMembri;
    }

    @NonNull
    @Override
    public MembroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_membro, parent, false);
        MembroHolder holder = new MembroHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MembroHolder holder, int position) {
        Utente u = listaMembri.get(position);
        holder.nomeMembro.setText(u.getNome() + " " + u.getCognome());
    }

    @Override
    public int getItemCount() {
        return listaMembri.size();
    }

    /* --- PLACEHOLDER --- */

    class MembroHolder extends RecyclerView.ViewHolder {
        TextView nomeMembro;

        public MembroHolder(@NonNull View itemView) {
            super(itemView);
            nomeMembro = itemView.findViewById(R.id.nomeUtente);
        }
    }
}
