package com.splashBrothers.abbonium.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.splashBrothers.abbonium.Activity.DettagliGruppoUniscitiActivity;
import com.splashBrothers.abbonium.Activity.ListaServiziAdapter;
import com.splashBrothers.abbonium.Activity.ListaServiziClickInterface;
import com.splashBrothers.abbonium.R;
import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment implements ListaServiziClickInterface {

    SearchView searchView;
    TextView benvenuto;
    RecyclerView listaServiziGlobali;

    ListaServiziAdapter listaServiziAdapter;
    Utente utenteAttivo;
    HashMap<String, Utente> utentiGlobali;
    ArrayList<Servizio> serviziGlobali;

    public HomeFragment() { }

    public static HomeFragment newInstance(HashMap<String, Utente> utentiGlobali, ArrayList<Servizio> serviziGlobali, Utente utenteAttivo) {
        HomeFragment myFragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putSerializable("utentiGlobali", utentiGlobali);
        args.putSerializable("serviziGlobali", serviziGlobali);
        args.putSerializable("utenteAttivo", utenteAttivo);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        utenteAttivo = (Utente) getArguments().getSerializable("utenteAttivo");
        utentiGlobali = (HashMap<String, Utente>) getArguments().getSerializable("utentiGlobali");
        serviziGlobali = (ArrayList<Servizio>) getArguments().getSerializable("serviziGlobali");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaServiziGlobali = view.findViewById(R.id.listaGruppiDisponibili);
        benvenuto = view.findViewById(R.id.textBenvenuto);
        searchView = view.findViewById(R.id.searchBar);

        //Imposto benvenuto
        benvenuto.setText("Benvenuto " + utenteAttivo.getNome());

        //Imposto la dimensione del recyclerView fissa
        listaServiziGlobali.setHasFixedSize(true); //Imposto la dimensione del recyclerView fissa
        listaServiziGlobali.setLayoutManager(new GridLayoutManager(getContext(), 2));

        listaServiziAdapter = new ListaServiziAdapter(serviziGlobali, utenteAttivo.getEmail(), false, this);
        listaServiziGlobali.setAdapter(listaServiziAdapter);

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
                listaServiziAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @SuppressLint("ShowToast")
    @Override
    public void onItemClick(Servizio servizio) {
        Intent intent = new Intent(getActivity(), DettagliGruppoUniscitiActivity.class);

        intent.putExtra("servizio", servizio);
        intent.putExtra("utenteAttivo", utenteAttivo);
        intent.putExtra("activity", "homeActivity");
        startActivity(intent);
    }
}