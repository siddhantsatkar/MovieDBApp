package com.example.moviedbapphomesdotcom.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moviedbapphomesdotcom.R;

public class NetworkLossActivity extends AppCompatActivity {
    boolean flag;
    public static final String TOPPREFERENCES = "TopPrefs" ;
    String movname1 = "";
    String movname2 = "";
    String movdescr1 = "";
    String movdescr2 = "";
    public static final String TOPID1 = "TopId1" ;
    public static final String TOPID2 = "TopId2" ;
    public static final String TOPID3 = "TopId3" ;
    public static final String TOPID4 = "TopId4" ;
    public static final String NOWPREFERENCES = "NowPrefs" ;
    public static final String NOWID1 = "NowId1" ;
    public static final String NOWID2 = "NowId2" ;
    public static final String NOWID3 = "NowId3" ;
    public static final String NOWID4 = "NowId4" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_loss);
        TextView movnam1 = (TextView) findViewById(R.id.textView_movname1);
        TextView movnam2 = (TextView) findViewById(R.id.textView_movname2);
        TextView movdesc1 = (TextView) findViewById(R.id.textView_movdescr1);
        TextView movdesc2 = (TextView) findViewById(R.id.textView_movdescr2);
        if(getIntent().getExtras()!= null) {
            flag = (Boolean) getIntent().getExtras().get(MainActivity.FLAgKEY);
            if(flag) {
                SharedPreferences prefs = getSharedPreferences(TOPPREFERENCES, MODE_PRIVATE);
                movname1 = prefs.getString(TOPID1, "No name defined");//"No name defined" is the default value.
                movdescr1 = prefs.getString(TOPID3, "No descr defined");
                movname2 = prefs.getString(TOPID2, "No name defined");
                movdescr2 = prefs.getString(TOPID4, "No descr defined");
                movnam1.setText(movname1);
                movnam2.setText(movname2);
                movdesc1.setText(movdescr1);
                movdesc2.setText(movdescr2);
            }else{
                SharedPreferences prefs = getSharedPreferences(NOWPREFERENCES, MODE_PRIVATE);
                movname1 = prefs.getString(NOWID1, "No name defined");
                movdescr1 = prefs.getString(NOWID3, "No descr defined");
                movname2 = prefs.getString(NOWID2, "No name defined");
                movdescr2 = prefs.getString(NOWID4, "No descr defined");
                movnam1.setText(movname1);
                movnam2.setText(movname2);
                movdesc1.setText(movdescr1);
                movdesc2.setText(movdescr2);
            }
        }
    }
}
