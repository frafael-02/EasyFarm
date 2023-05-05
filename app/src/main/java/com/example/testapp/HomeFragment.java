package com.example.testapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.api.OpenMeteoApiClient;
import com.example.testapp.database.DatabaseQueries;
import com.example.testapp.entiteti.Current;
import com.example.testapp.entiteti.Daily;
import com.example.testapp.entiteti.Hourly;
import com.example.testapp.entiteti.WeatherData;
import com.example.testapp.entiteti.WeatherDataCallback;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OpenMeteoApiClient apiClient;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);


        apiClient = new OpenMeteoApiClient();
        double latitude = 46.32;
        double longitude = 16.80;

        apiClient.getWeatherData(latitude, longitude, new WeatherDataCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {
                // Use the weather data here
              //  List<Daily> dailyList = weatherData.getDailyList();
                //List<Hourly> hourlyList = weatherData.getHourlyList();
                Current current = weatherData.getCurrent();

                // Example: set the current temperature on a TextView
                TextView temperatureTextView = view.findViewById(R.id.tempratureTextView);

                setText(temperatureTextView, "Trenutna temperatura: " + String.valueOf(current.getTemperature()));



            }

            @Override
            public void onFailure(Exception e) {
                // Handle the error here
                e.printStackTrace();
            }
        });



        return view;
    }

    private void setText(final TextView text,final String value){
        if(getActivity() != null)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    text.setText(value);
                }
            });
        }
        else {
            Toast.makeText(getContext(), "Informacije o temperaturi trenutno nisu dostupne, ponovo otvorite Home page", Toast.LENGTH_SHORT).show();
        }

    }
}