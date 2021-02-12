package com.splashBrothers.abbonium.Data.Services;

import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.LoginActivity;
import com.splashBrothers.abbonium.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ServizioInfo {

    public enum TipoRinnovo {MENSILE, ANNUALE};

    public enum TipoRelazione {AMICI, FAMIGLIA, STESSO_NUCLEO_DOMESTICO, TEAM_DI_LAVORO};


    /*** SERVIZI DEMO ***/

    static public class InfoNetflix {
        static public final String nomeServizio = "NETFLIX PREMIUM";
        static public final TipoRinnovo[] tipoRinnovo = {TipoRinnovo.MENSILE};
        static public final double[] costoTotale = {15.99};
        static public final TipoRelazione[] tipoRelazione = {TipoRelazione.STESSO_NUCLEO_DOMESTICO};
        static public final int maxPosti = 3;
        static public final int imgSource = R.drawable.netflix_image;
    }

    static public class InfoMarvelUnlimited{
        static public final String nomeServizio = "MARVEL UNLIMITED";
        static public final TipoRinnovo[] tipoRinnovo = {TipoRinnovo.MENSILE};
        static public final double[] costoTotale = {8.57};
        static public final TipoRelazione[] tipoRelazione = {TipoRelazione.AMICI};
        static public final int maxPosti = 5;
        static public final int imgSource = R.drawable.marvel_unlimited_image;
    }
}
