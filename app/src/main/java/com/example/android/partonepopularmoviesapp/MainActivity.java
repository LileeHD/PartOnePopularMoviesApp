package com.example.android.partonepopularmoviesapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PosterAdapter mPosterAdapter;
    private ArrayList<Movie> mMovieList;
    private RequestQueue mRequestQueue;
    public static final String POSTER_URL_BASE = "https://image.tmdb.org/t/p/w185";
    public static final String MOVIE_DB_URL_BASE = "http://api.themoviedb.org/3/movie/popular";
    public static final String API_KEY = "?api_key=";
    public static final String YOUR_KEY = "";
//    private static final String TOP_RATED = "top_rated?";
    private static final String MOVIE_DB_URL = MOVIE_DB_URL_BASE+API_KEY+YOUR_KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, false));

        mMovieList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        JsonParse();
    }

    private void JsonParse() {
        String url = MOVIE_DB_URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject movie = jsonArray.getJSONObject(i);
                        String posterPath = movie.getString("poster_path");
                        String imageUrl = "https://image.tmdb.org/t/p/w500" + posterPath;
                        mMovieList.add(new Movie(imageUrl));
                    }
                    mPosterAdapter = new PosterAdapter(MainActivity.this, mMovieList);
                    mRecyclerView.setAdapter(mPosterAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }
}
