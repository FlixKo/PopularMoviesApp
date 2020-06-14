package com.example.popularmoviesapp.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class Movie implements Parcelable {

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
    @PrimaryKey(autoGenerate = true)
    public int mId;
    private String mImage;
    private String mYear;
    private String mTitle;
    private String mRating;
    private String mDescription;
    private int mMovieID;
    //private String voteCount;
    private boolean mFavorite;
    //    private ArrayList<Review> mReviews;
    //  private ArrayList<Trailer> mTrailer;
    private double mPopularity;

    public Movie(int id, String image, String year, String title, String rating, double popularity, String description, int movieID) {
        //ArrayList reviews, ArrayList trailer){
        mId = id;
        mImage = image;
        mYear = year;
        mTitle = title;
        mRating = rating;
        mDescription = description;
        mMovieID = movieID;
        mFavorite = false;
        mPopularity = popularity;
        //mReviews = reviews;
        //mTrailer = trailer;
    }

    @Ignore
    public Movie(String image, String year, String title, String rating, double popularity, String description, int movieID) {
        mImage = image;
        mYear = year;
        mTitle = title;
        mRating = rating;
        mDescription = description;
        mMovieID = movieID;
        mFavorite = false;
        mPopularity = popularity;
    }

    @Ignore
    private Movie(Parcel in) {
        mImage = in.readString();
        mTitle = in.readString();
        mYear = in.readString();
        mDescription = in.readString();
        mRating = in.readString();
        mMovieID = in.readInt();
        mPopularity = in.readDouble();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mFavorite = in.readBoolean();
        }
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

    public boolean isFavorite() {
        return mFavorite;
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

    public void setFavorite(boolean mFavorite) {
        this.mFavorite = mFavorite;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double mPopularity) {
        this.mPopularity = mPopularity;
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
        dest.writeString(mRating);
        dest.writeInt(mMovieID);
        dest.writeDouble(mPopularity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(mFavorite);
        }
        //dest.writeList(mReviews);
        //dest.writeList(mTrailer);
    }
}
