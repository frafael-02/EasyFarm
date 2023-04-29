package com.example.testapp.entiteti;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Polje extends Entitet implements Serializable {

    String arkodId;
    String naziv;
    List<Biljka> listaKultura;

    String korisnikEmail;

    public Polje(Long id, String arkodId, String naziv, List<Biljka> listaKultura, String korisnikEmail) {
        super(id);
        this.arkodId = arkodId;
        this.naziv = naziv;
        this.listaKultura = listaKultura;
        this.korisnikEmail = korisnikEmail;
    }

    public String getArkodId() {
        return arkodId;
    }

    public void setArkodId(String arkodId) {
        this.arkodId = arkodId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Biljka> getListaKultura() {
        return listaKultura;
    }

    public void setListaKultura(List<Biljka> listaKultura) {
        this.listaKultura = listaKultura;
    }

    public String getKorisnikEmail() {
        return korisnikEmail;
    }

    @NonNull
    @Override
    public String toString(){return naziv;}

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Polje)
        {
            int result=0;
            if(!arkodId.equals(((Polje) o).getArkodId()))
                result++;
            else if(!naziv.equals(((Polje) o).getNaziv()))
                result++;
            else if(!listaKultura.equals(((Polje) o).getListaKultura()))
                result++;
            else if(!korisnikEmail.equals(((Polje) o).getKorisnikEmail()))
                result++;

            return (result == 0);
        }
        else return false;
    }
}
