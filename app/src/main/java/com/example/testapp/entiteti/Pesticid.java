package com.example.testapp.entiteti;

public class Pesticid extends Entitet {


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
}
