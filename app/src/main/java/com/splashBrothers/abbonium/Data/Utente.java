package com.splashBrothers.abbonium.Data;

import com.splashBrothers.abbonium.Data.Services.Servizio;

import java.io.Serializable;
import java.util.ArrayList;

public class Utente implements Serializable {
    String nome;
    String cognome;
    String password;
    String email;
    String dataNascita;
    String emailPayPal;  //email pagamento

    ArrayList<Servizio> myGruppiUnito;
    ArrayList<Servizio> myGruppiCreati;

    public Utente(String nome, String cognome, String password, String email, String dataNascita,  String emailPaypal) {
        this.password = password;
        this.dataNascita = dataNascita;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.emailPayPal = emailPaypal;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getEmailPayPal() {
        return emailPayPal;
    }

}
