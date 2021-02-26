package com.splashBrothers.abbonium.Data.Services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.splashBrothers.abbonium.Data.Utente;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;


abstract public class Servizio implements Serializable {
    String nomeServizio;
    int imgSource;
    double costoTotale;
    String frequenzaRinnovo;
    int nPosti; //posti disponibili
    int maxPosti;
    String tipoRelazione;
    String linkCondivisione;

    String creatore; //email del creatore
    HashMap<String, Utente> membri;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Servizio(String nomeServizio, int imgSource, double costoTotale, String frequenzaRinnovo,
                    String tipoRelazione, int maxPosti, Utente proprietario) {
        this.nomeServizio = nomeServizio;
        this.imgSource = imgSource;
        this.costoTotale = costoTotale;
        this.frequenzaRinnovo = frequenzaRinnovo;
        this.nPosti = 0;
        this.maxPosti = maxPosti;
        this.tipoRelazione = tipoRelazione;

        linkCondivisione = "abbonium/" + generaLinkCondivisione();

        creatore = proprietario.getEmail();
        membri = new HashMap<>();
        membri.put(creatore, proprietario);
    }

    public String getNomeServizio() {
        return nomeServizio;
    }

    public int getImgSource() {
        return imgSource;
    }

    public double getCostoTotale() {
        return costoTotale;
    }

    public String getFrequenzaRinnovo() {
        return frequenzaRinnovo;
    }

    public int getnPosti() {
        return nPosti;
    }

    public String getTipoRelazione() {
        return tipoRelazione;
    }

    public String getCreatore() {
        return creatore;
    }

    public HashMap<String, Utente> getMembri() {
        return membri;
    }

    public int getMaxPosti() {
        return maxPosti;
    }

    public double getCostoSingolo() {
        switch(nomeServizio) {
            case "NETFLIX PREMIUM":
                return Math.round((costoTotale / (ServizioInfo.InfoNetflix.maxPosti + 1)) * 100.0) / 100.0;
            case "DISNEY PLUS":
                return Math.round((costoTotale / (ServizioInfo.InfoDisneyPlus.maxPosti + 1)) * 100.0) / 100.0;
        }

        return 0.0;
    }

    public String getLinkCondivisione() {
        return linkCondivisione;
    }

    public void setLinkCondivisione(String link) {
        linkCondivisione = link;
    }


    /* --- FUNZIONI --- */

    public void aggiungiMembro(Utente newMembro) {
        membri.put(newMembro.getEmail(), newMembro);
        nPosti++;
    }

    public boolean rimuoviMembro(Utente membro) {
        if(membri.remove(membro.getEmail()) != null) {
            nPosti--;
            return true;
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String generaLinkCondivisione() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
