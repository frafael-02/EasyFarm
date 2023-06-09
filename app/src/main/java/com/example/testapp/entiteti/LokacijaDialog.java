package com.example.testapp.entiteti;

import static com.example.testapp.HomeFragment.string;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.testapp.HomeFragment;
import com.example.testapp.MainActivity2;
import com.example.testapp.R;
import com.example.testapp.api.OpenMeteoApiClient;
import com.example.testapp.api.PoljeAPI;

public class LokacijaDialog extends AppCompatDialogFragment {

    private LokacijaDialog.LokacijaDialogListener listener;
    private Button saveButton;
    private Button cancleButton;
    private Spinner spinner;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_lokacija, null);
        builder.setView(view);/*
                .setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Spremi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String email = editTextEmail.getText().toString();
                        String punoIme = editTextIme.getText().toString();
                        int mibpg = Integer.parseInt(editTextMibpg.getText().toString());
                        listener.editInformation(punoIme,mibpg );
                    }
                });*/

        saveButton=view.findViewById(R.id.btnSpremi);
        cancleButton=view.findViewById(R.id.btnOdustani);
        spinner=view.findViewById(R.id.spinnerPolje);
      /*  spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(poljeAdapter.getItem(position) != null)
                {
                    Polje polje =poljeAdapter.getItem(position);
                    HomeFragment.odabranoPolje=polje;
                    HomeFragment.mjestoTextView.setText(polje.getNaziv());
                    HomeFragment.odabranoPoljeInt=position;


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
                                        HomeFragment.temperatureTextView.setText(string);
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

*/

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                dismiss();
            }

        });
        cancleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                dismiss();
            }

        });




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (LokacijaDialog.LokacijaDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement AccountDialogListener");
        }
    }




    public interface LokacijaDialogListener {
        void editInformation(String punoIme, int mibpg);
    }

}

