package com.example.popularmoviesapp.models;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmoviesapp.database.MovieDatabase;

import java.util.List;

public class ReviewViewModel extends AndroidViewModel {
    private static final String LOG_TAG = ReviewViewModel.class.getSimpleName();
    private MovieDatabase movieDatabase;

    public ReviewViewModel(@NonNull Application application) {
        super(application);
        movieDatabase = MovieDatabase.getInstance(getApplication());
        Log.d(LOG_TAG, "reviewDB created");
    }

    public LiveData<List<Review>> getReviews(int id) {
        return movieDatabase.reviewDao().loadReviews(id);
    }
}