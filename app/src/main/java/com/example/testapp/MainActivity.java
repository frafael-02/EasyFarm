package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void loginClicked(View v) {
        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }


    public void registerClicked(View v) {
        Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(myIntent);

    }
}