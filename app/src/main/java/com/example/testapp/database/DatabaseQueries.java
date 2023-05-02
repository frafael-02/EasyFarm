package com.example.testapp.database;

import androidx.annotation.NonNull;

import com.example.testapp.MainActivity;
import com.example.testapp.MainActivity2;
import com.example.testapp.entiteti.Biljka;
import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
                    MainActivity2.maxIdPolje=id;
                    String attribute1 = pestSnapshot.child("arkodId").getValue(String.class);
                    String attribute2 = pestSnapshot.child("naziv").getValue(String.class);
                    String korisnikEmail = pestSnapshot.child("korisnikEmail").getValue(String.class);
                    Long biljkaId = pestSnapshot.child("biljkaId").getValue(Long.class);
                    int povrsina = pestSnapshot.child("povrsina").getValue(Integer.class);

                    if(korisnikEmail.equals(getCurrentUser()))
                    {
                        Polje polje = new Polje(id, attribute1, attribute2, biljkaId, korisnikEmail, povrsina);

                        poljeList.add(polje);
                    }

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
                evidencijaList.clear();



                for (DataSnapshot pestSnapshot : dataSnapshot.getChildren()) {
                    Long id = Long.valueOf(pestSnapshot.getKey());
                    MainActivity2.maxId = id;

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
                    if(korisnikEmail.equals(getCurrentUser()))
                    {
                        Evidencija evidencija = new Evidencija(id, pesticidId, koristenaDoza, poljeId,start, kraj, tretiranaPovrsina, biljkaId, korisnikEmail );
                        evidencijaList.add(evidencija);
                    }


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

    public static String getCurrentUser()
    {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
            return user.getEmail();
        else
            return "No user logged in";
    }

    public static void sendEvidencija(Evidencija evidencija)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = firebaseDatabase.getReference("evidencija");
        DatabaseReference newRecordId = myRef.child(String.valueOf(evidencija.getId()));
        HashMap<String, Object> newRecordMap = new HashMap<>();
        newRecordMap.put("biljkaId", evidencija.getBiljkaId());
        newRecordMap.put("korisnikEmail", evidencija.getKorisnikEmail());
        newRecordMap.put("koristenaDoza", evidencija.getKoristenaDoza());
        newRecordMap.put("pesticidId", evidencija.getPesticidId());
        newRecordMap.put("poljeId", evidencija.getPoljeId());
        newRecordMap.put("tretiranaPovrsina", evidencija.getTretiranaPovrsina());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String vrijemeKraj = evidencija.getVrijemeKraj().format(formatter);
        String vrijemeStart = evidencija.getVrijemeStart().format(formatter);

        newRecordMap.put("vrijemeKraj", vrijemeKraj);
        newRecordMap.put("vrijemeStart", vrijemeStart);

        newRecordId.setValue(newRecordMap);
       // MainActivity2.evidencijaList.add(evidencija);

    }

    public static void editEvidencija(Evidencija evidencija)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = firebaseDatabase.getReference("evidencija");
        DatabaseReference newRecordId = myRef.child(String.valueOf(evidencija.getId()));
        HashMap<String, Object> newRecordMap = new HashMap<>();
        newRecordMap.put("biljkaId", evidencija.getBiljkaId());
        newRecordMap.put("korisnikEmail", evidencija.getKorisnikEmail());
        newRecordMap.put("koristenaDoza", evidencija.getKoristenaDoza());
        newRecordMap.put("pesticidId", evidencija.getPesticidId());
        newRecordMap.put("poljeId", evidencija.getPoljeId());
        newRecordMap.put("tretiranaPovrsina", evidencija.getTretiranaPovrsina());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String vrijemeKraj = evidencija.getVrijemeKraj().format(formatter);
        String vrijemeStart = evidencija.getVrijemeStart().format(formatter);

        newRecordMap.put("vrijemeKraj", vrijemeKraj);
        newRecordMap.put("vrijemeStart", vrijemeStart);
        System.out.println("editing");
        newRecordId.setValue(newRecordMap);

    }

    public static void sendPolje(Polje polje)
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://testapp-dc63d-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = firebaseDatabase.getReference("polje");
        DatabaseReference newRecordId = myRef.child(String.valueOf(polje.getId()));
        HashMap<String, Object> newRecordMap = new HashMap<>();
        newRecordMap.put("arkodId", polje.getArkodId());
        newRecordMap.put("naziv", polje.getNaziv());
        newRecordMap.put("korisnikEmail", polje.getKorisnikEmail());
        newRecordMap.put("povrsina", polje.getPovrsina());
        newRecordMap.put("biljkaId", polje.getBiljkaId());
        newRecordId.setValue(newRecordMap);






    }




}
