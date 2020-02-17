package com.nikolaevtsev.gameandrateofcurrency.concrete.model_concrete.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.nikolaevtsev.gameandrateofcurrency.abstract_.presenter_abstract.JsonPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class JsonUtils {

    private static final String PATH_TO_JSON = "https://www.cbr-xml-daily.ru/daily_json.js";
    private static final String JSON_OBJECT_ATTRIBUTE_CURRENCY = "Valute";
    private static final String JSON_OBJECT_ATTRIBUTE_DOLLAR = "USD";
    private static final String JSON_ATTRIBUTE_RATE = "Value";
    private JsonPresenter jsonPresenter;


    public JsonUtils(JsonPresenter jsonPresenter) {
        this.jsonPresenter = jsonPresenter;
    }

    public void startDownloadDollarRate() {
        JSONObject jsonObject = getJsonFromString(getJsonAsString());
        String dollarRate = null;
        try {
            JSONObject currency = jsonObject.getJSONObject(JSON_OBJECT_ATTRIBUTE_CURRENCY);
            JSONObject dollar = currency.getJSONObject(JSON_OBJECT_ATTRIBUTE_DOLLAR);
            dollarRate = Double.toString(dollar.getDouble(JSON_ATTRIBUTE_RATE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonPresenter.loadFinish(dollarRate);
    }

    public JSONObject getJsonFromString(String jsonAsString) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonAsString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObject == null)
            Log.i("JSON", "null");
        else
            Log.i("JSON", jsonObject.toString());
        return jsonObject;
    }

    private String getJsonAsString() {
        String jsonAsString = null;
        try {
            jsonAsString = new DownloadJsonAsStringTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Log.i("JSON as String", jsonAsString);
        return jsonAsString;
    }

    private static class DownloadJsonAsStringTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder result = new StringBuilder();
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(PATH_TO_JSON);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader reader = new InputStreamReader
                        (urlConnection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return result.toString();
        }
    }
}

