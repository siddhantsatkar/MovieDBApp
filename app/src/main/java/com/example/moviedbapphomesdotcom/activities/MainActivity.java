package com.example.moviedbapphomesdotcom.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviedbapphomesdotcom.R;

public class MainActivity extends AppCompatActivity {
    public final static String USER_KEY = "User";
    public final static String key = "67bddb3787d6f7e413d2ae467b6c7e83";//Enter your TMDB API key
    public final static String SEARCHMOVIEKEY = "SearchMovieKey";
    public boolean flag = true;
    public final static String FLAgKEY = "Flagkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isConnnectedOnline()){
        }else{
            Toast.makeText(MainActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, NetworkLossActivity.class);
            intent.putExtra(FLAgKEY, flag);
            startActivity(intent);
        }
        Button button_top_movies = (Button) findViewById(R.id.button_main_top_movies);
        Button button_now_playing = (Button) findViewById(R.id.button_main_now_playing);
        button_top_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnnectedOnline()){
                    Intent intent = new Intent(MainActivity.this, TopMoviesActivity.class);
                    flag = true;
                    intent.putExtra(USER_KEY, key);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });
        button_now_playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnnectedOnline()){
                    Intent intent = new Intent(MainActivity.this, NowPlayingActivity.class);
                    flag = false;
                    intent.putExtra(USER_KEY, key);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });

        final EditText search = (EditText) findViewById(R.id.editText_main_search_movie);
        Button searchButton = (Button) findViewById(R.id.button_main_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnnectedOnline()){
                    String temp = search.getText().toString();
                    String movie = temp.replace(" ","%20");
                    Intent intent = new Intent(MainActivity.this, SearchMovieActivity.class);
                    intent.putExtra(USER_KEY, key);
                    intent.putExtra(SEARCHMOVIEKEY, movie);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });
        Button savedMovs = (Button) findViewById(R.id.button_saved_movies);
        savedMovs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnnectedOnline()){
                    Intent intent = new Intent(MainActivity.this, SavedMoviesActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isConnnectedOnline()){
        }else{
            Toast.makeText(MainActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, NetworkLossActivity.class);
            intent.putExtra(FLAgKEY, flag);
            startActivity(intent);
        }
    }

    private boolean isConnnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }
}
