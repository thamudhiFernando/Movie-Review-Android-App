package com.example.moviereviewandroidapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moviereviewandroidapp.R;
import com.example.moviereviewandroidapp.model.Movie;

import java.util.List;


public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    Context ctx;
    int resID;
    View.OnClickListener callback;

    public MovieArrayAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects, View.OnClickListener callback) {
        super(context, resource, objects);
        ctx = context;
        resID = resource;
        this.callback = callback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View movieView = convertView;
        Movie movie = getItem(position);
        if (movieView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            movieView = inflater.inflate(resID, parent, false);
            if (callback != null) {
                CheckBox checkbox = movieView.findViewById(R.id.fav_checkbox);
                if (checkbox != null) {
                    checkbox.setChecked(movie.isFavourite());
                }
            }
        }
        TextView titleTextView = movieView.findViewById(R.id.movie_title);
        titleTextView.setText(movie.getTitle());

        TextView yearTextView = movieView.findViewById(R.id.movie_year_1);
        yearTextView.setText(String.valueOf(movie.getYear()));

        ImageView movieViewViewById = movieView.findViewById(R.id.imageView_card_movie);
        if (position % 3 == 0) {
            movieViewViewById.setImageResource(R.drawable.flower_1);
        }else if (position % 3 == 1) {
            movieViewViewById.setImageResource(R.drawable.flower_2);
        }else {
            movieViewViewById.setImageResource(R.drawable.flower_3);
        }

        CheckBox checkbox_movie = movieView.findViewById(R.id.fav_checkbox);
        checkbox_movie.setTag(position);
        checkbox_movie.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) callback);

        movieView.setOnClickListener(callback);
        movieView.setTag(position);

        return movieView;
    }

}
