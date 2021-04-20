package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.moviereviewandroidapp.adapter.MovieArrayAdapter;
import com.example.moviereviewandroidapp.database.MovieDatabase;
import com.example.moviereviewandroidapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class EditMovieActivity extends AppCompatActivity {
    //    Database
    private MovieDatabase movieDatabase;
    ListView listview;
    private List<Movie> movieList = new ArrayList<>();
    //    Adapter Declaration
    MovieArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        movieDatabase = new MovieDatabase(this);
        getAllMovies(movieDatabase.getMovies());

        listview = findViewById(R.id.listview_movies_edit);

        adapter = new MovieArrayAdapter(this, R.layout.update_movie_list, movieList, null);
        listview.setAdapter(adapter);
    }

    private void getAllMovies(Cursor cursor) {
        while (cursor.moveToNext()) {
            //    Movie model
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
            movieList.add(movie);
        }
        cursor.close();
    }

    public void editMovies(View view) {
        Intent intent = new Intent(this, EditMovieDetailActivity.class);
        intent.putExtra("movie", movieList.get(Integer.parseInt(view.getTag().toString())).getId());
        startActivity(intent);
    }
}