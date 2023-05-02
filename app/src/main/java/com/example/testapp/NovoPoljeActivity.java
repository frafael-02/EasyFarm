package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Polje;

import java.util.ArrayList;
import java.util.List;

public class NovoPoljeActivity extends AppCompatActivity {

    public EditText nazivPolja;
    public EditText arkodId;
    public Spinner spinnerBiljke;
    public EditText povrsina;

    private long selectedBiljka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_polje);

        nazivPolja = findViewById(R.id.nazivId);
        arkodId = findViewById(R.id.arkodId);
        spinnerBiljke=findViewById(R.id.spinnerBiljka);
        povrsina=findViewById(R.id.povrsinaId);

        KulturaAdapter biljkaAdapter = new KulturaAdapter(this, new ArrayList<>(MainActivity2.biljkaList));
        biljkaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBiljke.setAdapter(biljkaAdapter);

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
    }

    public void spremiClicked(View v)
    {

        if(!arkodId.getText().toString().equals("") && !nazivPolja.getText().toString().equals("") && selectedBiljka != 0L && !povrsina.getText().toString().equals(""))
        {
            Polje polje = new Polje(MainActivity2.maxIdPolje+1, arkodId.getText().toString(), nazivPolja.getText().toString(),selectedBiljka, DatabaseQueries.getCurrentUser(), Integer.parseInt(povrsina.getText().toString()));
            DatabaseQueries.sendPolje(polje);
            finish();
        }
        else Toast.makeText(this, "Unesite sve podatke", Toast.LENGTH_SHORT).show();

    }
}