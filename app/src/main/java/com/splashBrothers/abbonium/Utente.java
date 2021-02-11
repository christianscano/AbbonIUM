package com.splashBrothers.abbonium;

import com.splashBrothers.abbonium.Services.Servizio;

import java.util.ArrayList;

public class Utente {
    String username;
    String nome;
    String cognome;
    String password;
    String email;
    String dataNascita;

    ArrayList<Servizio> myGruppiUnito;
    ArrayList<Servizio> myGruppiCreati;

    public Utente(String username, String nome, String cognome, String password, String email, String dataNascita) {
        this.username = username;
        this.password = password;
        this.dataNascita = dataNascita;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public String getUsername() {
        return username;
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
}
