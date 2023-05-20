package com.example.testapp.api;

import com.example.testapp.entiteti.Current;
import com.example.testapp.entiteti.Daily;
import com.example.testapp.entiteti.Hourly;
import com.example.testapp.entiteti.WeatherData;
import com.example.testapp.entiteti.WeatherDataCallback;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

public class OpenMeteoApiClient {

    private static final String API_URL = "https://api.open-meteo.com/v1/forecast";
    private final OpenMeteoParser parser;

    public OpenMeteoApiClient() {
        this.parser = new OpenMeteoParser();
    }

    public void getWeatherData(double latitude, double longitude, WeatherDataCallback callback) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
        urlBuilder.addQueryParameter("latitude", String.valueOf(latitude));
        urlBuilder.addQueryParameter("longitude", String.valueOf(longitude));
        urlBuilder.addQueryParameter("hourly", "temperature_2m,rain,weathercode");
        urlBuilder.addQueryParameter("daily", "weathercode,temperature_2m_max");
        urlBuilder.addQueryParameter("current_weather", "true");
        urlBuilder.addQueryParameter("timezone", "auto");

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure(new Exception("Unexpected code " + response));
                } else {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.body().string());
                        List<Daily> dailyList = parser.parseDaily(jsonResponse);
                        List<Hourly> hourlyList = parser.parseHourly(jsonResponse);
                        Current current = parser.parseCurrent(jsonResponse);

                        callback.onSuccess(new WeatherData(dailyList, hourlyList, current));
                    } catch (JSONException e) {
                        callback.onFailure(e);
                    }
                }
            }
        });
    }
}
