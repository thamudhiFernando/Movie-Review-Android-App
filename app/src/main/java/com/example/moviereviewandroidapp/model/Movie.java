package com.example.moviereviewandroidapp.model;

public class Movie {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_ACTORS = "actor";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_REVIEW = "review";
    public static final String COLUMN_FAVOURITE = "favourite";

    private int id;
    private String title;
    private int year;
    private String director;
    private String actor;
    private int rating;
    private String review;
    private String favourite;

    public Movie() {

    }

    public Movie(String title, int year, String director, String actor, int rating, String review, String favourite) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.actor = actor;
        this.rating = rating;
        this.review = review;
        this.favourite = favourite;
    }

    public Movie(int id, String title, int year, String director, String actor, int rating, String review, String favourite) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actor = actor;
        this.rating = rating;
        this.review = review;
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", favourite='" + favourite + '\'' +
                '}';
    }
}
