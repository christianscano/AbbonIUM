package com.splashBrothers.abbonium.Data.Services;

import com.splashBrothers.abbonium.Data.Utente;

public class ServizioNetflix extends Servizio {

    public ServizioNetflix(double costoTotale, ServizioInfo.TipoRinnovo tipoRinnovo, ServizioInfo.TipoRelazione tipoRelazione,
                           int maxPosti, Utente proprietario) {
        super(
                ServizioInfo.InfoNetflix.nomeServizio,
                ServizioInfo.InfoNetflix.imgSource,
                costoTotale,
                tipoRinnovo,
                tipoRelazione,
                maxPosti,
                proprietario
        );
    }

}
