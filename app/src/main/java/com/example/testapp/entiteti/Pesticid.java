package com.example.testapp.entiteti;

import com.example.testapp.MainActivity2;

import java.io.Serializable;


public class Pesticid extends Entitet implements Serializable {


    String naziv;

    int dozaMax;

    double cijena;

    String opis;
    Long proizvodjacId;

    Boolean bio;

    int vrsta;
    String mjereSigurnosti;

    String nacinDjelovanja;

    String primjena;

    String formulacija;


    public Pesticid(Long id, String naziv, int dozaMax, double cijena, String opis, Long proizvodjacId, Boolean bio, int vrsta, String mjereSigurnosti, String nacinDjelovanja, String primjena, String formulacija) {
        super(id);
        this.naziv = naziv;
        this.dozaMax = dozaMax;
        this.cijena = cijena;
        this.opis = opis;
        this.proizvodjacId = proizvodjacId;
        this.bio = bio;
        this.vrsta = vrsta;
        this.mjereSigurnosti = mjereSigurnosti;
        this.nacinDjelovanja = nacinDjelovanja;
        this.primjena = primjena;
        this.formulacija = formulacija;
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

    public Long getProizvodjacId() {
        return proizvodjacId;
    }

    public Proizvodjac getProizvodjac(){
        for(Proizvodjac p : MainActivity2.proizvodjacList)
        {
            if(p.getId().equals(proizvodjacId))
                return p;
        }
        return null;



    }



    public Boolean getBio() {
        return bio;
    }

    public int getVrsta(){return vrsta;}

    public String getMjereSigurnosti() {
        return mjereSigurnosti;
    }

    public String getNacinDjelovanja() {
        return nacinDjelovanja;
    }

    public String getPrimjena() {
        return primjena;
    }

    public String getFormulacija() {
        return formulacija;
    }

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
            else if(proizvodjacId != ((Pesticid) o).getProizvodjacId())
                result++;
            else if(!bio.equals(((Pesticid) o).getBio()))
                result++;
            else if(vrsta != ((Pesticid) o).getVrsta())
                result++;
            else if(!formulacija.equals(((Pesticid) o).getFormulacija()))
                result++;
            else if(!mjereSigurnosti.equals(((Pesticid) o).getMjereSigurnosti()))
                result++;
            else if(!primjena.equals(((Pesticid) o).getPrimjena()))
                result++;
            else if(!nacinDjelovanja.equals(((Pesticid) o).getNacinDjelovanja()))
                result++;
            return (result == 0);
        } else return false;
    }
}






