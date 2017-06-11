package com.example.moviedbapphomesdotcom.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sid on 09-06-2017.
 */

public class MovieDetails implements Parcelable{
    String bg_path, id, descr, date, title, rating;

    public MovieDetails(){}

    public MovieDetails(String bg_path, String id, String descr, String date, String title, String rating) {
        this.bg_path = bg_path;
        this.id = id;
        this.descr = descr;
        this.date = date;
        this.title = title;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "bg_path='" + bg_path + '\'' +
                ", id='" + id + '\'' +
                ", descr='" + descr + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

    protected MovieDetails(Parcel in) {
        this.bg_path = in.readString();
        this.id = in.readString();
        this.descr = in.readString();
        this.date = in.readString();
        this.title = in.readString();
        this.rating = in.readString();
    }

    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel in) {
            return new MovieDetails(in);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };

    public String getBg_path() {
        return bg_path;
    }

    public void setBg_path(String bg_path) {
        this.bg_path = bg_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bg_path);
        parcel.writeString(id);
        parcel.writeString(descr);
        parcel.writeString(date);
        parcel.writeString(title);
        parcel.writeString(rating);
    }
}
