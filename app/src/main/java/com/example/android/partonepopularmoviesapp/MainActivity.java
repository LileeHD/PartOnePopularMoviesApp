package com.example.android.partonepopularmoviesapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

// Credit resources:
//https://stackoverflow.com/questions/29579811/changing-number-of-columns-with-gridlayoutmanager-and-recyclerview


public class MainActivity extends AppCompatActivity implements PosterAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "movieTitle";
    public static final String EXTRA_DATE = "releaseDate";
    public static final String EXTRA_RATING = "voteAverage";
    public static final String EXTRA_OVERVIEW = "moviePitch";

    protected MovieDbURLBuilder movieDbURLBuilder;
    private RecyclerView mRecyclerView;

    private PosterAdapter mPosterAdapter;
    private ArrayList<Movie> mMovieList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        }

        mMovieList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        movieDbURLBuilder = new MovieDbURLBuilder();

        callRequest(movieDbURLBuilder.sortBy("popular").getUrl());
    }


    private void callRequest(String movieDbUrl) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, movieDbUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movieInfo = results.getJSONObject(i);
                        mMovieList.add(new Movie(movieInfo));
                    }
                    mPosterAdapter = new PosterAdapter(MainActivity.this, mMovieList);
                    mRecyclerView.setAdapter(mPosterAdapter);
                    mPosterAdapter.setOnItemClickListener(MainActivity.this);

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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Movie movieClicked = mMovieList.get(position);

        intent.putExtra(EXTRA_URL, movieClicked.getmPosterPath());
        intent.putExtra(EXTRA_TITLE, movieClicked.getmTitle());
        intent.putExtra(EXTRA_DATE, movieClicked.getmRelease_date());
        intent.putExtra(EXTRA_OVERVIEW, movieClicked.getmOverview());
        intent.putExtra(EXTRA_RATING, movieClicked.getmRating());

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.most_popular:
                break;
            case R.id.top_rated:
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

}
