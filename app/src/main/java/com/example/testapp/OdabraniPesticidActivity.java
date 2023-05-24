package com.example.testapp;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.testapp.databinding.ActivityOdabraniPesticidBinding;

public class OdabraniPesticidActivity extends AppCompatActivity {

    ImageView imageView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabrani_pesticid);

        String name= getIntent().getStringExtra("NAME");
        int slika=getIntent().getIntExtra("SLIKA",0);

        imageView=findViewById(R.id.slika_odabrani_pesticid);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);

        imageView.setImageResource(slika);
        collapsingToolbarLayout.setTitle(name);




    }


}