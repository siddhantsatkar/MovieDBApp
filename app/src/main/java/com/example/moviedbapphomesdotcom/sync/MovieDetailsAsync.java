package com.example.moviedbapphomesdotcom.sync;

import android.os.AsyncTask;

import com.example.moviedbapphomesdotcom.models.MovieDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by sid on 09-06-2017.
 */

public class MovieDetailsAsync extends AsyncTask<String, Void, ArrayList<MovieDetails>> {
    MovieDetailsAsync.IMovieDetail activity;
    StringBuilder sb;
    public MovieDetailsAsync(MovieDetailsAsync.IMovieDetail activity){
        this.activity = activity;
    }

    @Override
    protected ArrayList<MovieDetails> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                int statusCode = conn.getResponseCode();
                if(statusCode==HttpURLConnection.HTTP_OK)
                {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    String line = br.readLine();
                    while(line != null)
                    {
                        sb.append(line);
                        line = br.readLine();
                    }
                    return MovieDetailsUtil.MoviesDetailsJsonParser.parseMovies(sb.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDetails> moviesDetailArray) {
        if(moviesDetailArray != null){
            activity.setMovieDetailList(moviesDetailArray);
        }
    }

    public static interface IMovieDetail{
        public void setMovieDetailList(ArrayList<MovieDetails> result);
    }
}

