package com.splashBrothers.abbonium.Data;

import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Services.ServizioInfo;
import com.splashBrothers.abbonium.Data.Services.ServizioMarvelUnlimited;
import com.splashBrothers.abbonium.Data.Services.ServizioNetflix;

import java.util.ArrayList;
import java.util.HashMap;

public class GenerateData {
    static public ArrayList<Servizio> generaServizi(HashMap<String, Utente> utentiGlobali) {
        ArrayList<Servizio> myServizi = new ArrayList<>();

        myServizi.add(new ServizioNetflix(
                ServizioInfo.InfoNetflix.costoTotale[0],
                ServizioInfo.InfoNetflix.tipoRinnovo[0],
                ServizioInfo.InfoNetflix.tipoRelazione[0],
                ServizioInfo.InfoNetflix.maxPosti,
                utentiGlobali.get("cri.maro99@outlook.it")
        ));

        myServizi.add(new ServizioMarvelUnlimited(
                ServizioInfo.InfoMarvelUnlimited.costoTotale[0],
                ServizioInfo.InfoMarvelUnlimited.tipoRinnovo[0],
                ServizioInfo.InfoMarvelUnlimited.tipoRelazione[0],
                ServizioInfo.InfoMarvelUnlimited.maxPosti,
                utentiGlobali.get("massi.frau15@virgilio.it")
        ));

        return myServizi;
    }

    static public HashMap<String, Utente> generaUtenti() {
        HashMap<String, Utente> myUtenti = new HashMap<>();

        myUtenti.put("luca.rio23@gmail.com", new Utente(
                "Luca",
                "Rio",
                "cornetto23",
                "luca.rio23@gmail.com",
                "31/04/1998",
                "luca.rio23@gmail.com"
        ));

        myUtenti.put("massi.frau15@virgilio.it", new Utente(
                "Massimiliano",
                "Frau",
                "pallone23",
                "massi.frau15@virgilio.it",
                "15/08/1995",
                "massi.frau15@virgilio.it"
        ));

        myUtenti.put("cri.maro99@outlook.it", new Utente(
                "Cristiano",
                "Marongiu",
                "unica2019",
                "cri.maro99@outlook.it",
                "15/12/2000",
                "cri.maro99@outlook.it"
        ));

        return myUtenti;
    }
}
