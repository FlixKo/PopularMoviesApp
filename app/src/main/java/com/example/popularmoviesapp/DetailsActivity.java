package com.example.popularmoviesapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesapp.utilities.JsonUtils;
import com.example.popularmoviesapp.utilities.MovieDatabase;
import com.example.popularmoviesapp.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity implements TrailerAdapter.TrailerAdapterOnClickHandler{

    private MovieDatabase mDb;
    private static final String LOG_TAG = DetailsActivity.class.getSimpleName();

    private RecyclerView recyclerViewReview;
    private ReviewAdapter mReviewAdapter;
    private RecyclerView recyclerViewTrailer;
    private TrailerAdapter mTrailerAdapter;

    // TODO!
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView mTitle = findViewById(R.id.tv_movie_title);
        TextView mYear = findViewById(R.id.tv_movie_year);
        TextView mDescription = findViewById(R.id.tv_movie_description);
        TextView mRating = findViewById(R.id.tv_movie_rating);
        ImageView mPoster = findViewById(R.id.iv_movie_poster);
        final Button mFavorite = findViewById(R.id.bt_favorite);

        recyclerViewReview = findViewById(R.id.recycler_reviews);
        recyclerViewReview.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewTrailer = findViewById(R.id.recycler_trailer);
        recyclerViewTrailer.setLayoutManager(new LinearLayoutManager(this));

        mDb = MovieDatabase.getInstance(getApplicationContext());

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("movie")) {
                final Movie mMovie = Objects.requireNonNull(intentThatStartedThisActivity.getExtras()).getParcelable("movie");
                mTitle.setText(Objects.requireNonNull(mMovie).getTitle());
                mYear.setText(mMovie.getYear());
                mDescription.setText(mMovie.getDescription());
                mRating.setText(mMovie.getRating());
                //mPoster.setImageResource(mMovie.getImage());

                Picasso.get()
                        .load(mMovie.getImage())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.image_not_found)
                        .into(mPoster);

                //Trailer
                URL trailerUrl = NetworkUtils.buildTrailerUrl(String.valueOf(mMovie.getMovieID()));
                Log.d(LOG_TAG, "Trailer URL: " + trailerUrl);
                new MovieDbQueryTask().execute(trailerUrl);

                //Reviews
                URL reviewsURL = NetworkUtils.buildReviewUrl(String.valueOf(mMovie.getMovieID()));
                Log.d(LOG_TAG, "Reviews URL: " + reviewsURL);
                new MovieDbQueryTask().execute(reviewsURL);

                // Load favorite Movies from Database
                List<Movie> moviesList = mDb.movieDao().loadAllMovies();
                if (moviesList.size() > 0) {
                    for (int i = 0; i < moviesList.size(); i++) {
                        if (moviesList.get(i).getMovieID() == mMovie.getMovieID()) {
                            mFavorite.setText("Delete from Favorites");
                        }
                    }
                }

//                {mFavorite.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        AsyncTask.execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    if(mFavorite.getText().toString().equals("Mark as Favorite")){
//                                        Log.d(LOG_TAG, "Added " + Objects.requireNonNull(mMovie).getTitle() + " to favorites");
//                                        // store movie in DB
//                                        Movie movie = new Movie(mMovie.getImage(), mMovie.getYear(), mMovie.getTitle(), mMovie.getRating(), mMovie.getDescription(), mMovie.getMovieID());
//                                        mDb.movieDao().insertMovie(movie);
//                                        runOnUiThread(new Runnable() {
//                                            public void run() {
//                                                Toast.makeText(getApplicationContext(),"Added " + Objects.requireNonNull(mMovie).getTitle() + " to favorites", Toast.LENGTH_SHORT).show();
//                                                mFavorite.setText("Delete from Favorites");
//                                            }
//                                        });
//                                    }else{
//                                        Log.d(LOG_TAG, "Deleted " + Objects.requireNonNull(mMovie).getTitle() + " from favorites");
//                                        // store movie in DB
//                                        mDb.movieDao().deleteMovie(mMovie);
//                                        runOnUiThread(new Runnable() {
//                                            public void run() {
//                                                Toast.makeText(getApplicationContext(),"Deleted " + Objects.requireNonNull(mMovie).getTitle() + " from favorites", Toast.LENGTH_SHORT).show();
//                                                mFavorite.setText("Mark as Favorite");
//                                            }
//                                        });
//                                    }
//                                }
//                                catch (SQLiteConstraintException ex){
//                                    runOnUiThread(new Runnable() {
//                                        public void run() {
//                                            Toast.makeText(getApplicationContext(),"Item already added",Toast.LENGTH_LONG).show();
//                                        }
//                                    });
//                                }
//                            }
//                        });
//                    }
//                });}


