package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testapp.entiteti.Polje;

import java.util.ArrayList;

public class NovaEvidencijaActivity extends AppCompatActivity {
    private TextView nazivPolja;
    private Spinner spinnerPolja;
    private Spinner spinnerKulture;
    private Spinner spinnerPesticida;
    private TextView nazivKulture;
    private TextView nazivPesticida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_evidencija);
        nazivPolja = findViewById(R.id.nazivPolja);
        spinnerPolja=findViewById(R.id.spinnerPolja);
        nazivKulture = findViewById(R.id.nazivKulture);
        nazivPesticida = findViewById(R.id.nazivPesticida);
        spinnerPesticida = findViewById(R.id.spinnerPesticida);
        spinnerKulture = findViewById(R.id.spinnerKulture);

        PoljeAdapter adapterPolja = new PoljeAdapter(this, (ArrayList<Polje>) MainActivity2.poljeList);
        adapterPolja.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPolja.setAdapter(adapterPolja);

        spinnerPolja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adapterPolja.getItem(position) != null)
                {
                    Polje polje = adapterPolja.getItem(position);
                    nazivPolja.setText(polje.getNaziv());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ne treba raditi ništa
            }
        });




    }
}