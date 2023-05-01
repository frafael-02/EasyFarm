package com.example.testapp.entiteti;

public interface WeatherDataCallback {

    void onSuccess(WeatherData weatherData);

    void onFailure(Exception e);
}
