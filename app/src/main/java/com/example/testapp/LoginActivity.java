package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import android.os.Handler;


import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

public class LoginActivity extends AppCompatActivity {

    public  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ShimmerFrameLayout shimmerFrameLayout;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        shimmerFrameLayout=findViewById(R.id.shimmer_view);
        shimmerFrameLayout.setVisibility(View.INVISIBLE);

        final ImageView imageView = findViewById(R.id.main_square);
        final EditText editTextEmail=findViewById(R.id.emailAddressId);
        final EditText editPassword=findViewById(R.id.passwordId);
        final TextView titleBig=findViewById(R.id.dobrodosli);
        final TextView titleSmall=findViewById(R.id.tekst);
        Button buttonRegister = findViewById(R.id.loginButton);

        buttonRegister((View) imageView, (View) editTextEmail,(View)editPassword,
                (View) titleBig,(View)titleSmall,(View)buttonRegister,buttonRegister);
        ImageButton buttonBack= findViewById(R.id.backArrow);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        LoginActivity.this, imageView, ViewCompat.getTransitionName(imageView));
                startActivity(intent, options.toBundle());
            }
        });





    }

    private void buttonRegister(View imageView, View editTextEmail,
                                View editpassword,View titleBig,
                                View titleSmall,View buttonViewRegister,
                                Button buttonRegister) {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair<View, String> p1 = Pair.create(imageView, "main_square");
                Pair<View, String> p2 = Pair.create(editTextEmail, "emailTransition");
                Pair<View, String> p3 = Pair.create(editpassword, "passwordTransition");
                Pair<View, String> p4 = Pair.create(titleBig, "titleBigTransition");
                Pair<View, String> p5 = Pair.create(titleSmall, "titleSmallTransition");
                Pair<View, String> p6 = Pair.create(buttonViewRegister, "buttonTransition");

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(LoginActivity.this,p1,p2,p3,p4,p5);
                startActivity(intent, options.toBundle());
            }
        });
    }

    public void loginClicked(View v) {
        String email = ((EditText) findViewById(R.id.emailAddressId)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordId)).getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Unesite email i šifru!", Toast.LENGTH_LONG).show();
        } else {



            loginAccountInFirebase(email, password);
        }
    }


    void loginAccountInFirebase(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                        constraintLayout = findViewById(R.id.screenId);
                        shimmerFrameLayout.setVisibility(View.VISIBLE);
                        constraintLayout.setVisibility(View.INVISIBLE);
                        shimmerFrameLayout.startShimmer();
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {

                            shimmerFrameLayout.stopShimmer();
                            shimmerFrameLayout.setVisibility(View.INVISIBLE);

                        }, 2000);

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

/*
    public void registerClicked(View v)
    {
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(myIntent);
    }
*/
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





}
