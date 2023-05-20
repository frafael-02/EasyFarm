package com.example.testapp.api;

import com.example.testapp.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VirtualAgent {

    public static String chatGPT(String message) {



        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = BuildConfig.ApiKey;
        String model = "gpt-3.5-turbo";

        Gson gson = new Gson();
        JsonObject messageJson = new JsonObject();
        messageJson.addProperty("role", "user");
        messageJson.addProperty("content", message);

        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("model", model);
        requestJson.add("messages", gson.toJsonTree(new JsonObject[]{messageJson}));

        RequestBody body = RequestBody.create(gson.toJson(requestJson), MediaType.parse("application/json"));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();
       /*

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}", mediaType);
        */

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JSONObject responseJson = new JSONObject(responseBody);
            JSONArray choicesArray = responseJson.getJSONArray("choices");
            JSONObject messageObject = choicesArray.getJSONObject(0).getJSONObject("message");
            String messageContent = messageObject.getString("content");

            response.close();

            return messageContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            return "Agent trenutno nije dostupan, pokušajte kasnije.";
        }

    }
}
