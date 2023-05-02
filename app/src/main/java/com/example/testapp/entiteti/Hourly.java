package com.example.testapp.entiteti;

import java.time.LocalDateTime;

public class Hourly {

    private LocalDateTime time;
    private double temperature2m;
    private double rain;
    private int weathercode;

    public Hourly(LocalDateTime time, double temperature2m, double rain, int weathercode) {
        this.time = time;
        this.temperature2m = temperature2m;
        this.rain = rain;
        this.weathercode = weathercode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getTemperature2m() {
        return temperature2m;
    }

    public void setTemperature2m(double temperature2m) {
        this.temperature2m = temperature2m;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public int getWeathercode() {
        return weathercode;
    }

    public void setWeathercode(int weathercode) {
        this.weathercode = weathercode;
    }
}
