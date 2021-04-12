package com.example.moviereviewandroidapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MovieDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "TestMovieDB";
    static final private String DB_TABLE = "Movie";
    static final private int DB_VERSION = 1;
    Context ctx;
    private static final String LOG_TAG = MovieDatabase.class.getSimpleName();

    public MovieDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_TABLE + " (id INTEGER primary key autoincrement," +
                "title TEXT NOT NULL," +
                "year INTEGER NOT NULL, " +
                "director TEXT NOT NULL, " +
                "actor TEXT NOT NULL, " +
                "rating INTEGER NOT NULL, " +
                "review TEXT NOT NULL," +
                "favourite TEXT NOT NULL);");
        Log.d(LOG_TAG, DB_TABLE + " Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        Log.d(LOG_TAG, "on Upgrade " + DB_TABLE);
        onCreate(db);
    }
}
