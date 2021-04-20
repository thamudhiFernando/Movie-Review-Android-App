package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviereviewandroidapp.database.MovieDatabase;
import com.example.moviereviewandroidapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class EditMovieDetailActivity extends AppCompatActivity {

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

    private int moviesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_detail);

        title = findViewById(R.id.editText_input_1);
        year = findViewById(R.id.editText_input_2);
        director = findViewById(R.id.editText_input_3);
        actor = findViewById(R.id.editText_input_4);
        rating = findViewById(R.id.editText_input_5);
        review = findViewById(R.id.editText_input_6);

        movieDatabase = new MovieDatabase(this);
        int movieId = getIntent().getExtras().getInt("movie");
        this.moviesId = movieId;
        getMovie(movieDatabase.getMovie(movieId));
    }

    public void clickedUpdateMovieButton(View view) {
        try {
            Movie movie = new Movie();
            movie.setTitle(title.getText().toString());
            movie.setYear(Integer.parseInt(year.getText().toString()));
            movie.setDirector(director.getText().toString());
            movie.setActor(actor.getText().toString());
            movie.setRating(Integer.parseInt(rating.getText().toString()));
            movie.setReview(review.getText().toString());
            movieDatabase.editMovies(movie);

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle(Html.fromHtml("<font color='#000200'>Movie Updated Successfully</font>"))
                    //set positive button
                    .setPositiveButton(Html.fromHtml("<font color='#FF0000'>OK</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d(LOG_TAG, "Data Updated Successfully");
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

    private void getMovie(Cursor cursor) {
        if (cursor.moveToNext()){
            this.title.setText(cursor.getString(1));
            this.year.setText(String.valueOf(cursor.getInt(2)));
            this.director.setText(cursor.getString(3));
            this.actor.setText(cursor.getString(4));
            this.rating.setText(String.valueOf(cursor.getInt(5)));
            this.review.setText(cursor.getString(6));
        }
    }
}