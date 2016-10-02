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

    AsyncResponse delegate = null;
    @Override
    protected String doInBackground(String... params) {
        //variables to hold json response and data respectively
        Log.d("doinBackground","Got here!");


        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        try {
            //create a url object
            URL url = new URL("http://api.reimaginebanking.com/accounts?key=54765c95182641144b2d2606ac0f2b42");

            //open a connection
            httpURLConnection = (HttpURLConnection) url.openConnection();
            Log.d("doinBackground","opended connection");
            //allow the connection to output
            httpURLConnection.setDoOutput(true);

            //set the connection type
            httpURLConnection.setRequestMethod("GET");

            //set the headers
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","application/json");
            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(httpURLConnection.getInputStream());
                Log.v("CatalogClient", server_response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    public SendJsonDataToServer(AsyncResponse asyncResponse){
        delegate =asyncResponse;
    }
    @Override
    protected void onPostExecute(String s){
        Log.d("post",s);
        Log.d("post jr",JsonResponse);
        if (JsonResponse!=null){
            delegate.processResponse(JsonResponse);
        }
        else{
            delegate.processResponse("No JsonResponse");
        }
    }

}
