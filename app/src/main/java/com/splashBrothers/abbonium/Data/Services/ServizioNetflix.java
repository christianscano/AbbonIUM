package com.splashBrothers.abbonium.Data.Services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.splashBrothers.abbonium.Data.Utente;

import java.io.Serializable;

public class ServizioNetflix extends Servizio implements Serializable {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ServizioNetflix(double costoTotale, String tipoRinnovo, String tipoRelazione,
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
