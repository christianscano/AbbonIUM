package com.splashBrothers.abbonium.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

public class DettagliGruppoUniscitiActivity extends AppCompatActivity {

    Utente utenteAttivo;
    Servizio servizio;
    String activity; //Serve a capire da chi è stato lanciato

    ImageButton btnBack;
    Button btnEntra;
    TextView nomeProprietario, nomeServizio, nPosti, rinnovo, costo;
    ImageView imgBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_gruppo_unisciti);

        nomeProprietario = findViewById(R.id.textNomeUtente);
        nomeServizio = findViewById(R.id.textNomeServizio);
        nPosti = findViewById(R.id.textPostiLiberi);
        rinnovo = findViewById(R.id.textRinnovo);
        costo = findViewById(R.id.textCosto);
        btnBack = findViewById(R.id.btnBack);
        btnEntra = findViewById(R.id.btnEntraGruppo);
        imgBackground = findViewById(R.id.imgBackground);

        recoveryData();

        caricaDati();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                switch (activity) {
                    case "homeActivity":
                        intent = new Intent(DettagliGruppoUniscitiActivity.this, HomeActivity.class);
                        break;
                    case"aggiungiActivity":
                        intent = new Intent(DettagliGruppoUniscitiActivity.this, AggiungiGruppoActivity.class);
                }

                intent.putExtra("utenteAttivo", utenteAttivo);
                startActivity(intent);
            }
        });

        btnEntra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DettagliGruppoUniscitiActivity.this, UniscitiGruppoActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                intent.putExtra("servizio", servizio);
                intent.putExtra("activity", activity);
                startActivity(intent);
            }
        });
    }


    /* --- FUNZIONI SUPPORTO --- */

    protected void recoveryData() {
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
        servizio = (Servizio) intent.getSerializableExtra("servizio");
        activity = intent.getStringExtra("activity");
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    protected void caricaDati() {
        nomeProprietario.setText(servizio.getMembri().get(servizio.getCreatore()).getNome());
        nomeServizio.setText(servizio.getNomeServizio());
        nPosti.setText("Posti liberi: " + (servizio.getMaxPosti() - servizio.getnPosti()) + "/" + servizio.getMaxPosti());
        rinnovo.setText("Rinnovo: " + servizio.getFrequenzaRinnovo());
        costo.setText(String.format("Costo: %.2f €", servizio.getCostoSingolo()));
        imgBackground.setImageResource(servizio.getImgSource());
    }

}