 package com.example.moviereviewandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

 public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     public void clickedRegisterButton(View view) {
         Log.d(LOG_TAG, "Register button Clicked !");
         Intent intent = new Intent(this, RegisterMovieActivity.class);
         startActivity(intent);
     }

     public void clickedDisplayButton(View view) {
         Log.d(LOG_TAG, "Display button Clicked !");
         Intent intent = new Intent(this, DisplayMovieActivity.class);
         startActivity(intent);
     }

     public void clickedFavouritesButton(View view) {
         Log.d(LOG_TAG, "Favourites button Clicked !");
         Intent intent = new Intent(this, FavouritesActivity.class);
         startActivity(intent);
     }

     public void clickedEditButton(View view) {
         Log.d(LOG_TAG, "Edit button Clicked !");
         Intent intent = new Intent(this, EditMovieActivity.class);
         startActivity(intent);
     }

     public void clickedSearchButton(View view) {
         Log.d(LOG_TAG, "Search button Clicked !");
         Intent intent = new Intent(this, SearchMovieActivity.class);
         startActivity(intent);
     }

     public void clickedRatingButton(View view) {
         Log.d(LOG_TAG, "Rating button Clicked !");
         Intent intent = new Intent(this, RatingActivity.class);
         startActivity(intent);
     }
 }