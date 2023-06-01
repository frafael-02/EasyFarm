package com.example.testapp.api;

import androidx.annotation.NonNull;

import com.example.testapp.DataLoadListener;
import com.example.testapp.MainActivity2;
import com.example.testapp.PoljeAPIListener;
import com.example.testapp.entiteti.Koordinate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PoljeAPI {

    private static PoljeAPIListener dataLoadListener;

    public static void registerDataLoadedListener(PoljeAPIListener listener)
    {
        dataLoadListener = listener;
    }

    private static String API_URL = "http://192.168.241.1:8080/dohvatiKoordinate";

    public static Koordinate poljeAPI(String arkod)
    {


        Koordinate koordinate = new Koordinate(0.0, 0.0);
        okhttp3.OkHttpClient client = new OkHttpClient();
     //   okhttp3.HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
       // urlBuilder.addQueryParameter("?arkod", arkod);
        String urlString = "http://192.168.241.1:8080/dohvatiKoordinate?arkod=" + arkod.toString();
        System.out.println(urlString);
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JSONObject jsonObject = new JSONObject(response.body().string());
                System.out.println(jsonObject.toString());
               koordinate.setX(jsonObject.getDouble("x"));
               koordinate.setY(jsonObject.getDouble("y"));
                dataLoadListener.onDataLoaded(jsonObject.getDouble("x"), jsonObject.getDouble("y"));


            } else {


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            //throw new RuntimeException(e);
        }
    return koordinate;
    }


}
