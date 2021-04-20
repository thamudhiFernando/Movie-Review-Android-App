package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviereviewandroidapp.adapter.MovieArrayAdapter;
import com.example.moviereviewandroidapp.database.MovieDatabase;
import com.example.moviereviewandroidapp.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouritesActivity extends AppCompatActivity  implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    //    Database
    private MovieDatabase movieDatabase;
    ListView listview;
    private List<Movie> movieList = new ArrayList<>();
    //    List of Favourite Movies
    private Map<Integer, Boolean> favouriteMoviesMap = new HashMap<>();
    private static final String LOG_TAG = DisplayMovieActivity.class.getSimpleName();
    //    Adapter Declaration
    MovieArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        movieDatabase = new MovieDatabase(this);
        System.out.println(movieDatabase.getFavouriteMovies().getCount());
        getAllMovies(movieDatabase.getFavouriteMovies());

        listview = findViewById(R.id.listview_movies);

        adapter = new MovieArrayAdapter(this, R.layout.movie_list, movieList, this);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        TextView textView = view.findViewById(R.id.movie_title);
        boolean checkedValue = true;
        int position = (Integer) view.getTag();
        if (favouriteMoviesMap.get(movieList.get(position).getId()) != null) {
            checkedValue = !favouriteMoviesMap.get(movieList.get(position).getId());
        } else {
            checkedValue = !movieList.get(position).isFavourite();
        }
        favouriteMoviesMap.put(movieList.get(position).getId(), checkedValue);
        Log.i("Clicked Movie : ", textView.getText().toString());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (Integer) buttonView.getTag();
        Log.i("Clicked on Checkbox", (position) + "");
        boolean value = true;
        if (favouriteMoviesMap.get(movieList.get(position).getId()) != null) {
            value = !favouriteMoviesMap.get(movieList.get(position).getId());
        } else {
            value = !movieList.get(position).isFavourite();
        }
        favouriteMoviesMap.put(movieList.get(position).getId(), value);
        Log.i("Clicked on Checkbox", String.valueOf(position));
    }


    public void clickedSaveButton(View view) {
        try {
            movieDatabase.makeMovieFavourite(favouriteMoviesMap);
            Log.d(LOG_TAG, "Favourite Updated Successfully");
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle(Html.fromHtml("<font color='#000200'>Saved Favourite Movies Successfully</font>"))
                    //set positive button
                    .setPositiveButton(Html.fromHtml("<font color='#FF0000'>OK</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            favouriteMoviesMap.clear();
                        }
                    })
                    //set negative button
                    .setCancelable(true)
                    .show();
        } catch (Exception error) {
            Toast toast = Toast.makeText(getApplicationContext(), "Failed to save favourite", Toast.LENGTH_SHORT);
            toast.show();
        }
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
}