package com.example.testapp.api;

import android.app.Activity;


import com.example.testapp.MainActivity2;

import java.io.File;
import java.io.IOException;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImagePostRequest {
    public static String sendImagePostRequest(Activity activity, String imageUri) {



        new Thread(new Runnable() {
            @Override
            public void run() {


                String baseUrl = "  https://plant-disease-classification-ejsiggjesq-ez.a.run.app";
                String endpoint = "/predict";
                String model = "res-model";

                String url = baseUrl + endpoint + "?model=" + model;



                OkHttpClient client = new OkHttpClient();



                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", "file.jpg",
                                RequestBody.create(new File(imageUri), MediaType.parse("image")))
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        MainActivity2.responseString = response.body().string();



                    } else {
                        MainActivity2.responseString="Server nije dostupan";

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        }).start();


        return MainActivity2.responseString;

    }
}
