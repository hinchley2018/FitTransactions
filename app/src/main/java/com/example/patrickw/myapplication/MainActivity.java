package com.example.patrickw.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends Activity {


    Button get_button;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content to layout
        setContentView(R.layout.activity_main);

        //get refs to elements
        get_button = (Button) findViewById(R.id.Get_Button);
        tv = (TextView) findViewById(R.id.Output_View);

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
          
      }
}
