package com.example.testapp.entiteti;

import java.io.Serializable;

public class Biljka extends Entitet implements Serializable {


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

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Biljka)
        {
            int result =0;
            if(naziv.equals(((Biljka) o).getNaziv()))
                return true;
            else
                return false;
        }
        else return false;
    }
}
