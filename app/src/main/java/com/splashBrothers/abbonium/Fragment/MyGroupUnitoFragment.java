package com.splashBrothers.abbonium.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.splashBrothers.abbonium.Activity.DettagliGruppoActivity;
import com.splashBrothers.abbonium.Activity.ListaServiziClickInterface;
import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.Activity.ListaServiziAdapter;
import com.splashBrothers.abbonium.R;

import java.io.Serializable;
import java.util.ArrayList;

//Fragment della tabView del myGroupFragment
public class MyGroupUnitoFragment extends Fragment implements ListaServiziClickInterface {

    ArrayList<Servizio> serviziGlobali;
    Utente utenteAttivo;
    ListaServiziAdapter listaServiziAdapter;

    RecyclerView listaGruppi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mygroup_unito, container, false);
    }

    public static MyGroupUnitoFragment newInstance(ArrayList<Servizio> serviziGlobali, Utente utenteAttivo) {
        MyGroupUnitoFragment myFragment = new MyGroupUnitoFragment();

        Bundle args = new Bundle();
        args.putSerializable("utenteAttivo", utenteAttivo);
        args.putSerializable("serviziGlobali", serviziGlobali);
        myFragment.setArguments(args);

        return myFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        utenteAttivo = (Utente) getArguments().getSerializable("utenteAttivo");
        serviziGlobali = (ArrayList<Servizio>) getArguments().getSerializable("serviziGlobali");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listaGruppi = view.findViewById(R.id.listaGruppi);

        listaGruppi.setHasFixedSize(true);
        listaGruppi.setLayoutManager(new GridLayoutManager(getContext(), 2));

        listaServiziAdapter = new ListaServiziAdapter(new ArrayList<>(utenteAttivo.getMyGruppiUnito().values()), utenteAttivo, true, this);

        listaGruppi.setAdapter(listaServiziAdapter);
    }

    @Override
    public void onItemClick(Servizio servizio) {
        Intent intent = new Intent(getActivity(), DettagliGruppoActivity.class);
        intent.putExtra("utenteAttivo", utenteAttivo);
        intent.putExtra("servizio", servizio);
        startActivity(intent);
    }
}
