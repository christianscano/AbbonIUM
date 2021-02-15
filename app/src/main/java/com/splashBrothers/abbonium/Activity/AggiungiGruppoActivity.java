package com.splashBrothers.abbonium.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

public class AggiungiGruppoActivity extends AppCompatActivity {
    Utente utenteAttivo;

    Button btnCreaGruppo, btnUnisciLink;
    ImageButton btnBack;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_gruppo);

        btnBack = findViewById(R.id.btnBack);
        btnCreaGruppo = findViewById(R.id.btnCreaGruppo);
        btnUnisciLink = findViewById(R.id.btnUniscitiLink);
        link = findViewById(R.id.textLinkInvito);

        recoveryData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AggiungiGruppoActivity.this, HomeActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                startActivity(intent);
            }
        });

        btnCreaGruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AggiungiGruppoActivity.this, CreazioneGruppoActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                startActivity(intent);
            }
        });

        btnUnisciLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uniscitiLink();
            }
        });
    }

    /* --- FUNZIONI SUPPORTO --- */

    protected void recoveryData() {
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
    }

    protected void uniscitiLink() {
        if(link.getText() == null || link.getText().length() == 0)
            link.setError("Nessun link inserito");
        else {
            link.setError(null);
            Servizio servizio = findLinkGroup();

            if (servizio != null) {
                Intent intent = new Intent(AggiungiGruppoActivity.this, DettagliGruppoUniscitiActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                intent.putExtra("servizio", servizio);
                intent.putExtra("activity", "aggiungiActivity");
                startActivity(intent);
            } else
                link.setError("Link non valido");
        }
    }

    protected Servizio findLinkGroup() {
        for(Servizio s : LoginActivity.serviziGlobali) {
            if (s.getLinkCondivisione().equals(link.getText().toString()))
                return s;
        }

        return null;
    }

}