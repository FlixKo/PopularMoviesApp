package com.example.popularmoviesapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.popularmoviesapp.models.Review;

import java.util.List;

@Dao
public interface ReviewDao {

    @Query("SELECT * FROM review WHERE mMovieId = :movieId ")
    LiveData<List<Review>> loadReviews(int movieId);

    @Query("SELECT * FROM review WHERE mId = :reviewID")
    Review getReviewByID(String reviewID);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateReview(Review review);

    @Insert
    void insertReview(Review review);

    @Delete
    void deleteReview(Review review);
}
