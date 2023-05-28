package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.testapp.database.DatabaseQueries;


import com.example.testapp.databinding.ActivityMain2Binding;
import com.example.testapp.entiteti.AccountDialog;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Korisnik;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;
import com.example.testapp.entiteti.Proizvodjac;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;
public class MainActivity2 extends AppCompatActivity implements AccountDialog.AccountDialogListener {

    public static  List<Pesticid> pesticidList = DatabaseQueries.getPesticidi();
    public static List<Biljka> biljkaList=DatabaseQueries.getBiljke();
    public static List<Polje> poljeList=DatabaseQueries.getPolja();
    public static List<Evidencija> evidencijaList=DatabaseQueries.getEvidencija();

    public static List<Proizvodjac> proizvodjacList = DatabaseQueries.getProizvodjaci();
   public static long maxId;
   public static long maxIdPolje;

   public static String responseString;

   public static long maxIdPesticid;

   public static Korisnik korisnik;
    ActivityMain2Binding binding;
BottomNavigationView bottomNavigationView;
    FloatingActionButton mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setBackgroundColor(Color.TRANSPARENT);

        if(savedInstanceState == null)
        {
            replaceFragment(new HomeFragment());
        }
        DatabaseQueries.dohvatiKorisnik(FirebaseAuth.getInstance().getCurrentUser().getUid());



        binding.bottomNavigation.setOnItemSelectedListener(item -> {
switch(item.getItemId()){
    case R.id.btnHome:
        replaceFragment(new HomeFragment());
        break;
    case R.id.btnAI:
        replaceFragment(new AiFragment());
        break;
    case R.id.btnEvidencija:
        replaceFragment(new EvidencijaFragment());
        break;
    case R.id.shop:
        replaceFragment(new ShopFragment());
        break;
    }
    return true;
        });




        mButton = findViewById(R.id.addEvidencijaButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity2.this, NovaEvidencijaActivity.class);
                startActivity(myIntent);
            }
        });


        /*  ImageButton btnEvidencija = findViewById(R.id.btnEvidencija);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnPlaner = findViewById(R.id.btnPlaner);


    if(savedInstanceState == null)
    {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, HomeFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();
    }



    btnHome.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            btnHome.setColorFilter(getResources().getColor(R.color.white));
            btnEvidencija.clearColorFilter();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, HomeFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // name can be null
                    .commit();
        }
    });



        btnEvidencija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEvidencija.setColorFilter(R.color.iconYellow,PorterDuff.Mode.SRC_IN);
                btnHome.clearColorFilter();;


                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, EvidencijaFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();


            }
        });


        btnPlaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               btnEvidencija.setColorFilter(R.color.iconGrey);
                btnHome.setColorFilter(R.color.iconGrey);
                btnPlaner.setColorFilter(R.color.iconYellow);

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, PlanerFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });*/









    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment, null);
        fragmentTransaction.commit();

    }

    @Override
    public void editInformation(String punoIme, int mibpg) {
        MainActivity2.korisnik.setPunoIme(punoIme);
        MainActivity2.korisnik.setMibpg(mibpg);
        DatabaseQueries.urediKorisnik(FirebaseAuth.getInstance().getCurrentUser().getUid(), punoIme, mibpg);
    }
}