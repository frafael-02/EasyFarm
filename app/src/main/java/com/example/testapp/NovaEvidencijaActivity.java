package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Entitet;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.OptionalLong;

public class NovaEvidencijaActivity extends AppCompatActivity {
    private Chronometer stoperica;
    private boolean running=false;
    private TextView nazivPolja;
    private Spinner spinnerPolja;
    private Spinner spinnerKulture;
    private Spinner spinnerPesticida;
    private TextView nazivKulture;
    private TextView nazivPesticida;
    private Button buttonStoperica;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private EditText povrsina;

    private EditText doza;

    private long selectedPolje;

    private long selectedBiljka;

    private long selectedPesticid;


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
        stoperica = findViewById(R.id.stopericaId);
        buttonStoperica = findViewById(R.id.startButton);
        povrsina = findViewById(R.id.povrsinaId);
        doza = findViewById(R.id.dozaId);

        PoljeAdapter adapterPolja = new PoljeAdapter(this, new ArrayList<>(MainActivity2.poljeList));
        adapterPolja.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPolja.setAdapter(adapterPolja);

        spinnerPolja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(adapterPolja.getItem(position) != null)
                {
                    Polje polje = adapterPolja.getItem(position);
                    selectedPolje = polje.getId();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ne treba raditi ništa
            }
        });

        KulturaAdapter kulturaAdapter = new KulturaAdapter(this, new ArrayList<>(MainActivity2.biljkaList));
        kulturaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKulture.setAdapter(kulturaAdapter);

        spinnerKulture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(kulturaAdapter.getItem(position) != null)
                {
                   Biljka biljka = kulturaAdapter.getItem(position);
                   selectedBiljka = biljka.getId();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ne treba raditi ništa
            }
        });

        PesticidAdapter pesticidAdapter = new PesticidAdapter(this, new ArrayList<>(MainActivity2.pesticidList));
        pesticidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPesticida.setAdapter(pesticidAdapter);
        spinnerPesticida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(pesticidAdapter.getItem(position) != null)
                {
                    Pesticid pesticid = pesticidAdapter.getItem(position);
                    selectedPesticid = pesticid.getId();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // ne treba raditi ništa
            }
        });







    }

    public void startStoperica(View v)
    {
        if(!running)
        {
            stoperica.setBase(SystemClock.elapsedRealtime());
            startTime = LocalDateTime.now();
            stoperica.start();
            running = true;
            buttonStoperica.setText("Zaustavi");


        }
        else if(running)
        {
            stoperica.stop();
            endTime = LocalDateTime.now();
            running=false;
            buttonStoperica.setText("Kreni");
        }
    }



    public void spremiClicked(View v)
    {
        if(doza.getText() != null && povrsina.getText() != null)
        {
            int dozaInt = Integer.parseInt(doza.getText().toString());
            int povrsinaInt = Integer.parseInt(povrsina.getText().toString());
             OptionalLong maksimalniId = MainActivity2.evidencijaList.stream()
                    .mapToLong(Entitet::getId).max();
            Evidencija evidencija = new Evidencija(maksimalniId.getAsLong() + 1L, selectedPesticid, dozaInt, selectedPolje, startTime, endTime, povrsinaInt, selectedBiljka, DatabaseQueries.getCurrentUser());

            DatabaseQueries.sendEvidencija(evidencija);
            finish();

        }


    }
}