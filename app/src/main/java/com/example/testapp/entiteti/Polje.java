package com.example.testapp.entiteti;

import androidx.annotation.NonNull;

import java.util.List;

public class Polje extends Entitet{

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
}
