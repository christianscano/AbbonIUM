package com.splashBrothers.abbonium.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.splashBrothers.abbonium.Activity.DettagliGruppoActivity;
import com.splashBrothers.abbonium.Activity.HomeActivity;
import com.splashBrothers.abbonium.Activity.LoginActivity;
import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAccountFragment extends Fragment {

    Utente utenteAttivo;
    ArrayList<Servizio> serviziGlobali;
    HashMap<String, Utente> utentiGlobali;

    TextView nomeUtente, email, password, dataNascita;
    Button btnLogout;

    public MyAccountFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    public static MyAccountFragment newInstance(HashMap<String, Utente> utentiGlobali, ArrayList<Servizio> serviziGlobali, Utente utenteAttivo) {
        MyAccountFragment myFragment = new MyAccountFragment();

        Bundle args = new Bundle();
        args.putSerializable("utentiGlobali", utentiGlobali);
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
        utentiGlobali = (HashMap<String, Utente>) getArguments().getSerializable("utentiGlobali");
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nomeUtente = view.findViewById(R.id.nomeUtente);
        email = view.findViewById(R.id.textEmail);
        password = view.findViewById(R.id.textPassword);
        dataNascita = view.findViewById(R.id.textDataNascita);
        btnLogout = view.findViewById(R.id.btnLogout);

        nomeUtente.setText(utenteAttivo.getNome() + " " + utenteAttivo.getCognome());
        email.setText(utenteAttivo.getEmail());
        password.setText(utenteAttivo.getPassword());
        dataNascita.setText(utenteAttivo.getDataNascita());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogExit("Vuoi uscire?");
            }
        });
    }

    /* --- FUNZIONI SUPPORTO --- */

    protected void showAlertDialogExit(String messaggio) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        View layoutView = getLayoutInflater().inflate(R.layout.alert_dialog_exit_group, null);
        TextView testo = layoutView.findViewById(R.id.testo);
        Button btnConferma = layoutView.findViewById(R.id.btnConferma);
        Button btnNegative = layoutView.findViewById(R.id.btnNegative);
        dialogBuilder.setView(layoutView);
        AlertDialog alertDialog = dialogBuilder.create();

        testo.setText(messaggio);

        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

}