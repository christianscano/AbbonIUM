package com.splashBrothers.abbonium.HomeFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splashBrothers.abbonium.ListaServiziHomeAdapter;
import com.splashBrothers.abbonium.R;
import com.splashBrothers.abbonium.Services.Servizio;
import com.splashBrothers.abbonium.Services.ServizioInfo;
import com.splashBrothers.abbonium.Services.ServizioMarvelUnlimited;
import com.splashBrothers.abbonium.Services.ServizioNetflix;
import com.splashBrothers.abbonium.Utente;

import java.security.Provider;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListaServiziHomeAdapter listaServiziHomeAdapter;
    RecyclerView listaServizi;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaServizi = view.findViewById(R.id.listaGruppiDisponibili);

        listaServizi.setHasFixedSize(true);
        listaServizi.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        listaServiziHomeAdapter = new ListaServiziHomeAdapter(createListServizi());
        listaServizi.setAdapter(listaServiziHomeAdapter);
    }

    public ArrayList<Servizio> createListServizi() {
        ArrayList<Servizio> listaServizi = new ArrayList<>();

        listaServizi.add(new ServizioNetflix(
                15.99,
                ServizioInfo.TipoRinnovo.MENSILE,
                ServizioInfo.TipoRelazione.STESSO_NUCLEO_DOMESTICO,
                3,
                new Utente(
                        "cris417tian1234dnejdwekdnwe",
                        "Christian",
                        "Scano",
                        "123",
                        "jckxnbckj",
                        "01/01/1999"
                )));

        listaServizi.add(new ServizioMarvelUnlimited(
                8.99,
                ServizioInfo.TipoRinnovo.MENSILE,
                ServizioInfo.TipoRelazione.AMICI,
                3,
                new Utente(
                        "cris",
                        "Christian",
                        "Scano",
                        "123",
                        "jckxnbckj",
                        "01/01/1999"
                )));
        return listaServizi;
    }
}