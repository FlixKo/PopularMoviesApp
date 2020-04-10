package com.example.popularmoviesapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<Movie> mMovies;
    private final MovieAdapterOnClickHandler mClickHandler;
    private final String LOG_TAG = MovieAdapter.class.getName();

    Context ctx;
    public MovieAdapter(MovieAdapterOnClickHandler mClickHandler, Context context, ArrayList<Movie> movies){
        this.mClickHandler = mClickHandler;
        ctx = context;
        mMovies = movies;
    }


    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myView = myInflater.inflate(R.layout.list_element, parent,false);
        return new MovieHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Log.d(LOG_TAG, mMovies.get(position).getImage());
        Picasso.get()
                .load(mMovies.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                //.resize(500,500)
                .into(holder.myImageView);
        //holder.myImageView.setImageResource(mMovies[position].getImage());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView myImageView;
        public MovieHolder(@NonNull View itemView) {
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
