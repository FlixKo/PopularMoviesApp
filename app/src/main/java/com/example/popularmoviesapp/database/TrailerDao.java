package com.example.popularmoviesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.popularmoviesapp.models.Trailer;

import java.util.List;

@Dao
public interface TrailerDao {

    @Query("SELECT * FROM trailer WHERE mMovieId = :movieId ")
    LiveData<List<Trailer>> loadTrailers(int movieId);

    @Query("SELECT * FROM trailer WHERE mId = :trailerID")
    Trailer getTrailerByID(String trailerID);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTrailer(Trailer trailer);

    @Insert
    void insertTrailer(Trailer trailer);

    @Delete
    void deleteTrailer(Trailer trailer);
}
