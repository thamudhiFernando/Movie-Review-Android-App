package com.example.moviereviewandroidapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MovieDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "MovieDB";
    static final private int DB_VERSION = 1;
    Context ctx;
    SQLiteDatabase sqLiteDatabase;
    private static final String LOG_TAG = MovieDatabase.class.getSimpleName();

    public MovieDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.ctx = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
