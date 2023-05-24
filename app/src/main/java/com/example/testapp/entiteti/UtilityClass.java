package com.example.testapp.entiteti;


import com.example.testapp.R;
import com.example.testapp.shop.ShopChildModelClass;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public static List<ShopChildModelClass> pesticidToShopList(List<Pesticid> pesticidList)
    {
        List<ShopChildModelClass> list = new ArrayList<>();
        for(Pesticid p : pesticidList)
            list.add(new ShopChildModelClass(R.drawable.pesticid,p));

        return list;

    }

    public static List<ShopChildModelClass> pesticidToShopBioList(List<Pesticid> pesticidList)
    {
        List<ShopChildModelClass> list = new ArrayList<>();
        for(Pesticid p : pesticidList) {
            if(p.getBio())
                list.add(new ShopChildModelClass(R.drawable.pesticid, p));
        }
        return list;

    }



}

