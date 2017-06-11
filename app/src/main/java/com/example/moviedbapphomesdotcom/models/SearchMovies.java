package com.example.moviedbapphomesdotcom.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sid on 09-06-2017.
 */

public class SearchMovies implements Parcelable {
    String id, title, imgpath, descr;

    public SearchMovies(){}

    public static final Creator<SearchMovies> CREATOR = new Creator<SearchMovies>() {
        @Override
        public SearchMovies createFromParcel(Parcel in) {
            return new SearchMovies(in);
        }

        @Override
        public SearchMovies[] newArray(int size) {
            return new SearchMovies[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    static public SearchMovies createSearchMovie(JSONObject js){
        SearchMovies mov = new SearchMovies();
        try{
            mov.setId(js.getString("id"));
            mov.setTitle(js.getString("title"));
            String imgp = js.getString("poster_path");
            mov.setImgpath("https://image.tmdb.org/t/p/w500"+imgp);
            mov.setDescr(js.getString("overview"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        return mov;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(imgpath);
        parcel.writeString(descr);
    }

    private SearchMovies(Parcel in){
        this.id = in.readString();
        this.title = in.readString();
        this.imgpath = in.readString();
        this.descr = in.readString();
    }
}

