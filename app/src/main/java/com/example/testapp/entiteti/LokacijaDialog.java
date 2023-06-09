package com.example.testapp.entiteti;

import static com.example.testapp.HomeFragment.string;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.testapp.HomeFragment;
import com.example.testapp.MainActivity2;
import com.example.testapp.PoljeAdapter2;
import com.example.testapp.R;
import com.example.testapp.api.OpenMeteoApiClient;
import com.example.testapp.api.PoljeAPI;

import java.util.ArrayList;

public class LokacijaDialog extends AppCompatDialogFragment {

   public static LokacijaDialog.LokacijaDialogListener listener;
    private Button saveButton;
    private Button cancleButton;
    private Spinner spinner;

    private Polje polje;

    private PoljeAdapter2 poljeAdapter;

    public static void setListener(LokacijaDialogListener listener2){
        listener = listener2;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_lokacija, null);
        builder.setView(view);/*
                .setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Spremi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email = editTextEmail.getText().toString();
                        String punoIme = editTextIme.getText().toString();
                        int mibpg = Integer.parseInt(editTextMibpg.getText().toString());
                        listener.editInformation(punoIme,mibpg );
                    }
                });*/

        saveButton=view.findViewById(R.id.btnSpremi);
        cancleButton=view.findViewById(R.id.btnOdustani);
        spinner=view.findViewById(R.id.spinnerPolje);
        poljeAdapter = new PoljeAdapter2(getContext(), (ArrayList<Polje>) MainActivity2.poljeList);
        spinner.setAdapter(poljeAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                polje = poljeAdapter.getItem(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

               listener.setLokacija(polje);
               dismiss();
            }

        });
        cancleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                dismiss();
            }

        });




        return builder.create();
    }





    public interface LokacijaDialogListener {
        void setLokacija(Polje p);
    }

}

