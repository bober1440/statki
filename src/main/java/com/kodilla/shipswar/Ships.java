package com.kodilla.shipswar;
import java.util.*;

public class Ships {
    private ArrayList<String> polaPolozenia;
    private String nazwa;

    public void setPolaPolozenia(ArrayList<String> ppol) {
        polaPolozenia = ppol;
    }

    public void setNazwa(String n) {
        nazwa = n;
    }

    public String sprawdz (String wskazanePole){
        String wynik = "pudlo";
        int indeks = polaPolozenia.indexOf(wskazanePole);
        if (indeks>=0) {
            polaPolozenia.remove(indeks);
            if (polaPolozenia.isEmpty()) {
                wynik = "zatopiony";
                System.out.println("Zatopiłeś statek o nazwie " + nazwa);
            } else {
                wynik = "trafiony";
            }
        }
        return wynik;
    }
}
