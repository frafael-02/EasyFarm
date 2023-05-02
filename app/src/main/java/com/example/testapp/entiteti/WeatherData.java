package com.example.testapp.entiteti;

import java.util.List;

public class WeatherData {
    private List<Daily> dailyList;
    private List<Hourly> hourlyList;
    private Current current;

    public WeatherData(List<Daily> dailyList, List<Hourly> hourlyList, Current current) {
        this.dailyList = dailyList;
        this.hourlyList = hourlyList;
        this.current = current;
    }

    public List<Daily> getDailyList() {
        return dailyList;
    }

    public List<Hourly> getHourlyList() {
        return hourlyList;
    }

    public Current getCurrent() {
        return current;
    }
}

