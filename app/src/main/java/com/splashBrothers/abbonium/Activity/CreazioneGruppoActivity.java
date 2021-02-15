package com.splashBrothers.abbonium.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Services.ServizioInfo;
import com.splashBrothers.abbonium.Data.Services.ServizioMarvelUnlimited;
import com.splashBrothers.abbonium.Data.Services.ServizioNetflix;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

public class CreazioneGruppoActivity extends AppCompatActivity {

    AutoCompleteTextView nomeServizio, frequenza, tipoRelazione, numeroPosti, costo;
    Button btnCreaGruppo;
    ImageButton btnBack;

    Utente utenteAttivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creazione_gruppo);

        nomeServizio = findViewById(R.id.textNomeServizio);
        frequenza = findViewById(R.id.textFrequenza);
        tipoRelazione = findViewById(R.id.textRelazione);
        numeroPosti = findViewById(R.id.textNumeroPosti);
        costo = findViewById(R.id.textCosto);
        btnCreaGruppo = findViewById(R.id.btnCreaGruppo);
        btnBack = findViewById(R.id.btnBack);

        recoveryData();

        caricaDroplist(true, -1);

        nomeServizio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                caricaDroplist(false, position);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreazioneGruppoActivity.this, AggiungiGruppoActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                startActivity(intent);
            }
        });

        btnCreaGruppo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                creaGruppo();
            }
        });
    }

    /* --- FUNZIONI SUPPORTO --- */

    protected void recoveryData() {
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
    }

    /**
     * Si occupa di caricare i valori nelle droplist dopo che è stato scelto un servizio
     * @param isLoad Se true indica che deve caricare solo la droplist dei servizi
     * @param id Rappresenta l'id del servizio scelto, in caricamento si passa -1
     */
    protected void caricaDroplist(boolean isLoad, int id) {
        if(isLoad) {
            ArrayAdapter<String> adapterServizio = new ArrayAdapter<>(this, R.layout.dropdown_item, ServizioInfo.servizi);
            nomeServizio.setAdapter(adapterServizio);
        } else {
            ArrayAdapter<String> adapterFrequenza, adapterRelazione, adapterCosto, adapterPosti;

            switch(id) {
                case 0: //NETFLIX
                    adapterFrequenza = new ArrayAdapter<String>(this, R.layout.dropdown_item, ServizioInfo.InfoNetflix.tipoRinnovo);
                    frequenza.setAdapter(adapterFrequenza);
                    adapterRelazione = new ArrayAdapter<String>(this, R.layout.dropdown_item, ServizioInfo.InfoNetflix.tipoRelazione);
                    tipoRelazione.setAdapter(adapterRelazione);
                    adapterCosto = new ArrayAdapter<>(this, R.layout.dropdown_item, ServizioInfo.InfoNetflix.costoTotale);
                    costo.setAdapter(adapterCosto);
                    adapterPosti = new ArrayAdapter<>(this, R.layout.dropdown_item, generaArrayPosti(ServizioInfo.InfoNetflix.maxPosti));
                    numeroPosti.setAdapter(adapterPosti);
                    break;
                case 1: //MARVEL
                    adapterFrequenza = new ArrayAdapter<String>(this, R.layout.dropdown_item, ServizioInfo.InfoMarvelUnlimited.tipoRinnovo);
                    frequenza.setAdapter(adapterFrequenza);
                    adapterRelazione = new ArrayAdapter<String>(this, R.layout.dropdown_item, ServizioInfo.InfoMarvelUnlimited.tipoRelazione);
                    tipoRelazione.setAdapter(adapterRelazione);
                    adapterCosto = new ArrayAdapter<>(this, R.layout.dropdown_item, ServizioInfo.InfoMarvelUnlimited.costoTotale);
                    costo.setAdapter(adapterCosto);
                    adapterPosti = new ArrayAdapter<>(this, R.layout.dropdown_item, generaArrayPosti(ServizioInfo.InfoMarvelUnlimited.maxPosti));
                    numeroPosti.setAdapter(adapterPosti);
                    break;
            }
        }
    }

    protected String[] generaArrayPosti(int maxPosti) {
        String[] nPosti = new String[maxPosti];

        for(int i = 0; i < maxPosti; i++)
            nPosti[i] = String.valueOf(i+1);

        return nPosti;
    }

    protected boolean checkInput() {
        boolean error = false;

        if(nomeServizio.getText() == null || nomeServizio.getText().length() == 0) {
            error = true;
            nomeServizio.setError("Nessun servizio selezionato");
        } else
            nomeServizio.setError(null);

        if(frequenza.getText() == null || frequenza.getText().length() == 0) {
            error = true;
            frequenza.setError("Nessuna frequenza selezionata");
        } else
            frequenza.setError(null);

        if(tipoRelazione.getText() == null || tipoRelazione.getText().length() == 0) {
            error = true;
            tipoRelazione.setError("Nessuna relazione selezionata");
        } else
            tipoRelazione.setError(null);

        if(costo.getText() == null || costo.getText().length() == 0) {
            error = true;
            frequenza.setError("Nessun costo selezionato");
        } else
            frequenza.setError(null);

        if(numeroPosti.getText() == null || numeroPosti.getText().length() == 0) {
            error = true;
            numeroPosti.setError("Nesssun posto selezionato");
        } else
            numeroPosti.setError(null);

        return error;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void creaGruppo() {
        if(!checkInput()) {
            Servizio newServizio = null;

            //Creazione nuovo gruppo
            if (nomeServizio.getText().toString().equals(ServizioInfo.servizi[0])) { //NETFLIX
                if(utenteAttivo.getMyGruppiCreati().get(ServizioInfo.servizi[0]) == null && utenteAttivo.getMyGruppiUnito().get(ServizioInfo.servizi[0]) == null) {
                    newServizio = new ServizioNetflix(
                            Double.parseDouble(costo.getText().toString()),
                            frequenza.getText().toString(),
                            tipoRelazione.getText().toString(),
                            Integer.parseInt(numeroPosti.getText().toString()),
                            utenteAttivo
                    );
                    utenteAttivo.getMyGruppiCreati().put(ServizioInfo.servizi[0], newServizio);
                } else
                    showAlertDialogInfo("Hai già creato o partecipi ad un gruppo per questo servizio", false);
            } else if(nomeServizio.getText().toString().equals(ServizioInfo.servizi[1]) && utenteAttivo.getMyGruppiUnito().get(ServizioInfo.servizi[1]) == null) { //MARVEL
                if(utenteAttivo.getMyGruppiCreati().get(ServizioInfo.servizi[1]) == null) {
                    newServizio = new ServizioMarvelUnlimited(
                            Double.parseDouble(costo.getText().toString()),
                            frequenza.getText().toString(),
                            tipoRelazione.getText().toString(),
                            Integer.parseInt(numeroPosti.getText().toString()),
                            utenteAttivo
                    );
                    utenteAttivo.getMyGruppiCreati().put(ServizioInfo.servizi[1], newServizio);
                } else
                    showAlertDialogInfo("Hai già creato o partecipi ad un gruppo per questo servizio", false);
            }

            //Se la creazione è andata a buon fine
            if(newServizio != null) {
                LoginActivity.serviziGlobali.add(newServizio);
                showAlertDialogInfo("Il gruppo è stato creato correttamente, da adesso potrai troavarlo nella sezione Gruppi", true);
            }
        }
    }

    protected void showAlertDialogInfo(String messaggio, boolean wait) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CreazioneGruppoActivity.this);
        View layoutView = getLayoutInflater().inflate(R.layout.alert_dialog_message, null);
        TextView testo = layoutView.findViewById(R.id.testo);
        Button btnConferma = layoutView.findViewById(R.id.btnConferma);
        dialogBuilder.setView(layoutView);
        AlertDialog alertDialog = dialogBuilder.create();

        testo.setText(messaggio);

        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                if(wait) {
                    Intent intent = new Intent(CreazioneGruppoActivity.this, HomeActivity.class);
                    intent.putExtra("utenteAttivo", utenteAttivo);
                    startActivity(intent);
                }
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}