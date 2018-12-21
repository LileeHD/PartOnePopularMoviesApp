package com.example.android.partonepopularmoviesapp;

public class Movie {
    private int mMovieId;
    private String mTitle;
    private String mPosterPath;
    private String mRelease_date;
    private String mRating;
    private String mOverview;

    public Movie(String posterPath) {
//        this.mTitle = title;
        this.mPosterPath = posterPath;
//        this.mRelease_date = release_date;
//        this.mRating = rating;
//        this.mOverview = overview;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public String getmRelease_date() {
        return mRelease_date;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmOverview() {
        return mOverview;
    }
}
