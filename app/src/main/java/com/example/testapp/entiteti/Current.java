package com.example.testapp.entiteti;

import java.time.LocalDateTime;

public class Current {
    private LocalDateTime time;
    private int weathercode;
    private double temperature2m;

    public Current(LocalDateTime time, int weathercode, double temperature2m) {
        this.time = time;
        this.weathercode = weathercode;
        this.temperature2m = temperature2m;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getWeathercode() {
        return weathercode;
    }

    public double getTemperature() {
        return temperature2m;
    }

}
