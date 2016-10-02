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


public class MainActivity extends Activity implements AsyncResponse{


    Button get_button;
    TextView tv;

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
            send_data_to_server("hello");
              tv.setText("Goodbye!");
          }
      }

      };
      public void send_data_to_server(String Recipe){

        //create a JSONObject so I can post data...
        JSONObject post_dict = new JSONObject();

        //try to put the recipe in the jsonObject
        try {
            post_dict.put("recipe",Recipe);
            //debug
            Log.i("My JSONObject",post_dict.toString());
            Toast.makeText(getApplication(),"My Json:"+post_dict.toString(),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (post_dict.length()>0){
            new SendJsonDataToServer(this).execute(String.valueOf(post_dict));
        }
      }
}
