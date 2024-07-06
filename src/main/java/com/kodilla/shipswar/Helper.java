package com.kodilla.shipswar;
import java.util.*;

public class Helper {
    private static final String alfabet = "ABCDEFG";
    private static final int planszaDlugosc = 7;
    private static final int planszaWielkosc = 49;
    private static final int maxProb = 200;
    static final int inkrementacjaWPoz = 1;
    static final int inkrementacjaWPion = planszaDlugosc;

    private final int[] plansza = new int[planszaWielkosc];
    private final Random liczbaLosowa = new Random();
    private int liczbaStatkow = 0;

    public String pobierzDaneWejsciowe(String komunikat) {
        System.out.println(komunikat + ": ");
        Scanner skaner = new Scanner(System.in);
        return skaner.nextLine().toLowerCase();
    }

    public ArrayList<String> rozmiescStatek(int wielkoscStatku) {
        int[] wspolrzedneStatku = new int[wielkoscStatku];
        int proby = 0;
        boolean czySieUdalo = false;

        liczbaStatkow++;
        int wartoscInkrementacji = getWartoscInkrementacji();

        while (!czySieUdalo & proby++ < maxProb) {
            int polozenie = liczbaLosowa.nextInt(planszaWielkosc);
            for (int i = 0; i < wspolrzedneStatku.length; i++) {
                wspolrzedneStatku[i] = polozenie;
                polozenie += wartoscInkrementacji;
            }

            if (czyStatekPasuje(wspolrzedneStatku, wartoscInkrementacji)) {
                czySieUdalo = czyWspolrzedneDostepne(wspolrzedneStatku);
            }
        }
        zapiszPolozenieNaPlanszy(wspolrzedneStatku);
        ArrayList<String> komorkiAlfaNum = konwertujPolozenieDoFormatuAlfaNum(wspolrzedneStatku);
        return komorkiAlfaNum;
    }

    private boolean czyStatekPasuje(int[] wspolrzedneStatku, int wartoscInkrementacji) {
        int koncowePolozenie = wspolrzedneStatku[wspolrzedneStatku.length - 1];
        if (wartoscInkrementacji == inkrementacjaWPoz) {
            return obliczWierszZIndeksu(wspolrzedneStatku[0]) == obliczWierszZIndeksu(koncowePolozenie);
        } else {
            return koncowePolozenie < planszaWielkosc;
        }
    }

    private boolean czyWspolrzedneDostepne(int[] wspolrzedneStatku) {
        for (int wspolrzedna : wspolrzedneStatku) {
            if (plansza[wspolrzedna] != 0) {
                return false;
            }
        }
        return true;
    }

    private void zapiszPolozenieNaPlanszy(int[] wspolrzedneStatku) {
        for (int indeks : wspolrzedneStatku) {
            plansza[indeks] = 1;
        }
    }

    private ArrayList<String> konwertujPolozenieDoFormatuAlfaNum(int[] wspolrzedneStatku) {
        ArrayList<String> komorkiAlfaNum = new ArrayList<>();
        for (int indeks : wspolrzedneStatku) {
            String wspolrzedneAlfaNum = pobierzWspolrzedneAlfaNumNaPdstwIndeksu(indeks);
            komorkiAlfaNum.add(wspolrzedneAlfaNum);
        }
        return komorkiAlfaNum;
    }
    private String pobierzWspolrzedneAlfaNumNaPdstwIndeksu(int indeks) {
        int wiersz = obliczWierszZIndeksu(indeks);
        int kolumna = indeks % planszaDlugosc;
        String litera = alfabet.substring(kolumna, kolumna+1);
        return litera + wiersz;
    }

    private int obliczWierszZIndeksu(int indeks) {
        return indeks / planszaDlugosc;
    }
    private int getWartoscInkrementacji() {
        if (liczbaStatkow % 2 == 0) {
            return inkrementacjaWPoz ;
        }else {
            return inkrementacjaWPion;
        }
    }
}










