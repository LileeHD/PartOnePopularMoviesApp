package com.example.android.partonepopularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.android.partonepopularmoviesapp.MainActivity.EXTRA_DATE;
import static com.example.android.partonepopularmoviesapp.MainActivity.EXTRA_OVERVIEW;
import static com.example.android.partonepopularmoviesapp.MainActivity.EXTRA_RATING;
import static com.example.android.partonepopularmoviesapp.MainActivity.EXTRA_TITLE;
import static com.example.android.partonepopularmoviesapp.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String movieTitle = intent.getStringExtra(EXTRA_TITLE);
        String moveDate= intent.getStringExtra(EXTRA_DATE);
        String rating = String.valueOf(intent.getDoubleExtra(EXTRA_RATING, 0));
        String movieOverview = intent.getStringExtra(EXTRA_OVERVIEW);

        ImageView posterView = findViewById(R.id.movie_poster_detail);

        TextView titleView = findViewById(R.id.movie_title);
        TextView releaseDateView = findViewById(R.id.movie_release_date);
        TextView ratingView = findViewById(R.id.movie_rating);
        TextView overView = findViewById(R.id.movie_overview);

        titleView.setText(movieTitle);
        releaseDateView.setText(moveDate);
        ratingView.setText(rating+"/10");
        overView.setText(movieOverview);

        Picasso.with(this)
                .load(imageUrl)
                .fit()
                .centerInside()
                .into(posterView);
    }
}
