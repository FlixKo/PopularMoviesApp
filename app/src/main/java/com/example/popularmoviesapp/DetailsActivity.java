package com.example.popularmoviesapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesapp.models.Movie;
import com.example.popularmoviesapp.models.MovieViewModel;
import com.example.popularmoviesapp.models.Review;
import com.example.popularmoviesapp.models.ReviewViewModel;
import com.example.popularmoviesapp.models.Trailer;
import com.example.popularmoviesapp.models.TrailerViewModel;
import com.example.popularmoviesapp.utilities.AppExecutors;
import com.example.popularmoviesapp.utilities.FetchReviewsFromNetwork;
import com.example.popularmoviesapp.utilities.FetchTrailerFromNetwork;
import com.example.popularmoviesapp.database.MovieDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity implements TrailerAdapter.TrailerAdapterOnClickHandler {

    private MovieDatabase mDb;

    private ReviewAdapter mReviewAdapter;
    private TrailerAdapter mTrailerAdapter;
    private Movie mMovie;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        MovieViewModel movieViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                getApplication()).create(MovieViewModel.class);

        final TextView mTitle = findViewById(R.id.tv_movie_title);
        final TextView mYear = findViewById(R.id.tv_movie_year);
        final TextView mDescription = findViewById(R.id.tv_movie_description);
        final TextView mRating = findViewById(R.id.tv_movie_rating);
        final ImageView mPoster = findViewById(R.id.iv_movie_poster);
        final Button mFavorite = findViewById(R.id.bt_favorite);

        RecyclerView recyclerViewReview = findViewById(R.id.recycler_reviews);
        recyclerViewReview.setLayoutManager(new LinearLayoutManager(this));
        mReviewAdapter = new ReviewAdapter();
        recyclerViewReview.setAdapter(mReviewAdapter);

        RecyclerView recyclerViewTrailer = findViewById(R.id.recycler_trailer);
        recyclerViewTrailer.setLayoutManager(new LinearLayoutManager(this));
        mTrailerAdapter = new TrailerAdapter(this);
        recyclerViewTrailer.setAdapter(mTrailerAdapter);

        mDb = MovieDatabase.getInstance(getApplicationContext());

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("movie")) {
                mMovie = Objects.requireNonNull(intentThatStartedThisActivity.getExtras()).getParcelable("movie");

                FetchReviewsFromNetwork.getReviews(getApplicationContext(), mMovie.getMovieID());
                getReviews(mMovie.getMovieID());
                FetchTrailerFromNetwork.getTrailers(getApplicationContext(), mMovie.getMovieID());
                getTrailers(mMovie.getMovieID());
            }
        }

                movieViewModel.getMovie(mMovie.getMovieID()).observe(this, new Observer<Movie>() {
                    @Override
                    public void onChanged(Movie movie) {
                        setTitle(R.string.movie_details);
                        mTitle.setText(Objects.requireNonNull(movie).getTitle());
                        mYear.setText(movie.getYear());
                        mDescription.setText(movie.getDescription());
                        mRating.setText(movie.getRating());
                        Picasso.get()
                                .load(movie.getImage())
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.image_not_found)
                                .into(mPoster);

                        if(movie.isFavorite()){
                            mFavorite.setText(R.string.remove_fav);
                        }else {
                            mFavorite.setText(R.string.add_fav);
                        }
                        //mFavorite.setBackground(mMovie.isFavorite() ? R.drawable.favorite_empty : R.drawable.favorite_filled);
                    }
                });

                mFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                final Movie movie = mDb.movieDao().getMovieById(mMovie.getMovieID());
                                movie.setFavorite(!mMovie.isFavorite());
                                mDb.movieDao().updateMovie(movie);
                            }
                        });
                    }
                });

    }

    private void getReviews(int id) {
        ReviewViewModel reviewViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                getApplication()).create(ReviewViewModel.class);
        reviewViewModel.getReviews(id).observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                if (reviews != null) {
                    mReviewAdapter.setReviewList(reviews);
                    mReviewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getTrailers(int id) {
        TrailerViewModel trailerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                getApplication()).create(TrailerViewModel.class);
        trailerViewModel.getTrailers(id).observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                if (trailers != null) {
                    mTrailerAdapter.setTrailerList(trailers);
                    mTrailerAdapter.notifyDataSetChanged();
                }
            }
        });
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

}
