package com.example.testapp.entiteti;

public class Proizvodjac extends Entitet {
    String naziv;
    String email;
    String telefon;

    public Proizvodjac(Long id, String naziv, String email, String telefon) {
        super(id);
        this.naziv = naziv;
        this.email = email;
        this.telefon = telefon;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
