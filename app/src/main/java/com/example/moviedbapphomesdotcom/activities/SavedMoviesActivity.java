package com.example.moviedbapphomesdotcom.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviedbapphomesdotcom.R;
import com.example.moviedbapphomesdotcom.adapter.SavedMovieAdapter;
import com.example.moviedbapphomesdotcom.adapter.TopMovieAdapter;
import com.example.moviedbapphomesdotcom.data.DatabaseDataManager;
import com.example.moviedbapphomesdotcom.models.MovieDetails;

import java.util.ArrayList;

public class SavedMoviesActivity extends AppCompatActivity {
    ArrayList<MovieDetails> savedMov = new ArrayList<MovieDetails>();
    DatabaseDataManager dm;
    public final static String USER_KEY = "User";
    public final static String MOVIE_ID_KEY = "Movkey";
    public final static String key = "67bddb3787d6f7e413d2ae467b6c7e83";//Enter you TMDB API key
    public boolean flag = true;
    public final static String FLAgKEY = "Flagkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies);
        dm  = new DatabaseDataManager(this);
        savedMov =(ArrayList<MovieDetails>) dm.getAllMovies();
        if(savedMov.size() != 0) {
            RecyclerView rvSavedMov = (RecyclerView) findViewById(R.id.rv_saved_movies);
            final SavedMovieAdapter adapter = new SavedMovieAdapter(this, savedMov);
            // Attach the adapter to the recyclerview to populate items
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            // Attach layout manager to the RecyclerView
            rvSavedMov.setLayoutManager(layoutManager);
            rvSavedMov.setAdapter(adapter);
            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            rvSavedMov.addItemDecoration(itemDecoration);
            adapter.setOnItemClickListener(new SavedMovieAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(isConnnectedOnline()){
                        MovieDetails movclicked = savedMov.get(position);
                        String selectedMovieId = movclicked.getId();
                        Intent intent = new Intent(SavedMoviesActivity.this, MovieDetailsActivity.class);
                        intent.putExtra(USER_KEY, key);
                        intent.putExtra("activity","saved");
                        intent.putExtra(MOVIE_ID_KEY, selectedMovieId);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SavedMoviesActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SavedMoviesActivity.this, NetworkLossActivity.class);
                        intent.putExtra(FLAgKEY, flag);
                        startActivity(intent);
                    }

                }
            });
            adapter.setOnLongClickListener(new SavedMovieAdapter.OnLongClickListener(){

                @Override
                public void onItemLongClick(View itemView, int position) {
                    if(isConnnectedOnline()){
                        MovieDetails movclicked = savedMov.get(position);
                        dm.deleteMovie(movclicked);
                        savedMov.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(SavedMoviesActivity.this, "Deleted movie from the database", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SavedMoviesActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SavedMoviesActivity.this, NetworkLossActivity.class);
                        intent.putExtra(FLAgKEY, flag);
                        startActivity(intent);
                    }


                }
            });

        }
        else
        {   TextView tv = new TextView(this);
            tv.setText("No movie in database");
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            tv.setGravity(Gravity.CENTER);
            RelativeLayout rel = (RelativeLayout) findViewById(R.id.saved_mov_layout);
            rel.addView(tv,params);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(isConnnectedOnline()){
        }else{
            Toast.makeText(SavedMoviesActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SavedMoviesActivity.this, NetworkLossActivity.class);
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
