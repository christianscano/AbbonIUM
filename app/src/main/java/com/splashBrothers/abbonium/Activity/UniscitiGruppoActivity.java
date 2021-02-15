package com.splashBrothers.abbonium.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Services.ServizioInfo;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

import java.time.temporal.UnsupportedTemporalTypeException;

public class UniscitiGruppoActivity extends AppCompatActivity {

    Utente utenteAttivo;
    Servizio servizio; //Posizione nell'arrayList del gruppo a cui mi voglio unire
    String activity;

    RadioGroup radioGroupRelazione;
    RadioButton rBtnAmici, rBtFamiglia, rBtLavoro, rBtNucleo;
    CheckBox checkBoxContratto;
    Button btnEntra;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unisciti_gruppo);

        radioGroupRelazione = findViewById(R.id.radioGroupRelazione);
        checkBoxContratto = findViewById(R.id.checkboxConfermaContratto);
        rBtFamiglia = findViewById(R.id.radioBtnFamiglia);
        rBtLavoro = findViewById(R.id.radioBtnTeam);
        rBtnAmici = findViewById(R.id.radioBtnAmici);
        rBtNucleo = findViewById(R.id.radioBtnStessoNucleo);
        btnEntra = findViewById(R.id.btnEntra);
        btnBack = findViewById(R.id.btnBack);

        recoveryData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UniscitiGruppoActivity.this, DettagliGruppoUniscitiActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                intent.putExtra("activity", activity);
                intent.putExtra("servizio", servizio);
                startActivity(intent);
            }
        });

        btnEntra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entraGruppo();
            }
        });

        radioGroupRelazione.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rBtLavoro.setError(null);
                rBtnAmici.setError(null);
                rBtNucleo.setError(null);
                rBtFamiglia.setError(null);
                rBtLavoro.setFocusableInTouchMode(false);
                rBtnAmici.setFocusableInTouchMode(false);
                rBtNucleo.setFocusableInTouchMode(false);
                rBtFamiglia.setFocusableInTouchMode(false);
            }
        });

        checkBoxContratto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxContratto.setFocusableInTouchMode(false);
                checkBoxContratto.setError(null);
            }
        });
    }


    /* --- FUNZIONI SUPPORTO --- */

    protected void recoveryData() {
        Intent intent = getIntent();
        Servizio tmp;
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
        tmp = (Servizio) intent.getSerializableExtra("servizio");
        activity = intent.getStringExtra("activity");

        for(Servizio s : LoginActivity.serviziGlobali)
            if(s.getCreatore().equals(tmp.getCreatore()) && s.getNomeServizio().equals(tmp.getNomeServizio()))
                servizio = s;;

        checkBoxContratto.setText("Confermo di aver letto e rispettare le regole di " + servizio.getNomeServizio());

        caricaCheckBox();
    }

    protected void caricaCheckBox() {
        switch (servizio.getTipoRelazione()) {
            case "Amici":
                rBtnAmici.setEnabled(true);
                break;
            case "Famiglia":
                rBtFamiglia.setEnabled(true);
                break;
            case "Stesso nucleo domestico":
                rBtNucleo.setEnabled(true);
                break;
            case "Team di lavoro":
                rBtLavoro.setEnabled(true);
                break;
        }
    }

    protected boolean checkInput() {
        boolean error = false;

        if(radioGroupRelazione.getCheckedRadioButtonId() == -1) {
            error = true;

            if(rBtFamiglia.isEnabled()) {
                rBtFamiglia.setError("Nessuna relazione selezionata");
                rBtFamiglia.setFocusableInTouchMode(true);
            }

            if(rBtNucleo.isEnabled()) {
                rBtNucleo.setError("Nessuna relazione selezionata");
                rBtNucleo.setFocusableInTouchMode(true);
            }

            if(rBtnAmici.isEnabled()) {
                rBtnAmici.setError("Nessuna relazione selezionata");
                rBtnAmici.setFocusableInTouchMode(true);
            }

            if(rBtLavoro.isEnabled()) {
                rBtLavoro.setError("Nessuna relazione selezionata");
                rBtLavoro.setFocusableInTouchMode(true);
            }
        } else {
            rBtLavoro.setError(null);
            rBtnAmici.setError(null);
            rBtNucleo.setError(null);
            rBtFamiglia.setError(null);
            rBtLavoro.setFocusableInTouchMode(false);
            rBtnAmici.setFocusableInTouchMode(false);
            rBtNucleo.setFocusableInTouchMode(false);
            rBtFamiglia.setFocusableInTouchMode(false);
        }

        if (!checkBoxContratto.isChecked()) {
            error = true;
            checkBoxContratto.setError("Devi accettare le condizioni");
            checkBoxContratto.setFocusableInTouchMode(true);
        } else {
            checkBoxContratto.setFocusableInTouchMode(false);
            checkBoxContratto.setError(null);
        }

        return error;
    }

    protected void entraGruppo() {
        if(!checkInput()) {
            servizio.aggiungiMembro(utenteAttivo);
            utenteAttivo.getMyGruppiUnito().put(servizio.getNomeServizio(), servizio);

            Intent intent = new Intent(UniscitiGruppoActivity.this, HomeActivity.class);
            intent.putExtra("utenteAttivo", utenteAttivo);
            startActivity(intent);
        }
    }

}