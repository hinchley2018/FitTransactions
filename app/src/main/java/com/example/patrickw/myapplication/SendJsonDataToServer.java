package com.example.patrickw.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jhinchley on 5/5/16.
 */
public class SendJsonDataToServer extends AsyncTask<String,Void,Void> {

    String server_response;
    AsyncResponse delegate = null;

    @Override
    protected Void doInBackground(String... params) {
        //variables to hold json response and data respectively
        Log.d("doinBackground", "Got here!");


        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        try {
            //create a url object
            URL url = new URL("http://api.reimaginebanking.com/accounts?key=54765c95182641144b2d2606ac0f2b42");

            //open a connection
            httpURLConnection = (HttpURLConnection) url.openConnection();
            Log.d("doinBackground", "opended connection");
            //allow the connection to output
            httpURLConnection.setDoOutput(true);

            //set the connection type
            httpURLConnection.setRequestMethod("GET");

            //set the headers
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                server_response = readStream(httpURLConnection.getInputStream());
                Log.v("CatalogClient", server_response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
