package com.example.testapp.entiteti;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.testapp.MainActivity2;
import com.example.testapp.R;

public class AccountDialog extends AppCompatDialogFragment {
    private TextView editTextEmail;
    private EditText editTextIme;
    private EditText editTextMibpg;
    private AccountDialogListener listener;
    private Button saveButton;
    private Button cancleButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
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
        editTextEmail = view.findViewById(R.id.edit_username);
        editTextIme = view.findViewById(R.id.edit_ime);
        editTextMibpg = view.findViewById(R.id.edit_mibpg);

        saveButton=view.findViewById(R.id.btnSpremi);
        cancleButton=view.findViewById(R.id.btnOdustani);

        editTextEmail.setText(MainActivity2.korisnik.getEmail());
        editTextIme.setText(MainActivity2.korisnik.getPunoIme());
        System.out.println(MainActivity2.korisnik.getMibpg());
        editTextMibpg.setText(String.valueOf(MainActivity2.korisnik.getMibpg()));

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String email = editTextEmail.getText().toString();
                String punoIme = editTextIme.getText().toString();
                int mibpg = Integer.parseInt(editTextMibpg.getText().toString());
                listener.editInformation(punoIme,mibpg );
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AccountDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement AccountDialogListener");
        }
    }


    public interface AccountDialogListener {
        void editInformation(String punoIme, int mibpg);
    }

}
