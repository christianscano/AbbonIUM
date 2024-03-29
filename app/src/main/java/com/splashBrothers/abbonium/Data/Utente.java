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

    public void setMyGruppiUnito(HashMap<String, Servizio> myGruppiUnito) {
        this.myGruppiUnito = myGruppiUnito;
    }

    public void setMyGruppiCreati(HashMap<String, Servizio> myGruppiCreati) {
        this.myGruppiCreati = myGruppiCreati;
    }

    /* --- FUNZIONI --- */
    public boolean esisteServizio(String nomeServizio) {
        if(myGruppiUnito.get(nomeServizio) == null && myGruppiCreati.get(nomeServizio) == null)
            return false;
        return true;
    }




}
