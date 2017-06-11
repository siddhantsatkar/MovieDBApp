package com.example.moviedbapphomesdotcom.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviedbapphomesdotcom.models.MovieDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sid on 10-06-2017.
 */

public class MoviesDAO {
    private SQLiteDatabase db;

    public MoviesDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(MovieDetails mov){
        ContentValues values = new ContentValues();
        values.put(MovieDetailsTable.COLUMN_bg_path, mov.getBg_path());
        values.put(MovieDetailsTable.COLUMN_ID, mov.getId());
        values.put(MovieDetailsTable.COLUMN_descr, mov.getDescr());
        values.put(MovieDetailsTable.COLUMN_date, mov.getDate());
        values.put(MovieDetailsTable.COLUMN_title, mov.getTitle());
        values.put(MovieDetailsTable.COLUMN_rating, mov.getRating());
        return db.insert(MovieDetailsTable.TABLENAME, null, values);
    }

    public boolean delete(MovieDetails mov){
        return db.delete(MovieDetailsTable.TABLENAME, MovieDetailsTable.COLUMN_ID+"=?", new String[]{mov.getId()}) > 0;
    }
    public MovieDetails get(String id){
        MovieDetails mvdet = null;

        Cursor c = db.query(true, MovieDetailsTable.TABLENAME, new String[]{MovieDetailsTable.COLUMN_bg_path, MovieDetailsTable.COLUMN_ID, MovieDetailsTable.COLUMN_descr, MovieDetailsTable.COLUMN_date, MovieDetailsTable.COLUMN_title, MovieDetailsTable.COLUMN_rating},
                MovieDetailsTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);
        if(c!= null && c.moveToFirst())
        {
            mvdet = buildMovieFromCursor(c);
            if(!c.isClosed())
                c.close();
        }
        return mvdet;
    }
    public List<MovieDetails> getAll(){
        List<MovieDetails> movies = new ArrayList<MovieDetails>();
        Cursor c = db.query(MovieDetailsTable.TABLENAME,new String[]{MovieDetailsTable.COLUMN_bg_path, MovieDetailsTable.COLUMN_ID, MovieDetailsTable.COLUMN_descr, MovieDetailsTable.COLUMN_date, MovieDetailsTable.COLUMN_title, MovieDetailsTable.COLUMN_rating},
                null, null, null, null, null, null);
        if(c!= null && c.moveToFirst())
        {
            do {
                MovieDetails mov = buildMovieFromCursor(c);
                movies.add(mov);

            }
            while(c.moveToNext());
            if(!c.isClosed())
                c.close();


        }
        return movies;
    }

    private MovieDetails buildMovieFromCursor(Cursor c){
        MovieDetails mov = null;
        if(c!=null){
            mov = new MovieDetails();
            mov.setBg_path(c.getString(0));
            mov.setId(c.getString(1));
            mov.setDescr(c.getString(2));
            mov.setDate(c.getString(3));
            mov.setTitle(c.getString(4));
            mov.setRating(c.getString(5));

        }
        return mov;
    }
}
