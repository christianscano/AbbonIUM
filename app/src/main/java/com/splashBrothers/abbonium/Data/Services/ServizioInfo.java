package com.splashBrothers.abbonium.Data.Services;

import com.splashBrothers.abbonium.R;

public class ServizioInfo {

    public final static String[] tipoRinnovo  = {"Mensile", "Annuale"};

    public final static String[] tipoRelazione = {"Amici", "Famiglia", "Stesso nucleo domestico", "Team di lavoro"};

    public final static String[] servizi = {"NETFLIX PREMIUM", "MARVEL UNLIMITED"};


    /*** SERVIZI DEMO ***/

    static public class InfoNetflix {
        static public final String nomeServizio = servizi[0];
        static public final String[] tipoRinnovo = {ServizioInfo.tipoRinnovo[0]};
        static public final String[] costoTotale = {"15.99"};
        static public final String[] tipoRelazione = {ServizioInfo.tipoRelazione[2]};
        static public final int maxPosti = 3;
        static public final int imgSource = R.drawable.netflix_image;
    }

    static public class InfoMarvelUnlimited{
        static public final String nomeServizio = servizi[1];
        static public final String[] tipoRinnovo = {ServizioInfo.tipoRinnovo[0]};
        static public final String[] costoTotale = {"8.57"};
        static public final String[] tipoRelazione = {ServizioInfo.tipoRelazione[0]};
        static public final int maxPosti = 5;
        static public final int imgSource = R.drawable.marvel_unlimited_image;
    }
}
