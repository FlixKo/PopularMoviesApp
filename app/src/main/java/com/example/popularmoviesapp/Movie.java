package com.example.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

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

    private Movie(Parcel in){
        mImage = in.readString();
        mTitle = in.readString();
        mYear = in.readString();
        mDescription = in.readString();
        mDuration = in.readString();
        mRating = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImage);
        dest.writeString(mTitle);
        dest.writeString(mYear);
        dest.writeString(mDescription);
        dest.writeString(mDuration);
        dest.writeString(mRating);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
}
