package com.example.moviedbapphomesdotcom.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviedbapphomesdotcom.models.MovieDetails;

import java.util.List;

/**
 * Created by sid on 10-06-2017.
 */

public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private MoviesDAO moviesDAO;
    public DatabaseDataManager(Context context)
    {
        mContext = context;
        dbOpenHelper  = new DatabaseOpenHelper(mContext);
        db= dbOpenHelper.getWritableDatabase();
        moviesDAO = new MoviesDAO(db);
    }
    public void close()
    { if(db!=null)
        db.close();
    }

    public long saveMovie(MovieDetails mov)
    {
        return  this.moviesDAO.save(mov);
    }

    public boolean deleteMovie(MovieDetails mov)
    {
        return  this.moviesDAO.delete(mov);
    }

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public MovieDetails getMovie(String id)
    {
        return this.moviesDAO.get(id);
    }

    public List<MovieDetails> getAllMovies()
    {
        return  this.moviesDAO.getAll();
    }


}
