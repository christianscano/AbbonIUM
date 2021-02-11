package com.splashBrothers.abbonium.Services;

import com.splashBrothers.abbonium.Utente;
import java.util.HashMap;

abstract public class Servizio {
    String nomeServizio;
    int imgSource;
    double costoTotale;
    ServizioInfo.TipoRinnovo frequenzaRinnovo;
    int nPosti;
    int maxPosti;
    ServizioInfo.TipoRelazione tipoRelazione;

    String creatore;
    HashMap<String, Utente> membri;

    public Servizio(String nomeServizio, int imgSource, double costoTotale, ServizioInfo.TipoRinnovo frequenzaRinnovo,
                    ServizioInfo.TipoRelazione tipoRelazione, int maxPosti, Utente proprietario) {
        this.nomeServizio = nomeServizio;
        this.imgSource = imgSource;
        this.costoTotale = costoTotale;
        this.frequenzaRinnovo = frequenzaRinnovo;
        this.nPosti = 0;
        this.maxPosti = maxPosti;
        this.tipoRelazione = tipoRelazione;

        creatore = proprietario.getUsername();
        membri = new HashMap<>();
        membri.put(creatore, proprietario);
    }



    public String getNomeServizio() {
        return nomeServizio;
    }

    public void setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
    }

    public int getImgSource() {
        return imgSource;
    }

    public void setImgSource(int imgSource) {
        this.imgSource = imgSource;
    }

    public double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(double costoTotale) {
        this.costoTotale = costoTotale;
    }

    public ServizioInfo.TipoRinnovo getFrequenzaRinnovo() {
        return frequenzaRinnovo;
    }

    public void setFrequenzaRinnovo(ServizioInfo.TipoRinnovo frequenzaRinnovo) {
        this.frequenzaRinnovo = frequenzaRinnovo;
    }

    public int getnPosti() {
        return nPosti;
    }

    public void setnPosti(int nPosti) {
        this.nPosti = nPosti;
    }

    public ServizioInfo.TipoRelazione getTipoRelazione() {
        return tipoRelazione;
    }

    public void setTipoRelazione(ServizioInfo.TipoRelazione tipoRelazione) {
        this.tipoRelazione = tipoRelazione;
    }

    public String getCreatore() {
        return creatore;
    }

    public void setCreatore(String creatore) {
        this.creatore = creatore;
    }

    public HashMap<String, Utente> getMembri() {
        return membri;
    }

    public void setMembri(HashMap<String, Utente> membri) {
        this.membri = membri;
    }

    public int getMaxPosti() {
        return maxPosti;
    }

    public void setMaxPosti(int maxPosti) {
        this.maxPosti = maxPosti;
    }
}
