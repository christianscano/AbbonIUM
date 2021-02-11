package com.splashBrothers.abbonium.Services;

import com.splashBrothers.abbonium.R;

public class ServizioInfo {

    public enum TipoRinnovo {MENSILE, ANNUALE};

    public enum TipoRelazione {AMICI, FAMIGLIA, STESSO_NUCLEO_DOMESTICO, TEAM_DI_LAVORO};


    /*** SERVIZI DEMO ***/

    static public class InfoNetflix {
        static String nomeServizio = "NETFLIX PREMIUM";
        static TipoRinnovo tipoRinnovo = TipoRinnovo.MENSILE;
        static double costoTotale = 15.99;
        static TipoRelazione tipoRelazione = TipoRelazione.STESSO_NUCLEO_DOMESTICO;
        static int maxPosti = 3;
        static int imgSource = R.drawable.netflix_image;
    }

    static public class InfoMarvelUnlimited{
        static String nomeServizio = "MARVEL UNLIMITED";
        static TipoRinnovo tipoRinnovo = TipoRinnovo.MENSILE;
        static double costoTotale = 8.57;
        static TipoRelazione tipoRelazione = TipoRelazione.AMICI;
        static int maxPosti = 5;
        static int imgSource = R.drawable.marvel_unlimited_image;
    }
}
