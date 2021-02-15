package com.splashBrothers.abbonium.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.splashBrothers.abbonium.Data.GenerateData;
import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    /* --- DATI GLOBALI --- */
    //HashMap Email, Utente
    static HashMap<String, Utente> utentiGlobali;
    static ArrayList<Servizio> serviziGlobali;

    EditText email, password;
    Button btnAccedi;
    TextView btnRegistrati;
    Utente utenteAttivo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.textEmail);
        password = findViewById(R.id.textPassword);
        btnAccedi = findViewById(R.id.btnLogin);
        btnRegistrati = findViewById(R.id.btnRegistrati);

        generaDatiIniziali();


        btnAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLogin();
            }
        });

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrati();
            }
        });
    }


    /* --- FUNZIONI SUPPORTO --- */

    protected boolean checkInput() {
        boolean error = false;

        if(email.getText() == null || email.getText().length() == 0) {
            error = true;
            email.setError("Email mancante");
        } else
            email.setError(null);

        if(password.getText() == null || email.getText().length() == 0) {
            error = true;
            password.setError("Password mancante");
        } else
            password.setError(null);

        return error;
    }

    protected void tryLogin() {
        if (!checkInput()) {
            utenteAttivo = utentiGlobali.get(email.getText().toString());
            if (utenteAttivo != null)
                if (utenteAttivo.getPassword().equals(password.getText().toString())) {
                    email.setError(null);
                    password.setError(null);

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("utenteAttivo", utenteAttivo);
                    intent.putExtra("isFirst", true);
                    startActivity(intent);
                } else
                    password.setError("Password errata");
            else
                email.setError("Email non esistente");
        }
    }

    protected void registrati() {
        Intent intent = new Intent(LoginActivity.this, RegistrazioneActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void generaDatiIniziali() {
        if(utentiGlobali == null || serviziGlobali == null) {
            utentiGlobali = GenerateData.generaUtenti();
            serviziGlobali = GenerateData.generaServizi(utentiGlobali);
        }
    }

}