package com.example.testapp.entiteti;

public class Biljka extends Entitet {


    String naziv;

    public Biljka(Long id, String naziv) {
        super(id);
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
