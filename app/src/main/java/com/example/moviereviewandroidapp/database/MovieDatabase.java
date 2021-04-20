package com.example.moviereviewandroidapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.moviereviewandroidapp.model.Movie;

import java.util.Map;

public class MovieDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "TestMovieDB1";
    static final private String DB_TABLE = "Movie";
    static final private int DB_VERSION = 1;
    Context ctx;

    private static final String[] FROM = {Movie.COLUMN_ID, Movie.COLUMN_TITLE, Movie.COLUMN_YEAR, Movie.COLUMN_DIRECTOR, Movie.COLUMN_ACTORS, Movie.COLUMN_RATING, Movie.COLUMN_REVIEW, Movie.COLUMN_FAVOURITE};
    private static final String ORDER_BY = Movie.COLUMN_TITLE + " ASC";
    private static final String WHERE_SEARCH = Movie.COLUMN_TITLE + " LIKE ? OR " + Movie.COLUMN_DIRECTOR + " LIKE ? OR " + Movie.COLUMN_ACTORS + " LIKE ? ";
    private static final String WHERE_ID = Movie.COLUMN_ID + "=?";
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
                "favourite BOOLEAN NOT NULL);");
        Log.d(LOG_TAG, DB_TABLE + " Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        Log.d(LOG_TAG, "on Upgrade " + DB_TABLE);
        onCreate(db);
    }

    public Cursor getMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE + " ORDER BY " + Movie.COLUMN_TITLE + " ASC;", null);
        return cursor;
    }

    public Cursor getFavouriteMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE " + Movie.COLUMN_FAVOURITE + " = 'true' ORDER BY " + Movie.COLUMN_TITLE + " ASC;", null);
        return cursor;
    }

    public void insertMovie(Movie movie) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Movie.COLUMN_TITLE, movie.getTitle());
        values.put(Movie.COLUMN_YEAR, movie.getYear());
        values.put(Movie.COLUMN_DIRECTOR, movie.getDirector());
        values.put(Movie.COLUMN_ACTORS, movie.getActor());
        values.put(Movie.COLUMN_RATING, movie.getRating());
        values.put(Movie.COLUMN_REVIEW, movie.getReview());
        values.put(Movie.COLUMN_FAVOURITE, false);
        long insert = database.insert(DB_TABLE, null, values);
        Log.d(LOG_TAG, "Data Saved Successfully");
    }

    public void makeMovieFavourite(Map<Integer, Boolean> favouriteMoviesMap){
        SQLiteDatabase db = this.getWritableDatabase();
        for (Map.Entry<Integer, Boolean> entry : favouriteMoviesMap.entrySet()) {
            ContentValues values = new ContentValues();
            values.put(Movie.COLUMN_FAVOURITE, entry.getValue());
            db.update(Movie.DB_TABLE, values, Movie.COLUMN_ID + " = " + entry.getKey(), null);
        }
    }

    public Cursor searchMovie(String searchQuery) {
        SQLiteDatabase liteDatabase = this.getReadableDatabase();
        if (searchQuery == null || searchQuery.equals("")){
            return this.getMovies();
        }
        return liteDatabase.query(DB_TABLE, FROM, WHERE_SEARCH, new String[]{"%"+ searchQuery +"%", "%"+ searchQuery +"%", "%"+ searchQuery +"%"}, null, null, ORDER_BY);
    }

    public void editMovies(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Movie.COLUMN_TITLE, movie.getTitle());
        values.put(Movie.COLUMN_YEAR, movie.getYear());
        values.put(Movie.COLUMN_DIRECTOR, movie.getDirector());
        values.put(Movie.COLUMN_ACTORS, movie.getActor());
        values.put(Movie.COLUMN_RATING, movie.getRating());
        values.put(Movie.COLUMN_REVIEW, movie.getReview());
        values.put(Movie.COLUMN_FAVOURITE, false);

        db.update(DB_TABLE,values,"id=" + movie.getId(), null);
    }

    public Cursor getMovie(int movieIDd){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(DB_TABLE, FROM, WHERE_ID,  new String[]{movieIDd + ""}, null, null, ORDER_BY);
    }
}
