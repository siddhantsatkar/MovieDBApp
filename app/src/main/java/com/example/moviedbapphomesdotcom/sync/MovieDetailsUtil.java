package com.example.moviedbapphomesdotcom.sync;

import com.example.moviedbapphomesdotcom.models.MovieDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sid on 09-06-2017.
 */

public class MovieDetailsUtil {
    static public class MoviesDetailsJsonParser{
        static ArrayList<MovieDetails> parseMovies(String in){
            ArrayList<MovieDetails> moviedetaillist = new ArrayList<MovieDetails>();
            try{
                JSONObject root = new JSONObject(in);
                MovieDetails movd = new MovieDetails();
                String temp = root.getString("backdrop_path");
                movd.setBg_path("https://image.tmdb.org/t/p/w500"+temp);
                movd.setId(root.getString("id"));
                movd.setDescr(root.getString("overview"));
                movd.setDate(root.getString("release_date"));
                movd.setTitle(root.getString("title"));
                movd.setRating(root.getString("vote_average"));
                moviedetaillist.add(movd);
            }catch(JSONException e) {
                moviedetaillist = null;
                e.printStackTrace();
            }
            return moviedetaillist;
        }
    }
}
