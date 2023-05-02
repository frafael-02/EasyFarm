package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    public  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }


    public void loginClicked(View v) {
        String email = ((EditText) findViewById(R.id.emailAddressId)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordId)).getText().toString();
        System.out.println(email + " " + password);
        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Unesite email i šifru!", Toast.LENGTH_LONG).show();
        } else
            loginAccountInFirebase(email, password);
    }


    void loginAccountInFirebase(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                        Intent myIntent = new Intent(LoginActivity.this, MainActivity2.class);
                        startActivity(myIntent);


                    } else {
                        firebaseAuth.getCurrentUser().sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Molimo potvrdite svoju email adresu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void registerClicked(View v)
    {
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(myIntent);
    }

    public void forgotClicked(View v)
    {
        String email = ((EditText) findViewById(R.id.emailAddressId)).getText().toString();
        if(email.equals(""))
        {
            Toast.makeText(this, "Unesite email za reset", Toast.LENGTH_SHORT).show();
        }
        else{
            firebaseAuth.sendPasswordResetEmail(email);
            Toast.makeText(this, "Reset link je poslan na email!", Toast.LENGTH_SHORT).show();
        }
    }

    public void backArrow(View v)
    {
        finish();
    }

    /*public void registerClicked(View v)
    {

        String email = ((EditText) findViewById(R.id.emailAddressId)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordId)).getText().toString();
        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(this, "Unesite email i šifru", Toast.LENGTH_SHORT).show();
        }
        else {

            registerAccountInFirebase(email, password);
        }



    }



    void registerAccountInFirebase(String email, String password)
    {


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Potvrdite adresu", Toast.LENGTH_SHORT).show();
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }*/
}
