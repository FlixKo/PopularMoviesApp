package com.example.popularmoviesapp.utilities;

import android.content.Context;
import android.util.Log;

import com.example.popularmoviesapp.database.MovieDatabase;
import com.example.popularmoviesapp.models.Movie;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FetchMoviesFromNetwork {
    private static final String LOG_TAG = FetchMoviesFromNetwork.class.getSimpleName();
    private static final String POPULARITY_DESCENDING = "popularity.desc";
    private static final String BEST_RATED_DESCENDING = "vote_average.desc";
    private static final String VOTE_COUNT_DESCENDING = "vote_count.desc";

    private static URL popularMoviesUrl = NetworkUtils.buildUrl(POPULARITY_DESCENDING);
    private static URL bestRatedMoviesUrl = NetworkUtils.buildUrl(BEST_RATED_DESCENDING);

    public static void getPopularMovies(Context context) {
        getMovies(popularMoviesUrl, context);
    }

    public static void getTopRatedMovies(Context context) {
        getMovies(bestRatedMoviesUrl, context);
    }

    private static void getMovies(final URL url, final Context context) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                MovieDatabase mDb = MovieDatabase.getInstance(context);

                String popularMoviesResults = null;
                try {
                    popularMoviesResults = NetworkUtils.getResponseFromHttpUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (popularMoviesResults != null) {
                    try {
                        List<Movie> movies = JsonUtils.extractMovieFromJSON(popularMoviesResults);
                        for (int i = 0; i < movies.size(); i++) {
                            Movie networkMovie = movies.get(i);
                            Log.d(LOG_TAG, networkMovie.getTitle() + " id: " + networkMovie.getMovieID());
                            Movie dbMovie = mDb.movieDao().getMovieById(networkMovie.getMovieID());
                            if (dbMovie != null) {
                                networkMovie.setFavorite(dbMovie.isFavorite());
                                //networkMovie.setFavorite(false);
                                mDb.movieDao().updateMovie(networkMovie);
                            } else {
                                mDb.movieDao().insertMovie(networkMovie);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
