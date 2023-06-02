package com.example.testapp;

import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Polje;

import java.util.List;

public interface EvidencijaLoadListener {
    void onDataLoaded(List<Evidencija> evidencijaList);
}
