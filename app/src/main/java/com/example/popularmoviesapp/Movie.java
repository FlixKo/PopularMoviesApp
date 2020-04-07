package com.example.popularmoviesapp;

public class Movie {

    private String mImage;
    private String mYear;
    private String mTitle;
    private String mDuration;
    private String mRating;
    private String mDescription;

    public Movie(String image, String year, String title, String duration, String rating, String description){
        mImage = image;
        mYear = year;
        mTitle = title;
        mDuration = duration;
        mRating = rating;
        mDescription = description;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String mYear) {
        this.mYear = mYear;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String mRating) {
        this.mRating = mRating;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
