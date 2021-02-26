package com.splashBrothers.abbonium.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.Fragment.HomeFragment;
import com.splashBrothers.abbonium.Fragment.MyAccountFragment;
import com.splashBrothers.abbonium.Fragment.MyGroupFragment;
import com.splashBrothers.abbonium.R;


public class HomeActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    FloatingActionButton btnAddGruppo;

    Utente utenteAttivo;
    boolean isHome; //serve per capire quale fragment caricare
    boolean isFirst; //serve per capire se è il primo avvio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recoveryData();

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        btnAddGruppo = findViewById(R.id.btnAddGroup);

        bottomNavigationView.getMenu().getItem(3).setEnabled(false);

        if(isHome)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, HomeFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo)).commit();
        else {
            bottomNavigationView.setSelectedItemId(R.id.myGroup);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, MyGroupFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo)).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = HomeFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo);
                        break;
                    case R.id.myGroup:
                        fragment = MyGroupFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo);
                        break;
                    case R.id.myAccount:
                        fragment = new MyAccountFragment().newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, fragment).commit();
                return true;
            }
        });

        btnAddGruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AggiungiGruppoActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                startActivity(intent);
            }
        });

        if(isFirst)
            showAlertDialogInfo();
    }

    /* --- FUNZIONI SUPPORTO --- */

    public void recoveryData() {
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
        isHome = intent.getBooleanExtra("isHome", true);
        isFirst = intent.getBooleanExtra("isFirst", false);

        updateUtenti();
    }

    protected void showAlertDialogInfo() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HomeActivity.this);
        View layoutView = getLayoutInflater().inflate(R.layout.alert_dialog_message, null);
        TextView testo = layoutView.findViewById(R.id.testo);
        Button btnConferma = layoutView.findViewById(R.id.btnConferma);
        dialogBuilder.setView(layoutView);
        AlertDialog alertDialog = dialogBuilder.create();

        testo.setText(Html.fromHtml("Benvenuto su AbbonIUM, in questa schermata potrai " +
                "visualizzare tutti i gruppi disponibili a cui potrai unirti oppure se vorrai, potrai direttamente" +
                " creare un tuo gruppo di condivisione\n" + "<b>Attenzione: Questa è una versione demo quindi potrai creare o unirti a gruppi di\n" +
                "        condivisione NETFLIX o DISNEY PLUS<\b>"));

        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    protected void updateUtenti() {
        LoginActivity.utentiGlobali.get(utenteAttivo.getEmail()).setMyGruppiCreati(utenteAttivo.getMyGruppiCreati());
        LoginActivity.utentiGlobali.get(utenteAttivo.getEmail()).setMyGruppiUnito(utenteAttivo.getMyGruppiUnito());
    }
}