package com.example.moviedbapphomesdotcom.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sid on 10-06-2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper{
    static final String DB_NAME = "movies.db";
    static final int DB_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MovieDetailsTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MovieDetailsTable.onUpgrade(db, oldVersion, newVersion);
    }
}
