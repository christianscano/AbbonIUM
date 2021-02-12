package com.splashBrothers.abbonium.HomeFragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.splashBrothers.abbonium.ListaServiziHomeAdapter;
import com.splashBrothers.abbonium.LoginActivity;
import com.splashBrothers.abbonium.R;
import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Services.ServizioInfo;
import com.splashBrothers.abbonium.Data.Services.ServizioMarvelUnlimited;
import com.splashBrothers.abbonium.Data.Services.ServizioNetflix;
import com.splashBrothers.abbonium.Data.Utente;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    SearchView searchView;
    TextView benvenuto;
    RecyclerView listaServiziGlobali;

    ListaServiziHomeAdapter listaServiziHomeAdapter;
    Utente utenteAttivo;
    HashMap<String, Utente> utentiGlobali;
    ArrayList<Servizio> serviziGlobali;

    public HomeFragment(HashMap<String, Utente> utentiGlobali, ArrayList<Servizio> serviziGlobali, Utente utenteAttivo) {
        this.utenteAttivo = utenteAttivo;
        this.serviziGlobali = serviziGlobali;
        this.utentiGlobali = utentiGlobali;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaServiziGlobali = view.findViewById(R.id.listaGruppiDisponibili);
        benvenuto = view.findViewById(R.id.textBenvenuto);
        searchView = view.findViewById(R.id.searchBar);

        //Imposto la dimensione del recyclerView fissa
        listaServiziGlobali.setHasFixedSize(true); //Imposto la dimensione del recyclerView fissa
        listaServiziGlobali.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        listaServiziHomeAdapter = new ListaServiziHomeAdapter(serviziGlobali);
        listaServiziGlobali.setAdapter(listaServiziHomeAdapter);

        benvenuto.setText("Benvenuto " + utenteAttivo.getNome());

        //Modifico il font della searchView
        Typeface tf = ResourcesCompat.getFont(requireContext(), R.font.baloo);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = (TextView) searchView.findViewById(id);
        searchText.setTypeface(tf);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                listaServiziHomeAdapter.getFilter().filter(query);
                return false;
            }
        });

    }
}