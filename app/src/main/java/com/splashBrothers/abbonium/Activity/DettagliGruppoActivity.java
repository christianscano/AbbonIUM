package com.splashBrothers.abbonium.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

public class DettagliGruppoActivity extends AppCompatActivity {

    Utente utenteAttivo;
    Servizio servizio;

    ClipboardManager clipboardManager;

    ImageButton btnExit, btnBack, btnShare;
    TextView nomeProprietario, nomeServizio, rinnovo, nPosti, costo;
    ImageView imgBackground;
    Button btnApriChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_gruppo);

        nomeProprietario = findViewById(R.id.textNomeUtente);
        nomeServizio = findViewById(R.id.textNomeServizio);
        nPosti = findViewById(R.id.textPostiLiberi);
        rinnovo = findViewById(R.id.textRinnovo);
        costo = findViewById(R.id.textCosto);
        imgBackground = findViewById(R.id.imgBackground);
        btnApriChat = findViewById(R.id.btnApriChat);
        btnBack = findViewById(R.id.btnBack);
        btnExit = findViewById(R.id.btnExitGroup);
        btnShare = findViewById(R.id.btnShareGroup);

        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        recoveryData();

        caricaDati();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DettagliGruppoActivity.this, HomeActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                intent.putExtra("isHome", false);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(utenteAttivo.getEmail().equals(servizio.getCreatore())) {
                    if(servizio.getMembri().size() == 1)
                        showAlertDialogExit(true, "Vuoi cancellare questo gruppo?");
                    else
                        showAlertDialogInfo("Nel gruppo sono ancora presenti dei membri, prima di rimuoverlo contattali");

                } else {
                    showAlertDialogExit(false, "Sei sicuro di voler abbandonare il gruppo?");
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogLink();
            }
        });

        btnApriChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /* --- FUNZIONI SUPPORTO --- */

    protected void recoveryData() {
        Servizio tmp;
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
        tmp = (Servizio) intent.getSerializableExtra("servizio");

        for(Servizio s : LoginActivity.serviziGlobali)
            if(s.getCreatore().equals(tmp.getCreatore()) && s.getNomeServizio().equals(tmp.getNomeServizio()))
                servizio = s;;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    protected void caricaDati() {
        nomeProprietario.setText(servizio.getMembri().get(servizio.getCreatore()).getNome());
        nomeServizio.setText(servizio.getNomeServizio());
        nPosti.setText("Posti liberi: " + (servizio.getMaxPosti() - servizio.getnPosti()) + "/" + servizio.getMaxPosti());
        rinnovo.setText("Rinnovo: " + servizio.getFrequenzaRinnovo());
        costo.setText(String.format("Costo: %.2f €", servizio.getCostoSingolo()));
        imgBackground.setImageResource(servizio.getImgSource());
    }

    protected void showAlertDialogExit(boolean isProprietario, String messaggio) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DettagliGruppoActivity.this);
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
               if(!isProprietario) {
                   servizio.rimuoviMembro(utenteAttivo);
                   utenteAttivo.getMyGruppiUnito().remove(servizio.getNomeServizio());
               } else {
                    utenteAttivo.getMyGruppiCreati().remove(servizio.getNomeServizio());
                    LoginActivity.serviziGlobali.remove(servizio);
               }

                Intent intent = new Intent(DettagliGruppoActivity.this, HomeActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                intent.putExtra("isHome", false);
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

    protected void showAlertDialogInfo(String messaggio) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DettagliGruppoActivity.this);
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
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    protected void showAlertDialogLink() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DettagliGruppoActivity.this);
        View layoutView = getLayoutInflater().inflate(R.layout.alert_dialog_link, null);
        TextView testo = layoutView.findViewById(R.id.textLink);
        Button btnConferma = layoutView.findViewById(R.id.btnCopia);
        dialogBuilder.setView(layoutView);
        AlertDialog alertDialog = dialogBuilder.create();

        testo.setText(servizio.getLinkCondivisione());

        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("Link", servizio.getLinkCondivisione()));
                Toast.makeText(getApplicationContext(), "Link copiato", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

}