package com.example.testapp.database;

import androidx.annotation.NonNull;

import com.example.testapp.MainActivity2;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueries {

    public static List<Pesticid> getPesticidi()
    {   List<Pesticid> pesticidiList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = firebaseDatabase.getReference("pesticidi");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot pestSnapshot : dataSnapshot.getChildren()) {
                    Long id = Long.valueOf(pestSnapshot.getKey());
                    String attribute1 = pestSnapshot.child("naziv").getValue(String.class);
                    Integer attribute2 = pestSnapshot.child("dozaMax").getValue(Integer.class);

                    Pesticid pesticid = new Pesticid(id, attribute1, attribute2);
                    pesticidiList.add(pesticid);
                }

                // Do something with the list of Pesticid objects
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });
    return pesticidiList;
    }



    public static List<Biljka> getBiljke()
    {   List<Biljka> biljkaList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = firebaseDatabase.getReference("biljka");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot pestSnapshot : dataSnapshot.getChildren()) {
                    Long id = Long.valueOf(pestSnapshot.getKey());
                    String attribute1 = pestSnapshot.child("naziv").getValue(String.class);


                    Biljka biljka= new Biljka(id, attribute1);
                    biljkaList.add(biljka);
                }

                // Do something with the list of Pesticid objects
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });
        return biljkaList;
    }

    public static List<Polje> getPolja()
    {   List<Polje> poljeList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = firebaseDatabase.getReference("polje");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot pestSnapshot : dataSnapshot.getChildren()) {
                    Long id = Long.valueOf(pestSnapshot.getKey());
                    String attribute1 = pestSnapshot.child("arkodId").getValue(String.class);
                    String attribute2 = pestSnapshot.child("naziv").getValue(String.class);
                    String korisnikEmail = pestSnapshot.child("korisnikEmail").getValue(String.class);
                    List<Biljka> biljkaList = new ArrayList<>();

                    DatabaseReference myRef2 = firebaseDatabase.getReference("poljeKulture");
                    myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                Long key = Long.valueOf(dataSnapshot1.getKey());
                                if(key.equals(id))
                                {

                                   Long biljkaId= dataSnapshot1.child(key.toString()).getValue(Long.class);
                                    for(Biljka b : MainActivity2.biljkaList)
                                    {
                                        if(b.getId().equals(biljkaId))
                                            biljkaList.add(b);
                                    }
                                }


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    Polje polje = new Polje(id, attribute1, attribute2, biljkaList, korisnikEmail);
                    System.out.println("Dodavanje polja");
                    poljeList.add(polje);
                }

                // Do something with the list of Pesticid objects
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });
        return poljeList;
    }

    public static List<Evidencija> getEvidencija()
    {   List<Evidencija> evidencijaList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = firebaseDatabase.getReference("evidencija");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot pestSnapshot : dataSnapshot.getChildren()) {
                    Long id = Long.valueOf(pestSnapshot.getKey());
                    Long biljkaId = pestSnapshot.child("biljkaId").getValue(Long.class);
                    Integer koristenaDoza = pestSnapshot.child("koristenaDoza").getValue(Integer.class);
                    Long pesticidId = pestSnapshot.child("pesticidId").getValue(Long.class);
                    Long poljeId = pestSnapshot.child("poljeId").getValue(Long.class);
                    Integer tretiranaPovrsina = pestSnapshot.child("tretiranaPovrsina").getValue(Integer.class);
                    String vrijemeStart = pestSnapshot.child("vrijemeStart").getValue(String.class);
                    String vrijemeKraj = pestSnapshot.child("vrijemeKraj").getValue(String.class);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime start = LocalDateTime.parse(vrijemeStart, formatter);
                    LocalDateTime kraj = LocalDateTime.parse(vrijemeKraj, formatter);
                    String korisnikEmail = pestSnapshot.child("korisnikEmail").getValue(String.class);

                    Evidencija evidencija = new Evidencija(id, pesticidId, koristenaDoza, poljeId,start, kraj, tretiranaPovrsina, biljkaId, korisnikEmail );

                    evidencijaList.add(evidencija);

                }

                // Do something with the list of Pesticid objects
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
                System.out.println("Error kod ucitavanja evidencije");
            }
        });
        return evidencijaList;
    }




}