//                mFavorite.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(mFavorite.getText().toString().equals("Mark as Favorite")){
//                            Log.d(LOG_TAG, "Added " + Objects.requireNonNull(mMovie).getTitle() + " to favorites");
//                            // store movie in DB
//                            Movie movie = new Movie(mMovie.getImage(), mMovie.getYear(), mMovie.getTitle(), mMovie.getRating(), mMovie.getDescription(), mMovie.getMovieID());
//                            mDb.movieDao().insertMovie(movie);
//                            Toast.makeText(getApplicationContext(),"Added " + Objects.requireNonNull(mMovie).getTitle() + " to favorites", Toast.LENGTH_SHORT).show();
//                            mFavorite.setText("Delete from Favorites");
//                        }else{
//                            Log.d(LOG_TAG, "Deleted " + Objects.requireNonNull(mMovie).getTitle() + " from favorites");
//                            // store movie in DB
//                            mDb.movieDao().deleteMovie(mMovie);
//                            Toast.makeText(getApplicationContext(),"Deleted " + Objects.requireNonNull(mMovie).getTitle() + " from favorites", Toast.LENGTH_SHORT).show();
//                            mFavorite.setText("Mark as Favorite");
//                        }
//
//                    }
//                });
            }
        }
    }

    @Override
    public void onClick(Trailer trailer) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(trailer.getYoutubeLink()));
        try {

            DetailsActivity.this.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            DetailsActivity.this.startActivity(webIntent);
        }
    }

    private void showTrailerDataView(String searchResult) throws JSONException {
        //mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mTrailerAdapter = new TrailerAdapter(this, JsonUtils.extractTrailerFromJSON(searchResult), getApplicationContext());
        recyclerViewTrailer.setAdapter(mTrailerAdapter);
        //scrollView.setVisibility(View.VISIBLE);
    }

    private void showReviewDataView(String searchResult) throws JSONException {
        //mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mReviewAdapter = new ReviewAdapter(JsonUtils.extractReviewFromJSON(searchResult), getApplicationContext());
        recyclerViewReview.setAdapter(mReviewAdapter);
        //scrollView.setVisibility(View.VISIBLE);
    }


    class MovieDbQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {

            URL searchUrl = urls[0];
            String movieDbResults = null;
            try {
                movieDbResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error fetching movies!!", e);
                //showErrorMessage();
            }
            return movieDbResults;
        }

        @Override
        protected void onPostExecute(String movieDbSearchResults) {
            //mLoadingIndicator.setVisibility(View.INVISIBLE);
            Log.d(LOG_TAG, movieDbSearchResults);
            if (movieDbSearchResults != null && !movieDbSearchResults.equals("")) {

                if (movieDbSearchResults.contains("iso_")) {
                    Log.d(LOG_TAG, "Getting Trailer information");
                    try {
                        showTrailerDataView(movieDbSearchResults);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(LOG_TAG, "Getting Review information");
                    try {
                        showReviewDataView(movieDbSearchResults);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                //showErrorMessage();
                Log.e(LOG_TAG, "Error getting results");
            }
        }
    }
}
