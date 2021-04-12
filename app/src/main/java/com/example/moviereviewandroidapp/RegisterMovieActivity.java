package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviereviewandroidapp.database.MovieDatabase;
import com.example.moviereviewandroidapp.model.Movie;

import static android.provider.BaseColumns._ID;

public class RegisterMovieActivity extends AppCompatActivity {
    //    Database
    private MovieDatabase movieDatabase;
    static final private String DB_TABLE = "Movie";
    //    Movie model
    private Movie movie;

    private static final String LOG_TAG = RegisterMovieActivity.class.getSimpleName();

    private EditText title;
    private EditText year;
    private EditText director;
    private EditText actor;
    private EditText rating;
    private EditText review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        title = findViewById(R.id.editText_input_1);
        year = findViewById(R.id.editText_input_2);
        director = findViewById(R.id.editText_input_3);
        actor = findViewById(R.id.editText_input_4);
        rating = findViewById(R.id.editText_input_5);
        review = findViewById(R.id.editText_input_6);
        movieDatabase = new MovieDatabase(this);

//        SQLiteDatabase db = movieDatabase.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM "+ DB_TABLE ,null);
//        System.out.println(cursor.getCount());
//        getAllMovies(cursor);
    }

    public void clickedSaveMovieButton(View view) {
        try {
            SQLiteDatabase database = movieDatabase.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Movie.COLUMN_TITLE, title.getText().toString());
            values.put(Movie.COLUMN_YEAR, Integer.parseInt(year.getText().toString()));
            values.put(Movie.COLUMN_DIRECTOR, director.getText().toString());
            values.put(Movie.COLUMN_ACTORS, actor.getText().toString());
            values.put(Movie.COLUMN_RATING, Integer.parseInt(rating.getText().toString()));
            values.put(Movie.COLUMN_REVIEW, review.getText().toString());
            values.put(Movie.COLUMN_FAVOURITE, "yes");
            database.insertOrThrow(DB_TABLE, null, values);
            Log.d(LOG_TAG, "Data Saved Successfully");
        } catch (Exception error) {
            Toast toast = Toast.makeText(getApplicationContext(), "Failed to add New Movie", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void getAllMovies(Cursor cursor) {
        movie = new Movie();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int year = cursor.getInt(2);
            String director = cursor.getString(3);
            String actor = cursor.getString(4);
            int rating = cursor.getInt(5);
            String review = cursor.getString(6);
            String fav = cursor.getString(7);
            movie.setId(id);
            movie.setYear(year);
            movie.setDirector(director);
            movie.setActor(actor);
            movie.setRating(rating);
            movie.setReview(review);
            movie.setFavourite(fav);
            movie.toString();
        }
        cursor.close();
    }
}