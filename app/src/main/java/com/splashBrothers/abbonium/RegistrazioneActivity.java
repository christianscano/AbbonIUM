package com.splashBrothers.abbonium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.splashBrothers.abbonium.Data.Utente;

import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrazioneActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText nome, cognome, email, password, cPassword, data, paypal;
    Button btnRegistrati;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        nome = findViewById(R.id.textNome);
        cognome = findViewById(R.id.textCognome);
        email = findViewById(R.id.textEmail);
        password = findViewById(R.id.textPassword);
        cPassword = findViewById(R.id.textConfPassword);
        data = findViewById(R.id.textData);
        paypal = findViewById(R.id.textEmailPagamento);
        btnRegistrati = findViewById(R.id.btnRegistrati);
        btnBack = findViewById(R.id.btnBack);

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrati();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    /* --- FUNZIONI SUPPORTO --- */

    //Controlla input dei campi form
    protected boolean checkInput() {
        boolean error = false;

        if(nome.getText() == null || nome.getText().length() == 0) {
            error = true;
            nome.setError("Nome mancante");
        } else
            nome.setError(null);

        if(cognome.getText() == null || cognome.getText().length() == 0) {
            error = true;
            cognome.setError("Cognome mancante");
        } else
            cognome.setError(null);

        if(email.getText() != null && email.getText().length() != 0) {
            if(isEmail(email.getText().toString()))
                if(isNewEmail(email.getText().toString()))
                    email.setError(null);
                else {
                    error = true;
                    email.setError("Email già esistente");
                }
            else {
                error = true;
                email.setError("Email non valida");
            }
        } else {
            error = true;
            email.setError("Email mancante");
        }

        if(paypal.getText() != null && paypal.getText().length() != 0) {
            if(isEmail(paypal.getText().toString()))
                    paypal.setError(null);
            else {
                error = true;
                paypal.setError("Email non valida");
            }
        } else {
            error = true;
            paypal.setError("Email mancante");
        }

        if(password.getText() == null || password.getText().length() == 0) {
            error = true;
            password.setError("Password mancante");
        } else
            password.setError(null);

        if(cPassword.getText() != null && cPassword.getText().length() != 0) {
            if(!password.getText().toString().equals(cPassword.getText().toString())) {
                error = true;
                cPassword.setError("Password non coincidono");
            } else
                cPassword.setError(null);
        } else {
            cPassword.setError("Password mancante");
            error = true;
        }

        if(data.getText() == null || data.getText().length() == 0) {
            error = true;
            data.setError("Data mancante");
        } else
            data.setError(null);

        return error;
    }

    //Controlla tramitte regex se il formato della mail è corretto
    protected boolean isEmail(String email) {
        return email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    //Controlla se l'email è già stata utilizzata
    protected boolean isNewEmail(String email) {
        return LoginActivity.utentiGlobali.get(email) == null;
    }

    //Effettua un tenativo di registrazione, in caso positivo inserisce il nuovo utente nel HashMap
    protected void registrati() {
        if(!checkInput()) {
            LoginActivity.utentiGlobali.put(email.getText().toString(), new Utente(
                    nome.getText().toString(),
                    cognome.getText().toString(),
                    cPassword.getText().toString(),
                    email.getText().toString(),
                    data.getText().toString(),
                    email.getText().toString()
            ));

            Intent intent = new Intent(RegistrazioneActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                R.style.DatePicker,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String tmp;

        if(dayOfMonth < 10)
            tmp = "0" + dayOfMonth;
        else
            tmp = "" + dayOfMonth;

        if(month < 10)
            tmp += "/0" + month + "/" + year;
        else
            tmp += "/" + month + "/" + year;

        data.setText(tmp);
    }
}