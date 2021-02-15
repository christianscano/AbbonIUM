package com.splashBrothers.abbonium.Data;

import com.splashBrothers.abbonium.Data.Services.Servizio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Utente implements Serializable {
    String nome;
    String cognome;
    String password;
    String email;
    String dataNascita;
    String emailPayPal;  //email pagamento

    HashMap<String, Servizio> myGruppiUnito;
    HashMap<String, Servizio> myGruppiCreati;

    public Utente(String nome, String cognome, String password, String email, String dataNascita,  String emailPaypal) {
        this.password = password;
        this.dataNascita = dataNascita;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.emailPayPal = emailPaypal;

        myGruppiCreati = new HashMap<>();
        myGruppiUnito = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getEmailPayPal() {
        return emailPayPal;
    }

    public HashMap<String, Servizio> getMyGruppiUnito() {
        return myGruppiUnito;
    }

    public HashMap<String, Servizio> getMyGruppiCreati() {
        return myGruppiCreati;
    }


    /* --- FUNZIONI --- */
    //public rimuoviGruppo(boolean isProprietario, )


}
