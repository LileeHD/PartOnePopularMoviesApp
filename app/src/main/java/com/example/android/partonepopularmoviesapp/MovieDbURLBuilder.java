package com.example.android.partonepopularmoviesapp;

public class MovieDbURLBuilder {

    private String defaultUrl = "https://api.themoviedb.org/3/movie/[SORTED]?api_key=[API_KEY]";

    private String sort = null;


    public MovieDbURLBuilder() {
        defaultUrl = defaultUrl.replace("[API_KEY]", "e9a4d258ab2f1609f16bd29d0eef3719");
    }

    public String getUrl() {
        String sort;
        if (this.sort != null) {
            sort = "popular";
        } else {
            sort = this.sort;
        }
//        String sort = this.sort != null ? this.sort : "popular";
        String url = defaultUrl.replace("[SORTED]", sort);
        this.sort = null;
        return url;

    }

    public MovieDbURLBuilder sortBy(String sort) {
        this.sort = sort;
        return this;
    }

}
