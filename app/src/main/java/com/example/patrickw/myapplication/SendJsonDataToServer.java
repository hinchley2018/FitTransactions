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
 public class SendJsonDataToServer extends AsyncTask<String , Void ,String> {
     String server_response;

     @Override
     protected String doInBackground(String... strings) {

         URL url;
         HttpURLConnection urlConnection = null;

         try {
             url = new URL(strings[0]);
             urlConnection = (HttpURLConnection) url.openConnection();

             int responseCode = urlConnection.getResponseCode();

             if(responseCode == HttpURLConnection.HTTP_OK){
                 server_response = readStream(urlConnection.getInputStream());
                 Log.v("CatalogClient", server_response);
             }

         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

         return null;
     }

     @Override
     protected void onPostExecute(String s) {
         super.onPostExecute(s);

         Log.e("Response", "" + server_response);


     }


 // Converting InputStream to String

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
