package com.example.popularmoviesapp.models;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.popularmoviesapp.database.MovieDatabase;

import java.util.List;

public class TrailerViewModel extends AndroidViewModel {
    private static final String LOG_TAG = TrailerViewModel.class.getSimpleName();
    private MovieDatabase movieDatabase;

    public TrailerViewModel(@NonNull Application application) {
        super(application);
        movieDatabase = MovieDatabase.getInstance(getApplication());
        Log.d(LOG_TAG, "trailerDB created");
    }

    public LiveData<List<Trailer>> getTrailers(int id) {
        return movieDatabase.trailerDao().loadTrailers(id);
    }
}