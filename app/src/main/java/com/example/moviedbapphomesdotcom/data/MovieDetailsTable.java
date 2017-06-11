package com.example.moviedbapphomesdotcom.data;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sid on 10-06-2017.
 */

public class MovieDetailsTable {
    static final String TABLENAME = "movies";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_bg_path = "bg_path";
    static final String COLUMN_descr = "descr";
    static final String COLUMN_date = "date";
    static final String COLUMN_title = "title";
    static final String COLUMN_rating = "rating";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME +" (");
        sb.append(COLUMN_bg_path + " text not null, ");
        sb.append(COLUMN_ID + " text not null, ");
        sb.append(COLUMN_descr + " text not null, ");
        sb.append(COLUMN_date + " text not null, ");
        sb.append(COLUMN_title + " text not null, ");
        sb.append(COLUMN_rating + " text not null, ");
        sb.append("PRIMARY KEY("+COLUMN_ID+"));");

        try{
            db.execSQL(sb.toString());
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        MovieDetailsTable.onCreate(db);
    }
}
