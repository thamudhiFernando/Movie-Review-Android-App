package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviereviewandroidapp.database.MovieDatabase;
import com.example.moviereviewandroidapp.model.Movie;
import com.example.moviereviewandroidapp.adapter.MovieArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayMovieActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    //    Database
    private MovieDatabase movieDatabase;
    static final private String DB_TABLE = "Movie";
    ListView listview;
    private List<Movie> modules = new ArrayList<>();
    private Map<Integer, Boolean> favMovies = new HashMap<>();
    private static final String LOG_TAG = DisplayMovieActivity.class.getSimpleName();
    MovieArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

        movieDatabase = new MovieDatabase(this);
        System.out.println(movieDatabase.getMovies().getCount());
        getAllMovies(movieDatabase.getMovies());

        listview = findViewById(R.id.listview_movies);

        adapter = new MovieArrayAdapter(this, R.layout.movie_list, modules, this);
        listview.setAdapter(adapter);
    }

    public void addToFavourite(View view) {
        try {
            SQLiteDatabase db = movieDatabase.getWritableDatabase();
            for (Map.Entry<Integer, Boolean> entry : favMovies.entrySet()) {
                ContentValues values = new ContentValues();
                values.put(Movie.COLUMN_FAVOURITE, entry.getValue());
                db.update(DB_TABLE, values, Movie.COLUMN_ID + " = " + entry.getKey(), null);
            }
            Log.d(LOG_TAG, "Favourite Updated Successfully");
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
//                    .setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle(Html.fromHtml("<font color='#00b300'>Favourite Updated Successfully</font>"))
                    //set positive button
                    .setPositiveButton(Html.fromHtml("<font color='#00b300'>OK</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            favMovies.clear();
                        }
                    })
                    //set negative button
                    .setCancelable(true)
                    .show();
        } catch (Exception error) {
            Toast toast = Toast.makeText(getApplicationContext(), "Failed to update favourite", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onClick(View view) {
        TextView textView = view.findViewById(R.id.movie_title);
        boolean checkedValue = true;
        int position = (Integer) view.getTag();
        if (favMovies.get(modules.get(position).getId()) != null) {
            checkedValue = !favMovies.get(modules.get(position).getId());
        } else {
            checkedValue = !modules.get(position).isFavourite();
        }
        favMovies.put(modules.get(position).getId(), checkedValue);
        Log.i("Clicked Movie : ", textView.getText().toString());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (Integer) buttonView.getTag();
        Log.i("Clicked on Checkbox", (position) + "");
        boolean value = true;
        if (favMovies.get(modules.get(position).getId()) != null) {
            value = !favMovies.get(modules.get(position).getId());
        } else {
            value = !modules.get(position).isFavourite();
        }
        favMovies.put(modules.get(position).getId(), value);
        Log.i("Clicked on Checkbox", String.valueOf(position));
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
            modules.add(movie);
        }
        cursor.close();
    }
}