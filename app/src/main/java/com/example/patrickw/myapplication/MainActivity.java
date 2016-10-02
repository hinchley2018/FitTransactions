package com.example.patrickw.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.ExecutionException;


public class MainActivity extends Activity implements AsyncResponse{


    Button get_button;
    TextView tv;
    String Display_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //set content to layout
        setContentView(R.layout.activity_main);

        //get refs to elements
        get_button = (Button) findViewById(R.id.Get_Button);
        get_button.setOnClickListener(onClickListener);
        tv = (TextView) findViewById(R.id.Output_View);
        tv.setText("helu");
        //Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();

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
              JSONObject jsonObject=new JSONObject(result);
              String purchase_date = jsonObject.getString("purchase_date");
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
