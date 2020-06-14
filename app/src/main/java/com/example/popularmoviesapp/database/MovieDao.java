package com.example.popularmoviesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.popularmoviesapp.models.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY mRating")
    LiveData<List<Movie>> loadAllMovies();

    @Insert
    void insertMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("SELECT * FROM movie WHERE mMovieID = :id")
    LiveData<Movie> loadMovieById(int id);

    @Query("SELECT * FROM movie WHERE mMovieID = :id")
    Movie getMovieById(int id);

    @Query("SELECT * FROM movie WHERE mFavorite = 1")
    LiveData<List<Movie>> loadFavoriteMovies();

    @Query("SELECT * FROM movie ORDER BY mRating DESC")
    LiveData<List<Movie>> loadMoviesByPopularity();

    @Query("SELECT * FROM movie ORDER BY mPopularity DESC")
    LiveData<List<Movie>> loadMoviesByTopRating();
}
