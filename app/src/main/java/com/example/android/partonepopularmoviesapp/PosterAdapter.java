package com.example.android.partonepopularmoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public PosterAdapter(Context mContext, ArrayList<Movie> movieList) {
        this.mContext = mContext;
        this.mMovieList = movieList;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.poster_item, viewGroup, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder posterViewHolder, int i) {
        Movie currentMovie = mMovieList.get(i);
        String posterUrl = currentMovie.getmPosterPath();
        Picasso.with(mContext)
                .load(posterUrl)
                .into(posterViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.movie_thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
