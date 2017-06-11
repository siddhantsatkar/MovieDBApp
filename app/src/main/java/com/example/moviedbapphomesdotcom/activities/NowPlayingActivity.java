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

import com.example.moviedbapphomesdotcom.models.NowPlaying;
import com.example.moviedbapphomesdotcom.adapter.NowPlayingAdapter;
import com.example.moviedbapphomesdotcom.sync.NowPlayingAsync;
import com.example.moviedbapphomesdotcom.R;

import java.util.ArrayList;

public class NowPlayingActivity extends AppCompatActivity implements NowPlayingAsync.INowPlaying {
    ArrayList<NowPlaying> nowPlaying = null;
    String userKey = "";
    NowPlaying selectedNowMovie = null;
    public final static String USER_KEY = "User";
    public final static String NOW_ID_KEY = "NowMovieID";
    public boolean flag = false;
    public final static String FLAgKEY = "Flagkey";
    public static final String NOWPREFERENCES = "NowPrefs" ;
    public static final String NOWID1 = "NowId1" ;
    public static final String NOWID2 = "NowId2" ;
    public static final String NOWID3 = "NowId3" ;
    public static final String NOWID4 = "NowId4" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        if(getIntent().getExtras()!= null) {
            userKey = (String) getIntent().getExtras().get(MainActivity.USER_KEY);
            String url = "https://api.themoviedb.org/3/movie/now_playing?api_key="+userKey+"&language=en-US&page=1";
            new NowPlayingAsync(this).execute(url);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(isConnnectedOnline()){
        }else{
            Toast.makeText(NowPlayingActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(NowPlayingActivity.this, NetworkLossActivity.class);
            intent.putExtra(FLAgKEY, flag);
            startActivity(intent);
        }
    }

    @Override
    public void setNowPlayingList(ArrayList<NowPlaying> result) {
        nowPlaying = result;
        RecyclerView rvNowPlaying = (RecyclerView) findViewById(R.id.rv_now_playing);
        NowPlayingAdapter adapter = new NowPlayingAdapter(this, nowPlaying);
        // Attach the adapter to the recyclerview to populate items
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // Attach layout manager to the RecyclerView
        rvNowPlaying.setLayoutManager(layoutManager);
        rvNowPlaying.setAdapter(adapter);
        // rvDaily.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration2 = new
                DividerItemDecoration(NowPlayingActivity.this, DividerItemDecoration.VERTICAL);
        rvNowPlaying.addItemDecoration(itemDecoration2);
        final ArrayList<NowPlaying> finalAll_nowplaying = nowPlaying;
        adapter.setOnItemClickListener(new NowPlayingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(isConnnectedOnline()){
                    selectedNowMovie = finalAll_nowplaying.get(position);
                    String selectedNowMovieId = selectedNowMovie.getId();
                    Intent intent = new Intent(NowPlayingActivity.this, MovieDetailsActivity.class);
                    intent.putExtra(USER_KEY, userKey);
                    intent.putExtra("activity", "now");
                    intent.putExtra(NOW_ID_KEY, selectedNowMovieId);
                    startActivity(intent);
                }else{
                    Toast.makeText(NowPlayingActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NowPlayingActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });
        SharedPreferences sp = getSharedPreferences(NOWPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(NOWID1, finalAll_nowplaying.get(0).getTitle());
        editor.putString(NOWID2, finalAll_nowplaying.get(1).getTitle());
        editor.putString(NOWID3, finalAll_nowplaying.get(0).getDescr());
        editor.putString(NOWID4, finalAll_nowplaying.get(1).getDescr());
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
