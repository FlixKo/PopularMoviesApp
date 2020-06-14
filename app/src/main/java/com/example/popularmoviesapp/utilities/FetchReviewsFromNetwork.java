package com.example.popularmoviesapp.utilities;

import android.content.Context;

import com.example.popularmoviesapp.database.MovieDatabase;
import com.example.popularmoviesapp.models.Review;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FetchReviewsFromNetwork {
    private static final String LOG_TAG = FetchReviewsFromNetwork.class.getSimpleName();
    private static MovieDatabase mDb;

    public static void getReviews(Context context, int id) {

        mDb = MovieDatabase.getInstance(context);
        final int movieId = id;

        final URL url = NetworkUtils.buildReviewUrl(String.valueOf(id));
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                String reviewsResults = null;

                try {
                    reviewsResults = NetworkUtils.getResponseFromHttpUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (reviewsResults != null) {
                        List<Review> reviews = JsonUtils.extractReviewFromJSON(reviewsResults, movieId);
                        for (int i = 0; i < reviews.size(); i++) {
                            Review review = reviews.get(i);
                            //Log.d(LOG_TAG, review.getContent());
                            Review dbReview = mDb.reviewDao().getReviewByID(review.getId());
                            if (dbReview != null) {
                                mDb.reviewDao().updateReview(review);
                            } else {
                                mDb.reviewDao().insertReview(review);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
