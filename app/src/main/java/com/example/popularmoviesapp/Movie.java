package com.example.popularmoviesapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "movie")
public class Movie implements Parcelable {

    private String mImage;
    private String mYear;
    private String mTitle;
    private String mRating;
    private String mDescription;
    private int mMovieID;
//    private ArrayList<Review> mReviews;
  //  private ArrayList<Trailer> mTrailer;
    //private String mPopularity;
    //private String voteCount;

    @PrimaryKey(autoGenerate = true)
    public int mId;

    public Movie(int id, String image, String year, String title, String rating, String description, int movieID){
                 //ArrayList reviews, ArrayList trailer){
        mId = id;
        mImage = image;
        mYear = year;
        mTitle = title;
        mRating = rating;
        mDescription = description;
        mMovieID = movieID;
        //mReviews = reviews;
        //mTrailer = trailer;
    }

    @Ignore
    public Movie(String image, String year, String title, String rating, String description, int movieID){
        mImage = image;
        mYear = year;
        mTitle = title;
        mRating = rating;
        mDescription = description;
        mMovieID = movieID;
    }

    @Ignore
    private Movie(Parcel in){
        mImage = in.readString();
        mTitle = in.readString();
        mYear = in.readString();
        mDescription = in.readString();
        mRating = in.readString();
        mMovieID = in.readInt();
        //mReviews = in.readArrayList(Review.class.getClassLoader());
        //mTrailer = in.readArrayList(Trailer.class.getClassLoader());
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

    public int getMovieID() {
        return mMovieID;
    }

    public void setMovieID(int mMovieID) {
        this.mMovieID = mMovieID;
    }

//    public void setReviews(ArrayList<Review> mReviews) {
//        this.mReviews = mReviews;
//    }
//
//    public ArrayList<Review> getReviews() {
//        return mReviews;
//    }
//
//    public void setTrailer(ArrayList<Trailer> mTrailer) {
//        this.mTrailer = mTrailer;
//    }
//
//    public ArrayList<Trailer> getTrailer() {
//        return mTrailer;
//    }

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
        dest.writeString(mRating);
        dest.writeInt(mMovieID);
        //dest.writeList(mReviews);
        //dest.writeList(mTrailer);
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
