package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity{
    //FirebaseApp firebaseApp = FirebaseApp.initializeApp(this);
    //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
    //DatabaseReference myRef = firebaseDatabase.getReference("pesticidi");

    public static  List<Pesticid> pesticidList = DatabaseQueries.getPesticidi();
    public static List<Biljka> biljkaList=DatabaseQueries.getBiljke();
    public static List<Polje> poljeList=DatabaseQueries.getPolja();
    public static List<Evidencija> evidencijaList=DatabaseQueries.getEvidencija();

    public boolean newEvidencijaAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Button btnEvidencija = findViewById(R.id.btnEvidencija);

    Button btnHome = findViewById(R.id.btnHome);



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

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, EvidencijaFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();


            }
        });

        Button btnPlaner = findViewById(R.id.btnPlaner);
        btnPlaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, PlanerFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });




        /*myRef.addValueEventListener(new ValueEventListener() {
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









}