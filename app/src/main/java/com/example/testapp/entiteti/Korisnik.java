package com.example.testapp.entiteti;

public class Korisnik {
    String email;
    int mibpg;
    String punoIme;

    public Korisnik(String email, int mibpg, String punoIme) {
        this.email = email;
        this.mibpg = mibpg;
        this.punoIme = punoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMibpg() {
        return mibpg;
    }

    public void setMibpg(int mibpg) {
        this.mibpg = mibpg;
    }

    public String getPunoIme() {
        return punoIme;
    }

    public void setPunoIme(String punoIme) {
        this.punoIme = punoIme;
    }
}
