package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class OdabranaEvidencijaActivity extends AppCompatActivity {
    public Spinner spinnerPolje;
    public Spinner spinnerSredstva;
    public Spinner spinnerBiljke;

    public EditText arkodId;

    public EditText povrsina;

    public EditText doza;

    public TextView datum;

    public EditText vrijemeStart;

    public EditText vrijemeKraj;

    private Evidencija evidencija;

    private long selectedPolje;

    private long  selectedBiljka;

    private long selectedPesticid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabrana_evidencija);

        spinnerPolje = findViewById(R.id.spinnerPolje);
        spinnerBiljke = findViewById(R.id.spinnerBiljka);
        spinnerSredstva = findViewById(R.id.spinnerSredstvo);
        povrsina =(EditText) findViewById(R.id.povrsinaId2);
        arkodId = (EditText) findViewById(R.id.arkodId);
        datum = findViewById(R.id.datumId2);
        doza = (EditText) findViewById(R.id.dozaId2);
        vrijemeKraj = findViewById(R.id.vrijemeKrajId);
        vrijemeStart = findViewById(R.id.vrijemeStartId);





        PoljeAdapter adapterPolja = new PoljeAdapter(this, new ArrayList<>(MainActivity2.poljeList));
        adapterPolja.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Intent intent = getIntent();
      evidencija = (Evidencija) intent.getSerializableExtra("evidencija");
        spinnerPolje.setAdapter(adapterPolja);
        spinnerPolje.setSelection(Long.valueOf(evidencija.getPoljeId()).intValue());
        spinnerPolje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        KulturaAdapter biljkaAdapter = new KulturaAdapter(this, new ArrayList<>(MainActivity2.biljkaList));
        biljkaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBiljke.setAdapter(biljkaAdapter);
        spinnerBiljke.setSelection(Long.valueOf(evidencija.getBiljkaId()).intValue());
        spinnerBiljke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(biljkaAdapter.getItem(position) != null)
                {
                    Biljka biljka = biljkaAdapter.getItem(position);
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
        spinnerSredstva.setAdapter(pesticidAdapter);
        spinnerSredstva.setSelection(Long.valueOf(evidencija.getPesticidId()).intValue());
        spinnerSredstva.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        povrsina.setText(String.valueOf(evidencija.getTretiranaPovrsina()));
        doza.setText(String.valueOf(evidencija.getKoristenaDoza()));

        String dateString = evidencija.getVrijemeStart().format(DateTimeFormatter.ofPattern("dd. MM. yyyy."));
        datum.setText(dateString);
        arkodId.setText(evidencija.getArkodId());
        vrijemeStart.setText(evidencija.getVrijemeStart().getHour() + ":" + evidencija.getVrijemeStart().getMinute());
        vrijemeKraj.setText(evidencija.getVrijemeKraj().getHour() + ":" + evidencija.getVrijemeKraj().getMinute());






    }

    public void pickDatum(View v)
    {
        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(OdabranaEvidencijaActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.", Locale.ITALY);
                        datum.setText(sdf.format(mCalendar.getTime()));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void spremiClicked(View v)
    {
        int dozaInt = Integer.parseInt(doza.getText().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy. HH:mm");
        String vrijemeStartString = datum.getText() + " " + vrijemeStart.getText();

        LocalDateTime vrijemeStart2 = LocalDateTime.parse(vrijemeStartString, formatter);

        String vrijemeKrajString = datum.getText() + " " + vrijemeKraj.getText();
        LocalDateTime vrijemeKraj2 = LocalDateTime.parse(vrijemeKrajString, formatter);
        int povrsinaInt = Integer.parseInt(povrsina.getText().toString());
        Evidencija evidencijaNew = new Evidencija(evidencija.getId(), selectedPesticid, dozaInt, selectedPolje, vrijemeStart2, vrijemeKraj2, povrsinaInt,selectedBiljka, DatabaseQueries.getCurrentUser());
        if(!evidencija.equals(evidencijaNew))
        {
            DatabaseQueries.editEvidencija(evidencijaNew);
            finish();
        }

        else Toast.makeText(this, "Nisu unesene nikakve promjene", Toast.LENGTH_SHORT).show();


    }




    }
