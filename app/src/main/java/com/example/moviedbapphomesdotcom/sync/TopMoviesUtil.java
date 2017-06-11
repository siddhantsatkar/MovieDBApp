package com.example.moviedbapphomesdotcom.sync;

import com.example.moviedbapphomesdotcom.models.TopMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sid on 09-06-2017.
 */

public class TopMoviesUtil {
    static public class TopMoviesJsonParser{
        static ArrayList<TopMovies> parseMovies(String in){
            ArrayList<TopMovies> topmovielist = new ArrayList<TopMovies>();
            try{
                    JSONObject root = new JSONObject(in);
                JSONArray topmoviejsonarray = root.getJSONArray("results");
                for(int i=0; i< topmoviejsonarray.length(); i++){
                    JSONObject topmovjsonobject = (JSONObject) topmoviejsonarray.get(i);
                    TopMovies topMovies = TopMovies.createTopMovie(topmovjsonobject);
                    topmovielist.add(topMovies);
                }
            }catch (JSONException e){
                topmovielist = null;
                e.printStackTrace();
            }
            return topmovielist;
        }
    }
}
