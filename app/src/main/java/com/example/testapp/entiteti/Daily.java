package com.example.testapp.entiteti;

import java.time.LocalDate;

public class Daily {
    private LocalDate date;
    private int weathercode;
    private double temperature2mMax;

    public Daily(LocalDate date, int weathercode, double temperature2mMax) {
        this.date = date;
        this.weathercode = weathercode;
        this.temperature2mMax = temperature2mMax;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getWeathercode() {
        return weathercode;
    }

    public double getTemperature2mMax() {
        return temperature2mMax;
    }

    }

