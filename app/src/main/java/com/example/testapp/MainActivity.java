package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    } else {
                        firebaseAuth.getCurrentUser().sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Molimo potvrdite svoju email adresu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

   public void registerClicked(View v)
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
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Potvrdite adresu", Toast.LENGTH_SHORT).show();
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    firebaseAuth.signOut();
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}