package com.example.testapp.entiteti;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Polje extends Entitet implements Serializable {

    String arkodId;
    String naziv;
    long biljkaId;

    String korisnikEmail;

    int povrsina;

    public Polje(Long id, String arkodId, String naziv, long biljkaId, String korisnikEmail, int povrsina) {
        super(id);
        this.arkodId = arkodId;
        this.naziv = naziv;
        this.biljkaId=biljkaId;
        this.korisnikEmail = korisnikEmail;
        this.povrsina = povrsina;
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


    public long getBiljkaId() {
        return biljkaId;
    }

    public void setBiljkaId(long biljkaId) {
        this.biljkaId = biljkaId;
    }

    public void setKorisnikEmail(String korisnikEmail) {
        this.korisnikEmail = korisnikEmail;
    }

    public void setPovrsina(int povrsina) {
        this.povrsina = povrsina;
    }

    public String getKorisnikEmail() {
        return korisnikEmail;
    }

    public int getPovrsina(){return povrsina;}

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
            else if(biljkaId != ((Polje) o).getBiljkaId())
                result++;
            else if(!korisnikEmail.equals(((Polje) o).getKorisnikEmail()))
                result++;
            else if(povrsina != ((Polje) o).getPovrsina())
                result++;

            return (result == 0);
        }
        else return false;
    }
}
