package com.example.testapp.entiteti;

import java.io.Serializable;


public class Pesticid extends Entitet implements Serializable {


    String naziv;

    int dozaMax;

    double cijena;

    String opis;

    String proizvodjac;

    Boolean bio;

    int vrsta;

    public Pesticid(Long id, String naziv, int dozaMax, double cijena, String opis, String proizvodjac, Boolean bio, int vrsta) {
        super(id);
        this.naziv = naziv;
        this.dozaMax = dozaMax;
        this.opis=opis;
        this.proizvodjac=proizvodjac;
        this.cijena = cijena;
        this.bio=bio;
        this.vrsta = vrsta;
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

    public double getCijena() {
        return cijena;
    }

    public String getOpis() {
        return opis;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public Boolean getBio() {
        return bio;
    }

    public int getVrsta(){return vrsta;}

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pesticid) {
            int result = 0;
            if (!naziv.equals(((Pesticid) o).getNaziv()))
                result++;
            else if (dozaMax != ((Pesticid) o).dozaMax)
                result++;
            else if(cijena != ((Pesticid) o).cijena)
                result++;
            else if(!opis.equals(((Pesticid) o).getOpis()))
                result++;
            else if(!proizvodjac.equals(((Pesticid) o).getProizvodjac()))
                result++;
            else if(!bio.equals(((Pesticid) o).getBio()))
                result++;
            else if(vrsta != ((Pesticid) o).getVrsta())
                result++;
            return (result == 0);
        } else return false;
    }
}






