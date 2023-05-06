package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.databinding.ActivityMain2Binding;
import com.example.testapp.databinding.ActivityMainBinding;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Korisnik;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
public class MainActivity2 extends AppCompatActivity{
    //FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);
    //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
    //DatabaseReference myRef = firebaseDatabase.getReference("pesticidi");
    public static  List<Pesticid> pesticidList = DatabaseQueries.getPesticidi();
    public static List<Biljka> biljkaList=DatabaseQueries.getBiljke();
    public static List<Polje> poljeList=DatabaseQueries.getPolja();
    public static List<Evidencija> evidencijaList=DatabaseQueries.getEvidencija();
   public static long maxId;
   public static long maxIdPolje;

   public static long maxIdPesticid;

   public static Korisnik korisnik;
    ActivityMain2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        replaceFragment(new HomeFragment());
        break;
    }
    return true;
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



/*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TableLayout tableLayout = findViewById(R.id.tablica);
                tableLayout.removeAllViews();
                TableRow headerRow = new TableRow(getApplicationContext());
                headerRow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                ));


                TextView imeHeader = new TextView(getApplicationContext());
                imeHeader.setText("Ime");
                imeHeader.setPadding(10,10,10,10);
                imeHeader.setTypeface(Typeface.DEFAULT_BOLD); // make the header bold
                headerRow.addView(imeHeader);

                TextView biljkaHeader = new TextView(getApplicationContext());
                biljkaHeader.setText("Cijena");
                biljkaHeader.setPadding(10,10,10,10);
                biljkaHeader.setTypeface(Typeface.DEFAULT_BOLD); // make the header bold
                headerRow.addView(biljkaHeader);
                TextView dozaHeader = new TextView(getApplicationContext());
                dozaHeader.setText("Doza");
                dozaHeader.setPadding(10,10,10,10);
                dozaHeader.setTypeface(Typeface.DEFAULT_BOLD); // make the header bold
                headerRow.addView(dozaHeader);
                tableLayout.addView(headerRow);

                for (DataSnapshot recordSnapshot : dataSnapshot.getChildren()) {
                    String imePesticida = recordSnapshot.getKey().toString();
                    Long cijena = recordSnapshot.child("cijena").getValue(Long.class);
                    Long doza = recordSnapshot.child("doza").getValue(Long.class);
                    TableRow dataRow = new TableRow(getApplicationContext());
                    dataRow.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT
                    ));

                    TextView attribute1View = new TextView(getApplicationContext());
                    attribute1View.setText(imePesticida);
                    attribute1View.setPadding(10,10,10,10);
                    dataRow.addView(attribute1View);
                    TextView attribute2View = new TextView(getApplicationContext());
                    if(cijena != null)
                    {
                        attribute2View.setText(cijena.toString());
                        dataRow.addView(attribute2View);
                    }
                    TextView attribute3View= new TextView(getApplicationContext());
                    if(doza != null)
                    {
                        attribute3View.setText(doza.toString());
                        dataRow.addView(attribute3View);
                    }


                    tableLayout.addView(dataRow);
                    attribute2View.setPadding(10,10,10,10);
                    attribute3View.setPadding(10,10,10,10);




                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur
            }
        });

    }


    public void addRecord (View v)
    {

        TextView newKey = findViewById(R.id.newKeyId);
        String newKeyString = newKey.getText().toString();
       String newKeyRecord = newKeyString;
        DatabaseReference newRecord = myRef.child(newKeyRecord);
        newRecord.child("doza").setValue(40);
        newRecord.child("cijena").setValue(10);

    }

    public void editClicked(View v)
    {
        TextView newKey = findViewById(R.id.newKeyId);
        String newKeyString = newKey.getText().toString();
        String newKeyRecord = newKeyString;
        Map<String, Object> updates = new HashMap<>();
        updates.put("herbicid2", null);
        //myRef.child("herbicid"

    }*/

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment, null);
        fragmentTransaction.commit();

    }
}