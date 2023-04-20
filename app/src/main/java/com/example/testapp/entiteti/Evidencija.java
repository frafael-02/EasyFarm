package com.example.testapp.entiteti;

import com.example.testapp.MainActivity2;

import java.time.LocalDateTime;

public class Evidencija extends Entitet {


    long pesticidId;
    int koristenaDoza;
    long poljeId;
    LocalDateTime vrijemeStart;
    LocalDateTime vrijemeKraj;
    int tretiranaPovrsina;
    long biljkaId;

    String korisnikEmail;

    public Evidencija(Long id, long pesticidId, int koristenaDoza, long poljeId, LocalDateTime vrijemeStart, LocalDateTime vrijemeKraj, int tretiranaPovrsina, long biljkaId, String korisnikEmail) {
        super(id);
        this.pesticidId = pesticidId;
        this.koristenaDoza = koristenaDoza;
        this.poljeId = poljeId;
        this.vrijemeStart = vrijemeStart;
        this.vrijemeKraj = vrijemeKraj;
        this.tretiranaPovrsina = tretiranaPovrsina;
        this.biljkaId = biljkaId;
        this.korisnikEmail = korisnikEmail;
    }



    public long getPesticidId() {
        return pesticidId;
    }

    public void setPesticidId(long pesticidId) {
        this.pesticidId = pesticidId;
    }

    public int getKoristenaDoza() {
        return koristenaDoza;
    }

    public void setKoristenaDoza(int koristenaDoza) {
        this.koristenaDoza = koristenaDoza;
    }

    public long getPoljeId() {
        return poljeId;
    }

    public void setPoljeId(long poljeId) {
        this.poljeId = poljeId;
    }

    public LocalDateTime getVrijemeStart() {
        return vrijemeStart;
    }

    public void setVrijemeStart(LocalDateTime vrijemeStart) {
        this.vrijemeStart = vrijemeStart;
    }

    public LocalDateTime getVrijemeKraj() {
        return vrijemeKraj;
    }

    public void setVrijemeKraj(LocalDateTime vrijemeKraj) {
        this.vrijemeKraj = vrijemeKraj;
    }

    public int getTretiranaPovrsina() {
        return tretiranaPovrsina;
    }

    public void setTretiranaPovrsina(int tretiranaPovrsina) {
        this.tretiranaPovrsina = tretiranaPovrsina;
    }

    public long getBiljkaId() {
        return biljkaId;
    }

    public void setBiljkaId(long biljkaId) {
        this.biljkaId = biljkaId;
    }

    public String getKorisnikEmail() {
        return korisnikEmail;
    }

    public String getPoljeIme(){
        for(Polje polje : MainActivity2.poljeList)
        {
            if(polje.getId().equals(poljeId))
                return polje.getNaziv();
        }
        return null;
    }

    public String getArkodId(){
        for(Polje polje:MainActivity2.poljeList)
        {
            if(polje.getId().equals(poljeId))
                return polje.getArkodId();
        }
        return null;
    }

    public String getImePesticida(){
        for(Pesticid p : MainActivity2.pesticidList)
        {
            if(p.getId().equals(pesticidId))
                return p.getNaziv();
        }
        return null;
    }

    public String getImeBiljke()
    {
        for(Biljka b : MainActivity2.biljkaList)
        {
            if(b.getId().equals(biljkaId))
                return b.getNaziv();
        }
        return null;
    }
}
