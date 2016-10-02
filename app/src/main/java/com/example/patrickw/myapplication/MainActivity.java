package com.example.patrickw.myapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.util.Log;
import android.os.health.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.ExecutionException;


public class MainActivity extends Activity implements AsyncResponse{


    Button get_button;
    TextView tv;
    //TextView hbo;
    String Display_Text;
    TableLayout table1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //set content to layout
        setContentView(R.layout.activity_main);

        //get refs to elements
        get_button = (Button) findViewById(R.id.Get_Button);
        get_button.setOnClickListener(onClickListener);
        tv = (TextView) findViewById(R.id.Output_View);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();


    }
    private OnClickListener onClickListener = new OnClickListener() {
      @Override
      public void onClick(final View v) {
          if (v == get_button){
              String result = "";

              try {
                  result = send_data_to_server(result);
              } catch (ExecutionException e) {
                  e.printStackTrace();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              //Toast.makeText(MainActivity.this,result, Toast.LENGTH_LONG).show();
              tv.setText(result);

              try {
                  JSONArray jsonObject= null;
                  jsonObject = new JSONArray(result);
                  for (int i = 0; i < jsonObject.length(); i++)
                  {
                      JSONObject purchase = (JSONObject) jsonObject.get(i);
                      Toast.makeText(MainActivity.this,purchase.getString("description"),Toast.LENGTH_LONG).show();
                  }

                  //String purchase_date = jsonObject.getString("purchase_date");
                  //Toast.makeText(MainActivity.this,purchase_date, Toast.LENGTH_LONG).show();
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
      }

      };
      public String send_data_to_server(String re) throws ExecutionException, InterruptedException {
            re =new SendJsonDataToServer(this).execute().get();
            Log.d("Finally",re);
            return re;

      }

    @Override
    public void processResponse(String output) {

    }
}
