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
import com.example.moviedbapphomesdotcom.adapter.SearchMovieAdapter;
import com.example.moviedbapphomesdotcom.adapter.TopMovieAdapter;
import com.example.moviedbapphomesdotcom.models.SearchMovies;
import com.example.moviedbapphomesdotcom.models.TopMovies;
import com.example.moviedbapphomesdotcom.sync.SearchMoviesAsync;
import com.example.moviedbapphomesdotcom.sync.TopMoviesAsync;

import java.util.ArrayList;

public class SearchMovieActivity extends AppCompatActivity implements SearchMoviesAsync.ISearchMovie {
    public final static String USER_KEY = "User";
    public final static String SEARCH_MOVIE_KEY = "SearchMovieKey";
    ProgressDialog progress;
    String userKey = "";
    String movname = "";
    SearchMovies selectedSearch = null;
    ArrayList<SearchMovies> searchMovies = null;

    public boolean flag = true;
    public final static String FLAgKEY = "Flagkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        if(getIntent().getExtras()!= null) {
            userKey = (String) getIntent().getExtras().get(MainActivity.USER_KEY);
            movname = (String) getIntent().getExtras().get(MainActivity.SEARCHMOVIEKEY);
            String url = "https://api.themoviedb.org/3/search/movie?api_key="+userKey+"&language=en-US&query="+movname+"&page=1&include_adult=false";
            new SearchMoviesAsync(this).execute(url);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isConnnectedOnline()){
        }else{
            Toast.makeText(SearchMovieActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SearchMovieActivity.this, NetworkLossActivity.class);
            intent.putExtra(FLAgKEY, flag);
            startActivity(intent);
        }
    }

    @Override
    public void setSearchMovieList(ArrayList<SearchMovies> result) {
        searchMovies = result;
        RecyclerView rvSearchMovie = (RecyclerView) findViewById(R.id.rv_search_movies);
        SearchMovieAdapter adapter = new SearchMovieAdapter(this, searchMovies);
        // Attach the adapter to the recyclerview to populate items
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // Attach layout manager to the RecyclerView
        rvSearchMovie.setLayoutManager(layoutManager);
        rvSearchMovie.setAdapter(adapter);
        // rvDaily.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration2 = new
                DividerItemDecoration(SearchMovieActivity.this, DividerItemDecoration.VERTICAL);
        rvSearchMovie.addItemDecoration(itemDecoration2);
        final ArrayList<SearchMovies> finalAll_search = searchMovies;
        adapter.setOnItemClickListener(new SearchMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(isConnnectedOnline()){
                    selectedSearch = finalAll_search.get(position);
                    String selectedMovieId = selectedSearch.getId();
                    Intent intent = new Intent(SearchMovieActivity.this, MovieDetailsActivity.class);
                    intent.putExtra(USER_KEY, userKey);
                    intent.putExtra("activity","search");
                    intent.putExtra(SEARCH_MOVIE_KEY, selectedMovieId);
                    startActivity(intent);
                }else{
                    Toast.makeText(SearchMovieActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SearchMovieActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });

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

