package com.example.android.partonepopularmoviesapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private String mTitle;
    private String mPosterPath;
    private String mRelease_date;
    private double mRating;
    private String mOverview;

    private String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";


    public Movie(JSONObject info) throws JSONException {

        this.mPosterPath = info.getString("poster_path");
        this.mTitle = info.getString("title");
        this.mRelease_date = info.getString("release_date");
        this.mRating = info.getInt("vote_average");
        this.mOverview = info.getString("overview");
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmPosterPath() {
        return IMG_BASE_URL + mPosterPath;
    }

    public String getmRelease_date() {
        return mRelease_date;
    }

    public double getmRating() {
        return mRating;
    }

    public String getmOverview() {
        return mOverview;
    }
}
