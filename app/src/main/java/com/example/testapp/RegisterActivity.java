package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.database.DatabaseQueries;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }



    public void registerClicked(View v)
    {

        String email = ((EditText) findViewById(R.id.emailAddressId)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordId)).getText().toString();
        String repeatPassword = ((EditText) findViewById(R.id.repeatPasswordId)).getText().toString();
        if(email.equals("") || password.equals("") || repeatPassword.equals(""))
        {
            Toast.makeText(this, "Unesite email i šifru", Toast.LENGTH_SHORT).show();
        }
        else {


            registerAccountInFirebase(email, password);
        }



    }

    public void backArrow(View v)
    {
        finish();
    }



    void registerAccountInFirebase(String email, String password)
    {


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Potvrdite adresu", Toast.LENGTH_SHORT).show();
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    DatabaseQueries.addKorisnik(firebaseAuth.getCurrentUser());
                    firebaseAuth.signOut();
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void loginClicked(View v) {
        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }
}