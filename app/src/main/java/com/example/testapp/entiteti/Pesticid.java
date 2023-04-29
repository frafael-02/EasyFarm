package com.example.testapp.entiteti;

import java.io.Serializable;

public class Pesticid extends Entitet implements Serializable {


    String naziv;

    int dozaMax;

    public Pesticid(Long id, String naziv, int dozaMax) {
        super(id);
        this.naziv = naziv;
        this.dozaMax = dozaMax;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getDozaMax() {
        return dozaMax;
    }

    public void setDozaMax(int dozaMax) {
        this.dozaMax = dozaMax;
    }


    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Pesticid)
        {
            int result =0;
            if(!naziv.equals(((Pesticid) o).getNaziv()))
                result++;
            else if(dozaMax != ((Pesticid) o).dozaMax)
                result++;
            return (result==0);
        }
        else return false;
    }
}
