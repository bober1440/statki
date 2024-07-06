package com.kodilla.shipswar;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;


@SpringBootApplication
public class ShipsWarApplication {
    private Helper pomocnik = new Helper();
    private ArrayList<Ships> statki = new ArrayList<Ships>() ;
    private int liczbaRuchow = 0;

    private void przygotujGre(){
        Ships pierwszy = new Ships();
        pierwszy.setNazwa("Pierwszy");
        Ships drugi = new Ships();
        drugi.setNazwa("Drugi");
        Ships trzeci = new Ships();
        trzeci.setNazwa("Trzeci");
        statki.add(pierwszy);
        statki.add(drugi);
        statki.add(trzeci);

        System.out.println("Twoim celem jest zatopienie 3 statków.");
        System.out.println("Ich nazwy to pierwszy,drugi i trzeci");
        System.out.println("Postaraj się je zatopić wykonując jak najmniej ruchów");

        for (Ships ships : statki){
            ArrayList<String> nowePolozenie = pomocnik.rozmiescStatek(3);
            ships.setPolaPolozenia(nowePolozenie);
        }
    }

    private void rozpocznijGre(){
        while (!statki.isEmpty()){
            String ruchGracza = pomocnik.pobierzDaneWejsciowe("Podaj pole");
            sprawdzRuchGracza(ruchGracza);
        }
        zakonczGre();
    }

    private void sprawdzRuchGracza(String ruchGracza){
        liczbaRuchow++;
        String wynik ;

        for (Ships statkiDoSprawdzenia : statki){
            wynik = statkiDoSprawdzenia.sprawdz(ruchGracza);
            if (wynik.equals("trafiony")){
                break;
            }
        }
    }

    private void zakonczGre(){
        System.out.println("Wsztstkie statki zostały zatopione");
        if (liczbaRuchow <= 18) {
            System.out.println("Wykonałeś jedynie " + liczbaRuchow + " ruchów");
        }else{
            System.out.println("Wykonałeś aż " + liczbaRuchow + " ruchów");
        }
    }

    public static void main(String[] args) {
        ShipsWarApplication gra = new ShipsWarApplication();
        gra.przygotujGre();
        gra.rozpocznijGre();
    }

}
