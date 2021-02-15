package com.splashBrothers.abbonium.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

public class ChatActivity extends AppCompatActivity {

    Utente utenteAttivo;
    Servizio servizio;

    ImageButton btnBack;
    TextView persona, persona2, messaggio, messaggio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btnBack = findViewById(R.id.btnBack);
        persona = findViewById(R.id.textPersona);
        persona2 = findViewById(R.id.textPersona2);
        messaggio = findViewById(R.id.textMessaggio);
        messaggio2 = findViewById(R.id.textMessaggio2);

        recoveryData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, DettagliGruppoActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                intent.putExtra("servizio", servizio);
                startActivity(intent);
            }
        });

        persona.setText(utenteAttivo.getNome());
        persona2.setText(servizio.getMembri().get(servizio.getCreatore()).getNome());
        messaggio.setText("Ciao a tutti");
        messaggio2.setText("Benvenuto " + utenteAttivo.getNome());

        showAlertDialogInfo("Questa chat è a solo scopo dimostrativo, non è possibile mandare nessun messaggio");
    }

    /* --- FUNZIONI SUPPORTO --- */

    protected void recoveryData() {
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
        servizio = (Servizio) intent.getSerializableExtra("servizio");
    }

    protected void showAlertDialogInfo(String messaggio) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ChatActivity.this);
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
}