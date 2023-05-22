package com.example.testapp.shop;

import com.example.testapp.entiteti.Pesticid;

public class ShopChildModelClass {

    int image;
    String text;

    Pesticid pesticid;

    public ShopChildModelClass(int image, Pesticid pesticid) {
        this.image = image;
        this.pesticid = pesticid;
        text = pesticid.getNaziv();
    }

    public String getItemName() {
    return text;
    }

    public Pesticid getPesticid() {
        return pesticid;
    }
}
