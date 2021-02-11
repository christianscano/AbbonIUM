package com.splashBrothers.abbonium.Services;

import com.splashBrothers.abbonium.R;
import com.splashBrothers.abbonium.Utente;

public class ServizioMarvelUnlimited extends Servizio {

    public ServizioMarvelUnlimited(double costoTotale, ServizioInfo.TipoRinnovo tipoRinnovo, ServizioInfo.TipoRelazione tipoRelazione,
                                    int maxPosti, Utente proprietario) {
        super(
                ServizioInfo.InfoMarvelUnlimited.nomeServizio,
                ServizioInfo.InfoMarvelUnlimited.imgSource,
                costoTotale,
                tipoRinnovo,
                tipoRelazione,
                maxPosti,
                proprietario
        );
    }
}
