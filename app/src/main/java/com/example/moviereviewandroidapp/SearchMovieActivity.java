package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.moviereviewandroidapp.adapter.MovieArrayAdapter;
import com.example.moviereviewandroidapp.database.MovieDatabase;
import com.example.moviereviewandroidapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieActivity extends AppCompatActivity {
    //    Database
    private MovieDatabase movieDatabase;
    ListView listview;
    private List<Movie> movieList = new ArrayList<>();
    //    Adapter Declaration
    MovieArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        movieDatabase = new MovieDatabase(this);
        this.searchMovies("");
    }

    private void getAllMovies(Cursor cursor) {
        System.out.println(cursor.getCount());
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

    public void searchMovies(String searchQuery) {
        this.movieList.clear();
        Cursor searchMovies_Cursor = movieDatabase.searchMovie(searchQuery);

        getAllMovies(searchMovies_Cursor);
        listview = findViewById(R.id.listview_search_movies);
        adapter = new MovieArrayAdapter(this, R.layout.search_movie_list, movieList, null);
        listview.setAdapter(adapter);
    }

    public void clickedLookUpButton(View view) {
        String searchQuery = ((EditText) findViewById(R.id.editText_input_search)).getText().toString();
        this.searchMovies(searchQuery);
    }
}