package com.example.moviedbapphomesdotcom.sync;

import com.example.moviedbapphomesdotcom.models.SearchMovies;
import com.example.moviedbapphomesdotcom.models.TopMovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sid on 09-06-2017.
 */

public class SearchMoviesUtil {
    static public class SearchMoviesJsonParser{
        static ArrayList<SearchMovies> parseMovies(String in){
            ArrayList<SearchMovies> searchmovielist = new ArrayList<SearchMovies>();
            try{
                JSONObject root = new JSONObject(in);
                JSONArray searchmoviejsonarray = root.getJSONArray("results");
                for(int i=0; i< searchmoviejsonarray.length(); i++){
                    JSONObject searchmovjsonobject = (JSONObject) searchmoviejsonarray.get(i);
                    SearchMovies searchMovies = SearchMovies.createSearchMovie(searchmovjsonobject);
                    searchmovielist.add(searchMovies);
                }
            }catch (JSONException e){
                searchmovielist = null;
                e.printStackTrace();
            }
            return searchmovielist;
        }
    }
}

