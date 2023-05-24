package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Pesticid;

public class NovPesticidActivity extends AppCompatActivity {

    public EditText naziv;
    public EditText doza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nov_pesticid);
        naziv = findViewById(R.id.nazivPesticida);
        doza = findViewById(R.id.dozaId);

    }


    public void spremiClicked(View v)
    {
        if(naziv.getText().toString().equals("") || doza.getText().toString().equals(""))
        {
            Toast.makeText(this, "Unesite sva polja!", Toast.LENGTH_SHORT).show();
        }
        else{String nazivString = naziv.getText().toString();
            int dozaInt = Integer.parseInt(doza.getText().toString());
            Pesticid pesticid = new Pesticid(MainActivity2.maxIdPesticid+1, nazivString, dozaInt, 0, "nista", "nista", false, 0);
            DatabaseQueries.sendPesticid(pesticid);
            finish();
        }


    }
    public void backArrow(View v){
        finish();
    }



}