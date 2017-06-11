package com.example.moviedbapphomesdotcom.sync;

import com.example.moviedbapphomesdotcom.models.NowPlaying;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sid on 09-06-2017.
 */

public class NowPlayingUtil {
    static public class NowPlayingJsonParser{
        static ArrayList<NowPlaying> parseNow(String in){
            ArrayList<NowPlaying> nowplayinglist = new ArrayList<NowPlaying>();
            try{
                JSONObject root = new JSONObject(in);
                JSONArray nowplayingjsonarray = root.getJSONArray("results");
                for(int i=0; i< nowplayingjsonarray.length(); i++){
                    JSONObject nowplayingjsonobject = (JSONObject) nowplayingjsonarray.get(i);
                    NowPlaying nowPlaying = NowPlaying.createNowPlaying(nowplayingjsonobject);
                    nowplayinglist.add(nowPlaying);
                }
            }catch (JSONException e){
                nowplayinglist = null;
                e.printStackTrace();
            }
            return nowplayinglist;
        }
    }
}
