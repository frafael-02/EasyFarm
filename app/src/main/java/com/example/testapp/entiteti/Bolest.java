package com.example.testapp.entiteti;

public class Bolest extends Entitet {
    String naziv;
    String code;

    Long pesticidId;

    public Bolest(Long id, String naziv, String code, Long pesticidId) {
        super(id);
        this.naziv = naziv;
        this.code = code;
        this.pesticidId = pesticidId;
    }



    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPesticidId() {
        return pesticidId;
    }

    public void setPesticidId(Long pesticidId) {
        this.pesticidId = pesticidId;
    }
}
