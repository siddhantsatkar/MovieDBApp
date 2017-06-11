package com.example.moviedbapphomesdotcom.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moviedbapphomesdotcom.R;
import com.example.moviedbapphomesdotcom.adapter.TopMovieAdapter;
import com.example.moviedbapphomesdotcom.models.TopMovies;
import com.example.moviedbapphomesdotcom.sync.TopMoviesAsync;

import java.util.ArrayList;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesAsync.ITopMovie {
    public static final String TOPPREFERENCES = "TopPrefs" ;
    public static final String TOPID1 = "TopId1" ;
    public static final String TOPID2 = "TopId2" ;
    public static final String TOPID3 = "TopId3" ;
    public static final String TOPID4 = "TopId4" ;
    public final static String USER_KEY = "User";
    public final static String MOVIE_ID_KEY = "MovieID";
    public boolean flag = true;
    public final static String FLAgKEY = "Flagkey";
    ArrayList<TopMovies> topMovies = null;
    String userKey = "";
    TopMovies selectedMovie = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);
        if(getIntent().getExtras()!= null) {
            userKey = (String) getIntent().getExtras().get(MainActivity.USER_KEY);
            String url = "https://api.themoviedb.org/3/movie/top_rated?api_key="+userKey+"&language=en-US&page=1";
            new TopMoviesAsync(this).execute(url);
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(isConnnectedOnline()){
        }else{
            Toast.makeText(TopMoviesActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(TopMoviesActivity.this, NetworkLossActivity.class);
            intent.putExtra(FLAgKEY, flag);
            startActivity(intent);
        }
    }

    @Override
    public void setTopMovieList(ArrayList<TopMovies> result) {
        topMovies = result;
        RecyclerView rvTopMovie = (RecyclerView) findViewById(R.id.rv_top_movies);
        TopMovieAdapter adapter = new TopMovieAdapter(this, topMovies);
        // Attach the adapter to the recyclerview to populate items
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // Attach layout manager to the RecyclerView
        rvTopMovie.setLayoutManager(layoutManager);
        rvTopMovie.setAdapter(adapter);
        // rvDaily.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration2 = new
                DividerItemDecoration(TopMoviesActivity.this, DividerItemDecoration.VERTICAL);
        rvTopMovie.addItemDecoration(itemDecoration2);
        final ArrayList<TopMovies> finalAll_movies = topMovies;
        adapter.setOnItemClickListener(new TopMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(isConnnectedOnline()){
                    selectedMovie = finalAll_movies.get(position);
                    String selectedMovieId = selectedMovie.getId();
                    Intent intent = new Intent(TopMoviesActivity.this, MovieDetailsActivity.class);
                    intent.putExtra(USER_KEY, userKey);
                    intent.putExtra("activity","top");
                    intent.putExtra(MOVIE_ID_KEY, selectedMovieId);
                    startActivity(intent);
                }else{
                    Toast.makeText(TopMoviesActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(TopMoviesActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });
        SharedPreferences sp = getSharedPreferences(TOPPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TOPID1, finalAll_movies.get(0).getTitle());
        editor.putString(TOPID2, finalAll_movies.get(1).getTitle());
        editor.putString(TOPID3, finalAll_movies.get(0).getDescr());
        editor.putString(TOPID4, finalAll_movies.get(1).getDescr());
        editor.commit();
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
