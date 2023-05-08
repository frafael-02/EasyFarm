package com.example.testapp.api;

import android.app.Activity;
import android.net.Uri;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImagePostRequest {
    public static String sendImagePostRequest(Activity activity, Uri imageUri) {
        String baseUrl = "http://example.com";
        String endpoint = "/predict";
        String model = "res-model";

        String url = baseUrl + endpoint + "?model=" + model;


        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageUri.getLastPathSegment(),
                        RequestBody.create(new File(imageUri.getPath()), MediaType.parse(activity.getContentResolver().getType(imageUri))))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseString = response.body().string();
               return responseString;

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
