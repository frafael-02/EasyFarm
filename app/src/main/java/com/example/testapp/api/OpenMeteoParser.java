package com.example.testapp.api;

import com.example.testapp.entiteti.Current;
import com.example.testapp.entiteti.Daily;
import com.example.testapp.entiteti.Hourly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OpenMeteoParser {
    public List<Daily> parseDaily(JSONObject json) throws JSONException {
        List<Daily> dailyList = new ArrayList<>();

        json=json.getJSONObject("daily");
        JSONArray timeArray = json.getJSONArray("time");
        JSONArray weathercodeArray = json.getJSONArray("weathercode");
        JSONArray temperature2mMaxArray = json.getJSONArray("temperature_2m_max");

        for (int i = 0; i < timeArray.length(); i++) {

            String dateString = timeArray.getString(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateString, formatter);
            int weathercode = weathercodeArray.getInt(i);
            double temperature2mMax = temperature2mMaxArray.getDouble(i);
            Daily daily = new Daily(date, weathercode, temperature2mMax);
            dailyList.add(daily);

        }

        return dailyList;
    }

    public List<Hourly> parseHourly(JSONObject json) throws JSONException {
        List<Hourly> hourlyList = new ArrayList<>();

        json = json.getJSONObject("hourly");
        JSONArray timeArray = json.getJSONArray("time");
        JSONArray weathercodeArray = json.getJSONArray("weathercode");
        JSONArray temperature2mMaxArray = json.getJSONArray("temperature_2m");
        JSONArray rainArray = json.getJSONArray("rain");

        for (int i = 0; i < timeArray.length(); i++) {

            String timeString = timeArray.getString(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime time = LocalDateTime.parse(timeString, formatter);
            int weathercode = weathercodeArray.getInt(i);
            double temperature2mMax = temperature2mMaxArray.getDouble(i);
            double rain = rainArray.getInt(i);
            Hourly hourly = new Hourly(time,temperature2mMax, rain, weathercode);
            hourlyList.add(hourly);

        }

        return hourlyList;
    }

    public Current parseCurrent(JSONObject json) throws JSONException {
        JSONObject currentJson = json.getJSONObject("current_weather");
        String timeString = currentJson.get("time").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        LocalDateTime time = LocalDateTime.parse(timeString, formatter);

        Current current = new Current(
                time, currentJson.getInt("weathercode"),
                currentJson.getInt("temperature")

        );

        return current;
    }
}

