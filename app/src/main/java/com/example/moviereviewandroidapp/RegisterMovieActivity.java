package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviereviewandroidapp.database.MovieDatabase;
import com.example.moviereviewandroidapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;

public class RegisterMovieActivity extends AppCompatActivity {
    //    Database
    private MovieDatabase movieDatabase;
    static final private String DB_TABLE = "Movie";
    //    Movie model
    private Movie movie;
    private List<Movie> modules = new ArrayList<>();

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

        movieDatabase = new MovieDatabase(this);
        System.out.println(movieDatabase.getMovies().getCount());
//        getAllMovies(movieDatabase.getMovies());
    }

    public void clickedSaveMovieButton(View view) {
        try {
            Movie movie = new Movie();
            movie.setTitle(title.getText().toString());
            movie.setYear(Integer.parseInt(year.getText().toString()));
            movie.setDirector(director.getText().toString());
            movie.setActor(actor.getText().toString());
            movie.setRating(Integer.parseInt(rating.getText().toString()));
            movie.setReview(review.getText().toString());
            movieDatabase.insertMovie(movie);

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle(Html.fromHtml("<font color='#00b300'>Movie Added Successfully</font>"))
                    //set positive button
                    .setPositiveButton(Html.fromHtml("<font color='#00b300'>OK</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d(LOG_TAG, "Data Saved Successfully");
                        }
                    })
                    //set negative button
                    .setCancelable(true)
                    .show();
        } catch (Exception error) {
            Toast toast = Toast.makeText(getApplicationContext(), "Failed to add New Movie", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void getAllMovies(Cursor cursor) {
        movie = new Movie();
        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.setId(cursor.getInt(0));
            movie.setTitle(cursor.getString(1));
            movie.setYear(cursor.getInt(2));
            movie.setDirector(cursor.getString(3));
            movie.setActor(cursor.getString(4));
            movie.setRating(cursor.getInt(5));
            movie.setReview(cursor.getString(6));
            movie.setFavourite(cursor.getString(7).equals("1"));
            movie.toString();
            modules.add(movie);
        }
        cursor.close();
    }
}