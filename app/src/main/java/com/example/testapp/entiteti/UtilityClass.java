package com.example.testapp.entiteti;


import java.time.LocalDate;
import java.util.List;

public class UtilityClass {

    public static int getPrskanjeDanas(List<Evidencija> e)
    {
        int result=0;
        for(Evidencija evidencija : e)
        {
            if(evidencija.getVrijemeStart().toLocalDate().equals(LocalDate.now()))
                result+=evidencija.koristenaDoza;
        }
        return result;
    }

    public static int getPrskanjeMjesec(List<Evidencija> e)
    {
        int result=0;
        for(Evidencija evidencija : e)
        {
            if(evidencija.getVrijemeStart().toLocalDate().getMonth().equals(LocalDate.now().getMonth()))
                result+=evidencija.koristenaDoza;
        }
        return result;
    }

    public static int getPrskanjeUkupno(List<Evidencija> e)
    {
        int result=0;
        for(Evidencija evidencija : e)
        {

            result+=evidencija.koristenaDoza;
        }
        return result;
    }



}

