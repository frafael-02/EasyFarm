package com.example.testapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.testapp.api.OpenMeteoApiClient;

import com.example.testapp.api.PoljeAPI;

import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.AccountDialog;
import com.example.testapp.entiteti.Current;

import com.example.testapp.entiteti.Evidencija;
import com.example.testapp.entiteti.Korisnik;
import com.example.testapp.entiteti.LokacijaDialog;
import com.example.testapp.entiteti.Pesticid;
import com.example.testapp.entiteti.Polje;
import com.example.testapp.entiteti.UtilityClass;
import com.example.testapp.entiteti.WeatherData;
import com.example.testapp.entiteti.WeatherDataCallback;
import com.example.testapp.shop.GeneralShopAdapter;
import com.example.testapp.shop.ParentAdapter;
import com.example.testapp.shop.ShopChildModelClass;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements DataLoadListener, PoljeAPIListener, EvidencijaLoadListener, PesticidLoad, LokacijaDialog.LokacijaDialogListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private static volatile String odgovorStatic;

    private OpenMeteoApiClient apiClient;
    public TextView temperatureTextView;
    public static String string;

    public long timer;

    RecyclerView recyclerView;
    List<ShopChildModelClass> childModelClassArrayList;
    GeneralShopAdapter generalShopAdapter;


    public TextView ukupnoPrskanja;
    public TextView danasPrskanje;
    public   TextView mjesecPrskanje;


    public int odabranoPoljeInt;




    public Button mjestoTextView;

    public static Polje odabranoPolje;

    private int br;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseQueries.registerDataLoadedListener(this);
        PoljeAPI.registerDataLoadedListener(this);
        DatabaseQueries.registerEvidencijaLoadedListener(this);
        DatabaseQueries.registerDataLoadedPesticidListener(this);
        LokacijaDialog.setListener(this);

       // getActivity().setTitle("Home");





        if(MainActivity2.poljeList == null)
            MainActivity2.poljeList =DatabaseQueries.getPolja();

        br=0;



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        timer = 500L;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();




        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        temperatureTextView = view.findViewById(R.id.tempratureTextView);
        danasPrskanje = view.findViewById(R.id.danasIspod);
        mjesecPrskanje = view.findViewById(R.id.ovajMjesecIspod);
        ukupnoPrskanja = view.findViewById(R.id.ukupnoIspod);
        mjestoTextView=view.findViewById(R.id.mjestoTextView);




        recyclerView=view.findViewById(R.id.rv_home);
        childModelClassArrayList=new ArrayList<>();
        childModelClassArrayList= UtilityClass.pesticidToShopList(MainActivity2.pesticidList);
        generalShopAdapter=new GeneralShopAdapter(childModelClassArrayList,view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(generalShopAdapter);
        generalShopAdapter.notifyDataSetChanged();;


        if(MainActivity2.evidencijaList != null)
        {
            danasPrskanje.setText(String.valueOf(UtilityClass.getPrskanjeDanas(MainActivity2.evidencijaList)) + "ha");
            mjesecPrskanje.setText(String.valueOf(UtilityClass.getPrskanjeMjesec(MainActivity2.evidencijaList)) + "ha");
            ukupnoPrskanja.setText(String.valueOf(UtilityClass.getPrskanjeUkupno(MainActivity2.evidencijaList)) + "ha");
        }



        RelativeLayout constraintLayout=view.findViewById(R.id.relativeLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        if(string != null)
        {
            temperatureTextView.setText(string);
            mjestoTextView.setText(odabranoPolje.getNaziv());
        }
        if(odabranoPolje != null)
        {
            mjestoTextView.setText(odabranoPolje.getNaziv());
        }



        /*new Handler().postDelayed(new Runnable() {

            public void run () {
                if(string != null)
                    temperatureTextView.setText( string);
                else
                    timer=timer+100L;


            }
        }, timer);*/

        AppCompatImageButton profilBtn = view.findViewById(R.id.profilBtn);
        profilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile(MainActivity2.korisnik);
            }
        });

        mjestoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLokacija();
            }
        });

        /*pitajBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                odgovorStatic=null;

                timer=500L;

                //pitaj(pitanje.getText().toString());
                new Handler().postDelayed(new Runnable() {
                    public void run () {

                        while(odgovorStatic == null)
                        {}
                        if(odgovorStatic!=null)
                            odgovor.setText(odgovorStatic);



                    }
                }, timer);

            }
        });*/
        return view;
    }

    public void openProfile(Korisnik korisnik)
    {
        AccountDialog accountDialog = new AccountDialog();
        accountDialog.show(getParentFragmentManager(), "Vaš korisnički profil");
    }

    public void changeLokacija()
    {
        LokacijaDialog lokacijaDialog = new LokacijaDialog();
        lokacijaDialog.show(getParentFragmentManager(), "Odaberite polje za vremensku analizu");
    }

  /*  public void pitaj(String pitanje)
    {
        List<String> result = new ArrayList<>();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.INVISIBLE);
        shimmerFrameLayout.startShimmer();
        Handler handler = new Handler();
        handler.postDelayed(()->{

            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.VISIBLE);

        },3000);
        new Thread(new Runnable() {
            @Override
            public void run() {

                odgovorStatic = (VirtualAgent.chatGPT(pitanje));

            }
        }).start();



    }*/

    @Override
    public void onDataLoaded2(List<Polje> poljeList)
    {




        Polje polje = poljeList.get(0);


        br++;

        if(br == 1)
        { odabranoPolje=polje;
            mjestoTextView.setText(polje.getNaziv());
            if(polje.getKoordinate() != null)
            {
                double latitude = polje.getKoordinate().getX();
                double longitude = polje.getKoordinate().getY();


                MainActivity2.koordinate = polje.getKoordinate();
                apiClient = new OpenMeteoApiClient();

                apiClient.getWeatherData(latitude, longitude, new WeatherDataCallback() {
                    @Override
                    public void onSuccess(WeatherData weatherData) {

                        //  List<Daily> dailyList = weatherData.getDailyList();
                        //List<Hourly> hourlyList = weatherData.getHourlyList();
                        Current current = weatherData.getCurrent();


                        string = current.getTemperature() + "°C";
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                temperatureTextView.setText(string);
                            }
                        });


                    }

                    @Override
                    public void onFailure(Exception e) {
                        // Handle the error here
                        e.printStackTrace();
                    }
                });
            }
            else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        MainActivity2.koordinate = PoljeAPI.poljeAPI(polje);

                    }
                }).start();

            }

        }



    }


    @Override
    public void onDataLoaded(Double x, Double y) {

        temperatureTextView.setText("učitavanje");


        apiClient = new OpenMeteoApiClient();

        apiClient.getWeatherData(x, y, new WeatherDataCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {

                //  List<Daily> dailyList = weatherData.getDailyList();
                //List<Hourly> hourlyList = weatherData.getHourlyList();
                Current current = weatherData.getCurrent();


                string = current.getTemperature() + "°C";

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        temperatureTextView.setText(string);
                    }
                });


            }

            @Override
            public void onFailure(Exception e) {
                // Handle the error here
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDataLoaded(List<Evidencija> evidencijaList) {
        danasPrskanje.setText(String.valueOf(UtilityClass.getPrskanjeDanas(evidencijaList)) + "ha");
        mjesecPrskanje.setText(String.valueOf(UtilityClass.getPrskanjeMjesec(evidencijaList)) + "ha");
        ukupnoPrskanja.setText(String.valueOf(UtilityClass.getPrskanjeUkupno(evidencijaList)) + "ha");

    }



    @Override
    public void onDataLoad2(List<Pesticid> pesticidList) {

        childModelClassArrayList= UtilityClass.pesticidToShopList(pesticidList);
        generalShopAdapter.setChildModelClassList(childModelClassArrayList);
        generalShopAdapter.notifyDataSetChanged();;

    }

    @Override
    public void setLokacija(Polje polje) {


        mjestoTextView.setText(polje.getNaziv());
        odabranoPolje = polje;
        if(polje.getKoordinate() != null)
        {
            double latitude = polje.getKoordinate().getX();
            double longitude = polje.getKoordinate().getY();


            MainActivity2.koordinate = polje.getKoordinate();
            apiClient = new OpenMeteoApiClient();

            apiClient.getWeatherData(latitude, longitude, new WeatherDataCallback() {
                @Override
                public void onSuccess(WeatherData weatherData) {

                    //  List<Daily> dailyList = weatherData.getDailyList();
                    //List<Hourly> hourlyList = weatherData.getHourlyList();
                    Current current = weatherData.getCurrent();


                    string = current.getTemperature() + "°C";
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            temperatureTextView.setText(string);
                        }
                    });


                }

                @Override
                public void onFailure(Exception e) {
                    // Handle the error here
                    e.printStackTrace();
                }
            });
        }
        else{
            new Thread(new Runnable() {
                @Override
                public void run() {

                    MainActivity2.koordinate = PoljeAPI.poljeAPI(polje);

                }
            }).start();

        }

    }
}