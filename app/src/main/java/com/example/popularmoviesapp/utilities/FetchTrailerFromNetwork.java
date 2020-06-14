package com.example.popularmoviesapp.utilities;

import android.content.Context;

import com.example.popularmoviesapp.database.MovieDatabase;
import com.example.popularmoviesapp.models.Trailer;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FetchTrailerFromNetwork {
    private static final String LOG_TAG = FetchTrailerFromNetwork.class.getSimpleName();
    private static MovieDatabase mDb;

    public static void getTrailers(Context context, int id) {
        mDb = MovieDatabase.getInstance(context);
        final int movieId = id;
        final URL url = NetworkUtils.buildTrailerUrl(String.valueOf(id));

        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                String trailersResults = null;
                try {
                    trailersResults = NetworkUtils.getResponseFromHttpUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (trailersResults != null) {
                        List<Trailer> trailers = JsonUtils.extractTrailerFromJSON(trailersResults, movieId);
                        for (int i = 0; i < trailers.size(); i++) {
                            Trailer trailer = trailers.get(i);
                            Trailer dbTrailer = mDb.trailerDao().getTrailerByID(trailer.getId());
                            if (dbTrailer != null) {
                                mDb.trailerDao().updateTrailer(trailer);
                            } else {
                                mDb.trailerDao().insertTrailer(trailer);
                            }
                        }
                    }
                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
