package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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

        final ImageView imageView = findViewById(R.id.main_square);
        final EditText editTextEmail=findViewById(R.id.emailAddressId);
        final EditText editPassword=findViewById(R.id.passwordId);
        final TextView titleBig=findViewById(R.id.dobrodosli);
        final TextView titleSmall=findViewById(R.id.tekst);
        ImageButton buttonBack = findViewById(R.id.backArrow);
        Button loginButton=findViewById(R.id.loginButton);
        buttonBack((View) imageView, (View) editTextEmail,(View)editPassword,
                (View) titleBig,(View)titleSmall,(View)buttonBack,buttonBack,loginButton);


    }

    private void buttonBack(View imageView, View editTextEmail,
                                View editpassword,View titleBig,
                                View titleSmall,View buttonViewRegister,
                                ImageButton buttonRegister,Button loginButton)
    {
        Pair<View, String> p1 = Pair.create(imageView, "main_square");
        Pair<View, String> p2 = Pair.create(editTextEmail, "emailTransition");
        Pair<View, String> p3 = Pair.create(editpassword, "passwordTransition");
        Pair<View, String> p4 = Pair.create(titleBig, "titleBigTransition");
        Pair<View, String> p5 = Pair.create(titleSmall, "titleSmallTransition");
        Pair<View, String> p6 = Pair.create(buttonViewRegister, "buttonTransition");
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentMethod(p1, p2, p3, p4, p5);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentMethod(p1, p2, p3, p4, p5);
            }
        });
    }

    private void intentMethod(Pair<View, String> p1, Pair<View, String> p2, Pair<View, String> p3,
                              Pair<View, String> p4, Pair<View, String> p5) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(RegisterActivity.this, p1, p2, p3, p4, p5);
        startActivity(intent, options.toBundle());
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


}