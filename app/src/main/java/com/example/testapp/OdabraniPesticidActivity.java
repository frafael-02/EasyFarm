package com.example.testapp;

import android.os.Bundle;

import com.example.testapp.database.GlideApp;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.testapp.databinding.ActivityOdabraniPesticidBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class OdabraniPesticidActivity extends AppCompatActivity {

    ImageView imageView;
    CollapsingToolbarLayout collapsingToolbarLayout;

    TextView opis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabrani_pesticid);

        String name= getIntent().getStringExtra("NAME");
        String imageUrl = getIntent().getStringExtra("SLIKA");
        String opisText = getIntent().getStringExtra("OPIS");

        imageView=findViewById(R.id.slika_odabrani_pesticid);
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl);
        GlideApp.with(this).load(storageReference).into(imageView);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);


        collapsingToolbarLayout.setTitle(name);

        opis=findViewById(R.id.opisTextView);
        opis.setText(opisText);






    }


}