package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.testapp.database.GlideApp;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.search.SearchActivty;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
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
    Button sigurnostButton;
    Button primjenaButton;
    Button preporukaButton;
    TextView sigurnostTextView;

    TextView formulacijaTextView;

    TextView nacinDjelovanjaTextView;
    TextView primejnaTextView;
    TextView preporukaTextView;

    TextView emailTextView;

    TextView telefonTextView;

    TextView opis;

    TextView cijena;
    ImageButton backArrow;
    boolean sigurnost_boolean=true;
    boolean primjena_boolean=true;
    boolean preporuka_boolean=true;

    FloatingActionButton buttonVrsta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabrani_pesticid);
        Pesticid pesticid = (Pesticid) getIntent().getSerializableExtra("PESTICID");
        buttonVrsta = findViewById(R.id.vrstaId);
        if(pesticid.getVrsta() == 1)
        {
            buttonVrsta.setImageResource(R.drawable.herbicid);
        }
        else if(pesticid.getVrsta() == 2)
        {
            buttonVrsta.setImageResource(R.drawable.fungicid);
        }
        String name= pesticid.getNaziv();
        String imageUrl = getIntent().getStringExtra("SLIKA");
        String opisText = pesticid.getOpis();
        String email = pesticid.getProizvodjac().getEmail();
        String telefon = pesticid.getProizvodjac().getTelefon();



        imageView=findViewById(R.id.slika_odabrani_pesticid);
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl);
        GlideApp.with(this).load(storageReference).into(imageView);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle(name);

        opis=findViewById(R.id.opisTextView);
        opis.setText(opisText);

        sigurnostButton=findViewById(R.id.sigurnost_button);
        primjenaButton=findViewById(R.id.primjena_button);
        preporukaButton=findViewById(R.id.preporuka_button);
        nacinDjelovanjaTextView = findViewById(R.id.djelovanje_textView);
        formulacijaTextView=findViewById(R.id.formulacija_textView);
        cijena=findViewById(R.id.cijena_textView);


        sigurnostTextView=findViewById(R.id.sigurnost_textView);
        primejnaTextView=findViewById(R.id.primjena_textView);
        preporukaTextView=findViewById(R.id.preporuka_textView);
        telefonTextView=findViewById(R.id.telefon);
        emailTextView=findViewById(R.id.mail);
        cijena.setText(pesticid.getCijena() + "€");
        sigurnostTextView.setText(pesticid.getMjereSigurnosti());
        primejnaTextView.setText(pesticid.getPrimjena());
        preporukaTextView.setText(pesticid.getPrimjena());
        nacinDjelovanjaTextView.setText(pesticid.getNacinDjelovanja());
        formulacijaTextView.setText(pesticid.getFormulacija());
        emailTextView.setText(email);
        telefonTextView.setText(telefon);
        backArrow=findViewById(R.id.backArrow);





        AlphaAnimation outAnimation = new AlphaAnimation(1.0f,0.0f);
        outAnimation.setDuration(300);
        outAnimation.setFillAfter(true);
        outAnimation.setFillAfter(true);
        AlphaAnimation inAnimation = new AlphaAnimation(0.0f,1.0f);
        inAnimation.setDuration(300);
        inAnimation.setFillAfter(true);
        inAnimation.setFillAfter(true);

        sigurnostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sigurnost_boolean){
                    sigurnostTextView.startAnimation(inAnimation);
                    sigurnostTextView.setVisibility(View.VISIBLE);
                    sigurnost_boolean=false;
                }
                else{
                    sigurnostTextView.startAnimation(outAnimation);
                    sigurnostTextView.setVisibility(View.GONE);
                    sigurnost_boolean=true;
                }
            }
        });
        preporukaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(preporuka_boolean){
                    preporukaTextView.startAnimation(inAnimation);
                    preporukaTextView.setVisibility(View.VISIBLE);
                    preporuka_boolean=false;
                }
                else{
                    preporukaTextView.startAnimation(outAnimation);
                    preporukaTextView.setVisibility(View.GONE);
                    preporuka_boolean=true;
                }
            }
        });
        primjenaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(primjena_boolean){
                    primejnaTextView.startAnimation(inAnimation);
                    primejnaTextView.setVisibility(View.VISIBLE);
                    primjena_boolean=false;
                }
                else{
                    primejnaTextView.startAnimation(outAnimation);
                    primejnaTextView.setVisibility(View.GONE);
                    primjena_boolean=true;
                }
            }
        });

        buttonVrsta.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view){
               Intent intent = new Intent(getApplicationContext(), SearchActivty.class);
               intent.putExtra("VRSTA", pesticid.getVrsta());
               startActivity(intent);
            }
        });






    }
    public void backArrow(View view)
    {     finish();    }

}