package com.example.popularmoviesapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "review")
public class Review {

    private int mMovieId;

    @PrimaryKey
    @NonNull
    private String mId;
    private String mAuthor;
    private String mContent;
    private String mUrl;


    public Review(int movieId, String author, String content, String id, String url) {
        mAuthor = author;
        mContent = content;
        mId = id;
        mUrl = url;
        mMovieId = movieId;
    }

    public int getMovieId() {
        return this.mMovieId;
    }

    public void setMovieId(int movieId) {
        this.mMovieId = movieId;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

}
