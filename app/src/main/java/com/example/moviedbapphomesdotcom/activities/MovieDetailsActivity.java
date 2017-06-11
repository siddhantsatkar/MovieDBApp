package com.example.moviedbapphomesdotcom.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviedbapphomesdotcom.data.DatabaseDataManager;
import com.example.moviedbapphomesdotcom.models.MovieDetails;
import com.example.moviedbapphomesdotcom.sync.MovieDetailsAsync;
import com.example.moviedbapphomesdotcom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsAsync.IMovieDetail {
    String topuserKey = "";
    String topmovieID = "";
    String searchuserKey = "";
    String searchmovieID = "";
    String nowuserKey = "";
    String nowmovieID = "";
    String saveduserKey = "";
    String savedmovieID = "";
    DatabaseDataManager dm;
    ArrayList<MovieDetails> movdet = null;
    MovieDetails movdb;
    public boolean flag = true;
    public final static String FLAgKEY = "Flagkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        if (isConnnectedOnline()) {
        } else {
            Toast.makeText(MovieDetailsActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MovieDetailsActivity.this, NetworkLossActivity.class);
            intent.putExtra(FLAgKEY, flag);
            startActivity(intent);
        }
        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");
        if (activity.equals("top")) {
            topuserKey = (String) getIntent().getExtras().get(TopMoviesActivity.USER_KEY);
            topmovieID = (String) getIntent().getExtras().get(TopMoviesActivity.MOVIE_ID_KEY);
            String url = "https://api.themoviedb.org/3/movie/" + topmovieID + "?api_key=" + topuserKey + "&language=en-US";
            new MovieDetailsAsync(this).execute(url);
        } else if (activity.equals("now")) {
            nowuserKey = (String) getIntent().getExtras().get(NowPlayingActivity.USER_KEY);
            nowmovieID = (String) getIntent().getExtras().get(NowPlayingActivity.NOW_ID_KEY);
            String url = "https://api.themoviedb.org/3/movie/" + nowmovieID + "?api_key=" + nowuserKey + "&language=en-US";
            new MovieDetailsAsync(this).execute(url);
        } else if (activity.equals("saved")) {
            saveduserKey = (String) getIntent().getExtras().get(SavedMoviesActivity.USER_KEY);
            savedmovieID = (String) getIntent().getExtras().get(SavedMoviesActivity.MOVIE_ID_KEY);
            String url = "https://api.themoviedb.org/3/movie/" + savedmovieID + "?api_key=" + saveduserKey + "&language=en-US";
            new MovieDetailsAsync(this).execute(url);
        } else {
            searchuserKey = (String) getIntent().getExtras().get(SearchMovieActivity.USER_KEY);
            searchmovieID = (String) getIntent().getExtras().get(SearchMovieActivity.SEARCH_MOVIE_KEY);
            String url = "https://api.themoviedb.org/3/movie/" + searchmovieID + "?api_key=" + searchuserKey + "&language=en-US";
            new MovieDetailsAsync(this).execute(url);
        }
        dm = new DatabaseDataManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isConnnectedOnline()) {
        } else {
            Toast.makeText(MovieDetailsActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MovieDetailsActivity.this, NetworkLossActivity.class);
            intent.putExtra(FLAgKEY, flag);
            startActivity(intent);
        }
    }

    @Override
    public void setMovieDetailList(ArrayList<MovieDetails> result) {
        movdet = result;
        MovieDetails mm = movdet.get(0);
        TextView tvtitle = (TextView) findViewById(R.id.textView_title_movie_details);
        tvtitle.setText(mm.getTitle().toString());
        TextView tvdate = (TextView) findViewById(R.id.textView_rel_date_movie_details);
        tvdate.setText(mm.getDate().toString());
        TextView tvRate = (TextView) findViewById(R.id.textView_rating_movie_details);
        tvRate.setText(mm.getRating().toString() + "/10");
        TextView tvDesc = (TextView) findViewById(R.id.textView_description_movie_details);
        tvDesc.setText(mm.getDescr().toString());
        ImageView icon = (ImageView) findViewById(R.id.imageView_background_moviedetails);
        Picasso.with(this).load(mm.getBg_path()).into(icon);
        movdb = mm;
        Button saveMov = (Button) findViewById(R.id.button_save_movie_details);
        saveMov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnnectedOnline()) {
                    MovieDetails mmm = dm.getMovie(movdb.getId());
                    if (mmm == null) {
                        dm.saveMovie(new MovieDetails(movdb.getBg_path(), movdb.getId(), movdb.getDescr(), movdb.getDate(), movdb.getTitle(), movdb.getRating()));
                        Toast.makeText(MovieDetailsActivity.this, "Saving the movie in database", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MovieDetailsActivity.this, "This movie is already saved", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MovieDetailsActivity.this, "Not Connected to internet", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MovieDetailsActivity.this, NetworkLossActivity.class);
                    intent.putExtra(FLAgKEY, flag);
                    startActivity(intent);
                }

            }
        });

    }

    private boolean isConnnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
