package com.example.popularmoviesapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private final MovieAdapterOnClickHandler mClickHandler;
    private final String LOG_TAG = MovieAdapter.class.getName();
    private final Context ctx;
    private ArrayList<Movie> mMovies;

    public MovieAdapter(MovieAdapterOnClickHandler mClickHandler, Context context, ArrayList<Movie> movies) {
        this.mClickHandler = mClickHandler;
        ctx = context;
        mMovies = movies;
    }

    void setMovieList(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myView = myInflater.inflate(R.layout.list_element, parent, false);
        return new MovieHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Log.d(LOG_TAG, mMovies.get(position).getImage());
        Picasso.get()
                .load(mMovies.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found)
                .into(holder.myImageView);
        //holder.myImageView.setImageResource(mMovies[position].getImage());
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public void setMovies(ArrayList<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView myImageView;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie currentMovie = mMovies.get(adapterPosition);
            mClickHandler.onClick(currentMovie);
        }
    }
}
